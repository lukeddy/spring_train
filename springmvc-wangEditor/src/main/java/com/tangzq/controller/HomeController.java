package com.tangzq.controller;

import com.tangzq.response.JsonObject;
import com.tangzq.service.ContentService;
import com.tangzq.utils.Constant;
import com.tangzq.vo.FormDataVO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 首页控制器
 */
@Controller
public class HomeController {


    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ContentService contentServicer;

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value="/home")
    public String index(
            ModelMap modelMap
    ){
        modelMap.addAttribute("appName", contentServicer.getAppName());
        return "index";
    }

    @RequestMapping(value="/saveData",method = RequestMethod.POST)
    public String saveFormData(
        FormDataVO formDataVO,
        ModelMap model
    ){
        System.out.println("title:"+formDataVO.getTitle());
        System.out.println("content:" + formDataVO.getContent());

        model.addAttribute("fd", formDataVO);
        model.addAttribute("appName", contentServicer.getAppName());


        try {
            String filename=System.currentTimeMillis()+".html";
            String filePath=getFullPath4Upload(filename);
            String newContent=contentServicer.combineContent(formDataVO.getContent());
            if(null!=newContent){
                FileCopyUtils.copy(newContent.getBytes(),new FileOutputStream(new File(filePath)));
                String previewURI="/"+Constant.UPLOAD_FOLDER +"/"+filename;
                model.addAttribute("previewURI",previewURI);
                model.addAttribute("suc_msg","生成内容成功！");
            }else{
                model.addAttribute("fail_msg","生成内容失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * 跳转到上传图片页面
     * @return
     */
    @RequestMapping(value="/pageUpload")
    public String pageUpload(){
        return "upload";
    }


    /**
     * 处理上传图片
     * @param photoFile
     * @return
     */
    @RequestMapping(value="/doUploadPhoto",method = RequestMethod.POST)
    @ResponseBody
    public JsonObject doUploadPhoto(
            @RequestParam(value = "photoFile",required =true)MultipartFile photoFile){
        JsonObject json=new JsonObject();
        try {
            if(null!=photoFile){
                String filename=savePhoto(photoFile,true);
                String savedURI="/"+Constant.UPLOAD_FOLDER+"/"+filename;
                logger.info("保存图片成功，路径为："+savedURI);
                json.setStatus(Boolean.TRUE);
                json.setMsg("保存图片成功");
                json.setData(savedURI);
            }else{
                json.setStatus(Boolean.FALSE);
                json.setMsg("图片不能为空");
            }
        } catch (Exception e) {
            logger.error("上传图片失败！", e);
            json.setStatus(Boolean.FALSE);
            json.setMsg("保存图片失败！");
        }
        return json;
    }

    private String savePhoto(MultipartFile photoFile,Boolean isReturnSavedFilename) throws IOException {
        String uploadPath =getUploadFolderPath();
        FileUtils.forceMkdir(new File(uploadPath));
        String savedFilename = UUID.randomUUID().toString().replaceAll("-","")+"."+ FilenameUtils.getExtension(photoFile.getOriginalFilename());
        String savedFilePath = uploadPath + File.separator + savedFilename;
        FileCopyUtils.copy(photoFile.getInputStream(), new FileOutputStream(savedFilePath));
        if(isReturnSavedFilename){
            return savedFilename;
        }else{
            return savedFilePath;
        }

    }

    private String getFullPath4Upload(String filename) throws IOException {
        String uploadPath=getUploadFolderPath();
        File folder=new File(uploadPath);
        if(!folder.exists()){
            FileUtils.forceMkdir(new File(uploadPath));
        }
        return uploadPath+File.separator+filename;
    }

    private String getUploadFolderPath() throws FileNotFoundException {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        String uploadFolderPath = WebUtils.getRealPath(servletContext, Constant.UPLOAD_FOLDER);
        return uploadFolderPath;
    }
}
