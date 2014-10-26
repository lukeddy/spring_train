package com.tzq.controller;

import com.tzq.domain.FileInfo;
import com.tzq.service.FileInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-7-18
 * Time: 下午3:12
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class APIController {
    Logger LOGGER = LoggerFactory.getLogger("APIController");

    @Autowired
    @Qualifier("fileInfoService")
    private FileInfoService fileInfoService;

    @RequestMapping("/getInfo")
    public ModelAndView getinfo(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("defaultJsonView");
        FileInfo fileInfo = fileInfoService.get(FileInfo.class, id);
        mav.addObject(fileInfo);
        return mav;
    }

    /**
     * 根据id查找文件路径并下载文件
     *
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping("/download")
    public void download(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;
        String downLoadPath = null;
        String fileName = null;
        FileInfo fileInfo = fileInfoService.get(FileInfo.class, id);
        downLoadPath = fileInfo.getMovieStorePath();
        fileName = fileInfo.getFileName();
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }

    }

    @RequestMapping("/getList")
    public ModelAndView getWeekInfo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("defaultJsonView");
        List list = fileInfoService.queryAll(" from FileInfo");
        mav.addObject(list);
        return mav;
    }
}
