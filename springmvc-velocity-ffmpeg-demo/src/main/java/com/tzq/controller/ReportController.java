package com.tzq.controller;

import au.com.bytecode.opencsv.CSVWriter;
import com.tzq.domain.FileInfo;
import com.tzq.service.FileInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-2
 * Time: 上午11:23
 */

@Controller
public class ReportController {

    Logger LOGGER = LoggerFactory.getLogger("ReportController");

    @Autowired
    @Qualifier("fileInfoService")
    private FileInfoService fileInfoService;

    @RequestMapping(value = "/report.csv")
    public String exportCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final String[] header = new String[]{"id", "file name", "title","subtitle","thumb_url","movie_url","createTime","updateTime"};

        List fileList = fileInfoService.queryAll("from FileInfo");

        String tmppath=request.getSession().getServletContext().getRealPath("/")+"report.csv";


        File tempFile = new File(tmppath);
        CSVWriter writer = new CSVWriter(new FileWriter(tempFile));

        writer.writeNext(header);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(int i=0;i<fileList.size();i++){

            FileInfo fileInfo = (FileInfo) fileList.get(i);
            String[] row = new String[]{String.valueOf(fileInfo.getId()),fileInfo.getFileName(),fileInfo.getTitle(),
                                        fileInfo.getSubTitle(),fileInfo.getThumbUrl(),fileInfo.getMovieUrl(),
                                       sdf.format(fileInfo.getCreateTime()) ,sdf.format(fileInfo.getUpdateTime())
            };

            writer.writeNext(row);

        }

        writer.close();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            long fileLength = new File(tmppath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=report.csv"  );

            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(tmppath));
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


        return null;
    }
}
