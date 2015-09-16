package com.tangzq.controller;

import com.baidu.ueditor.ActionEnter;
import com.tangzq.service.MyService;
import com.tangzq.utils.Constant;
import com.tangzq.vo.FormDataVO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 首页控制器
 */
@Controller
public class HomeController {

    @Autowired
    private MyService myService;

    /**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value="/home")
    public String index(
            ModelMap modelMap
    ){
        modelMap.addAttribute("appName", myService.getAppName());
        return "index";
    }

    @RequestMapping(value="/testue")
    public void testUEditor(
            HttpServletRequest request,
            HttpServletResponse response){

        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Type", "text/html");

        String rootPath = getRealPath("/");
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write(new ActionEnter(request, rootPath).exec());
    }

    @RequestMapping(value="/saveData",method = RequestMethod.POST)
    public String saveFormData(
        FormDataVO formDataVO,
        ModelMap model
    ){
        System.out.println("title:" + formDataVO.getTitle());
        System.out.println("content:" + formDataVO.getContent());

        model.addAttribute("fd", formDataVO);
        model.addAttribute("appName", myService.getAppName());


        try {
            String filename=System.currentTimeMillis()+".html";
            String filePath=getFullPath4Upload(filename);
            FileCopyUtils.copy(formDataVO.getContent().getBytes(),new FileOutputStream(new File(filePath)));
            String previewURI="/"+Constant.LOCAL_UPLOADER_FOLDER+"/"+filename;
            model.addAttribute("previewURI",previewURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }

    private String getFullPath4Upload(String filename) throws IOException {
        String uploadPath = getRealPath(Constant.LOCAL_UPLOADER_FOLDER);
        File folder=new File(uploadPath);
        if(!folder.exists()){
            FileUtils.forceMkdir(new File(uploadPath));
        }
        return uploadPath+File.separator+filename;
    }

    private String getRealPath(String relativePath){
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        try {
            return WebUtils.getRealPath(servletContext, relativePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
