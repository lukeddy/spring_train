package com.tzq.controller;

import com.tzq.domain.AjaxResponse;
import com.tzq.domain.FileInfo;
import com.tzq.domain.FileList;
import com.tzq.service.FileInfoService;
import com.tzq.util.AppFolders;
import com.tzq.util.VideoUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/admin")
public class FileController{
    Logger LOGGER = LoggerFactory.getLogger("FileController");

    @Autowired
    @Qualifier("fileInfoService")
    private FileInfoService fileInfoService;

    //跳转到上传页面
    @RequestMapping(value = "/uploadpage")
    public String goToUploadPage() {
        return "upload_page";
    }

    @RequestMapping(value = "/videoupload", method = RequestMethod.POST)
    // 将文件上传请求映射到该方法
    @ResponseBody
    public String handleFormUpload(HttpServletRequest request,@RequestParam("myfile") CommonsMultipartFile mFile,@RequestParam("isDim16x9") Integer isDim16x9,@RequestParam("title") String title,@RequestParam("subtitle")String subtitle) throws FileNotFoundException { // 请求参数一定要与form中的参数名对应
        if (null != mFile) {
                String moviePath = request.getSession().getServletContext().getRealPath(AppFolders.UPLOAD_MOVIE_FOLDER); // 获取本地存储路径// WebUtils.getRealPath(servletContext,"/"+user.getUsername()+"/"); //
                makeDir(new File(moviePath));
                String thumbPath = request.getSession().getServletContext().getRealPath(AppFolders.UPLOAD_THUMB_FOLDER);//视频缩略图存放路径
                makeDir(new File(thumbPath));
                String fileSuffix=getFileSuffix(mFile.getFileItem().getName());
                String uniqueUUID=  UUID.randomUUID().toString().replace("-","");
                String movieNameInDB=uniqueUUID+"-"+System.currentTimeMillis()+fileSuffix;
                File movie = new File(moviePath, movieNameInDB); // 新建一个文件
                try {
                    mFile.getFileItem().write(movie); // 将上传的文件写入新建的文件中
                    //根据视频存缩略图
                    String thumbNameInDB=uniqueUUID+"-"+System.currentTimeMillis()+".jpg";
                    File thumb=new File(thumbPath,thumbNameInDB);
                    VideoUtil.processImg(movie.getAbsolutePath(),thumb.getAbsolutePath(),isDim16x9==0?"640x264":"640x264");
                    String requestURL=request.getRequestURL().toString();
                    String requestURI=request.getRequestURI();
                    String baseURL=requestURL.replace(requestURI,"");
                    String thumbUrl=baseURL+AppFolders.UPLOAD_THUMB_FOLDER+"/"+thumb.getName();
                    String movieUrl=baseURL+AppFolders.UPLOAD_MOVIE_FOLDER+"/"+movie.getName();
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFileName(mFile.getFileItem().getName());
                    fileInfo.setStoreId(uniqueUUID);
                    fileInfo.setTitle(title);
                    fileInfo.setSubTitle(subtitle);
                    fileInfo.setThumbUrl(thumbUrl);
                    fileInfo.setMovieUrl(movieUrl);
                    fileInfo.setThumbStorePath(thumb.getPath());
                    fileInfo.setMovieStorePath(movie.getPath());
                    Date date=new Date();
                    fileInfo.setCreateTime(date);
                    fileInfo.setUpdateTime(date);
                    LOGGER.info(fileInfo.toString());
                    fileInfoService.editSave(fileInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            return "Successed";// 返回成功视图
        } else {
            return "Failed"; // 返回失败视图
        }
    }
    private void makeDir(File dirPath){
        if (!dirPath.exists()) {
                    dirPath.mkdirs();
        }
    }
    private String getFileSuffix(String filename){
        return filename.substring(filename.lastIndexOf("."),filename.length());
    }
    @RequestMapping (value="/listfiles")
    public ModelAndView listFile(ModelMap modelMap){
        List filelist=fileInfoService.queryAll(" from FileInfo");
        LOGGER.debug(filelist.toString());
        modelMap.addAttribute("filelist",filelist);
        return new ModelAndView("file_list",modelMap);
    }

    @RequestMapping(value="/delFile/{id}")
    public String deleteFile(@PathVariable int id){
        fileInfoService.deleteFile(id);
        return "redirect:/listfiles";
    }

    @RequestMapping(value = "/filepage")
    public ModelAndView ajaxListFile(@RequestParam("page") Long page, @RequestParam("rows") int rows){
        FileList fileList = new FileList();

        Long count = fileInfoService.countAll(FileInfo.class);
        fileList.setRecords(count);
        fileList.setPage(page);
        Long total = 0l;
        if(count%rows==0){
            total = count/rows;
        }else {
            total = count/rows+1;
        }

        fileList.setTotal(total);
        List ft=fileInfoService.listAll(FileInfo.class,page.intValue(),rows);
        fileList.setRows(ft);
        ModelAndView mav = new ModelAndView("defaultJsonView");
        mav.addObject(fileList);
        return mav;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public  String ajaxDelFile(@RequestParam("oper") String oper,@RequestParam("id") String id){

        if(!StringUtils.isEmpty(oper)&&!StringUtils.isEmpty(id)){
//            String hsql = "delete from FileInfo where id in" +"("+id+")" ;
//            fileInfoService.delete(hsql);
            fileInfoService.deleteFile(id);

        }

        return id;
    }

    @RequestMapping("/updateMovie")
    public ModelAndView updateMovie(@RequestParam("id") Integer id, @RequestParam("title") String title, @RequestParam("subtitle") String subtitle) {
        AjaxResponse ajaxResp = new AjaxResponse();
        ModelAndView mv = new ModelAndView("defaultJsonView");
        FileInfo fileInfo = fileInfoService.get(FileInfo.class, id);
        fileInfo.setTitle(title);
        fileInfo.setSubTitle(subtitle);
        fileInfo.setUpdateTime(new Date());
        fileInfoService.editUpdate(fileInfo);
        ajaxResp.setAjaxResult(AjaxResponse.SUCCESS);
        ajaxResp.setRespObj(fileInfo);
        mv.addObject(ajaxResp);
        return mv;
    }

    @RequestMapping("/play")
    public ModelAndView play(@RequestParam("id") Integer id,ModelMap modelMap){
        FileInfo fileInfo = fileInfoService.get(FileInfo.class, id);
        LOGGER.debug(fileInfo.toString());
        modelMap.addAttribute("fileInfo",fileInfo);
        return new ModelAndView("player",modelMap);
    }
}