package com.server.util;


import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;


/**
 * 文件基本信息工具类
 */
public class FileInfoUtil {
    /**
     * 获得文件类型(文件后缀),要么是文件夹，要么是文件后缀
     * @param path
     * @return
     */
    public static String getFileSuffix(String path) {
        File file = new File(path);
        String info = null;
        if (file.isFile()) {
            info = path.substring(path.lastIndexOf(".") + 1, path.length())
                    + "文件";
        }
        if (file.isDirectory()) {
            info = "文件夹";
        }
        return info;
    }
    /**
     * 获得文件的大小
     *
     * @param path
     * @return
     */
    public static long getFileSize(String path) {
        File f = new File(path);
        long size = 0;
        try {
            if (f.exists()) {
                if (f.isDirectory()) {
                    File flist[] = f.listFiles();
                    for (int i = 0; i < flist.length; i++) {
                        size = size + getFileSize(flist[i].getPath());
                    }
                } else {
                    FileInputStream fis = null;
                    fis = new FileInputStream(f);
                    size = fis.available();
                }
            } else {
                f.createNewFile();
                System.out.println("文件不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
    /**
     * 转换文件的大小以B,KB,M,G等计算
     * @param fileS
     * @return
     */
    public static String formetFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 判断选择的是什么类型的单位，并返回该单位代表的Byte值
     * @param unit
     * @return
     */
    public static long judgeUnit(String unit){
        Long value;
        if (unit.equals("B")) {
            value = 1L;
        } else if (unit.equals("K")) {
            value = 1024L;
        } else if (unit.equals("M")) {
            value = 1048576L;
        } else {
            value = 1073741824L;
        }
        return value;
    }

    // 获得文件夹内文件的个数。
    public static long getFileSize(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }
}
