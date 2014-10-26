package com.tzq.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class VideoUtil {
    static   Properties props = null;
    static{
        try {
            //从属性文件加载ffmpeg工具的路径
            props = PropertiesLoaderUtils.loadAllProperties("ffmpeg.properties");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    /**
     * process thumbnails
     * @param inputPath  input file path
     * @param outPath    out file path
     * @param imgSize    image size
     */
    public static boolean processImg(String inputPath, String outPath, String imgSize) {
       // String ffmpegPath ="C:\\ffmpeg\\bin\\ffmpeg"; //ffmpeg.exe所在目录路径
        String ffmpegPath=props.getProperty("ffmpegpath");
        List<String> commend = new java.util.ArrayList<String>();
        commend.add("\"" + ffmpegPath +"\"");
        commend.add("-i");
        commend.add("\"" + inputPath +"\"");
        commend.add("-y");
        commend.add("-f");
        commend.add("image2");
        commend.add("-ss");
        commend.add("5");
        commend.add("-t");
        commend.add("0.001");
        commend.add("-s");
        commend.add(imgSize);
        commend.add("\"" +outPath +"\"");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.start();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //String ffmpegPath ="C:\\ffmpeg\\bin\\ffmpeg"; //ffmpeg.exe所在目录路径
        String outPath="C:\\img\\";
        File file=new File(outPath);
        if(!file.exists()){
            file.mkdirs();
        }
        System.out.println(processImg("C:\\testvideo.mp4",outPath+"aaaa.jpg","480x360"));
    }

}
