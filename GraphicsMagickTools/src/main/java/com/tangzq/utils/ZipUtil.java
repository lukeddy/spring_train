package com.tangzq.utils;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;

public class ZipUtil {
    public static void zip(String sourceDir, String zipFile) {
        try {
            OutputStream os = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(os);
           //zos.setEncoding("GBK");
            File file = new File(sourceDir);
            String basePath = null;
            if (file.isDirectory()) {
                basePath = file.getPath().substring(0,file.getPath().lastIndexOf(File.separator));
            } else {
                basePath = file.getParent();
            }
            zipFile(sourceDir,basePath, zos);
            zos.closeEntry();
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void zipFile(String sourceDir,String basePath,  ZipOutputStream zos) {
        String pathName;
        byte[] buf = new byte[1024];
        int length = 0;
        try {

            File file = new File(sourceDir);
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    pathName = f.getPath();
                    zipFile(pathName,basePath, zos);
                }
            } else {
                pathName = file.getPath().substring(basePath.length() + 1);
                InputStream is = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                zos.putNextEntry(new ZipEntry(pathName));
                while ((length = bis.read(buf)) > 0) {
                    zos.write(buf, 0, length);
                }
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void unZip(String zipfile, String destDir) {
        destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;
        byte b[] = new byte[1024];
        int length;
        ZipFile zipFile=null;
        try {
            zipFile = new ZipFile(new File(zipfile));
            Enumeration enumeration = zipFile.getEntries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements()) {
                zipEntry = (ZipEntry) enumeration.nextElement();
                File loadFile = new File(destDir + zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    // 这段都可以不要，因为每次都貌似从最底层开始遍历的
                    loadFile.mkdirs();
                } else {
                    if (!loadFile.getParentFile().exists())
                        loadFile.getParentFile().mkdirs();
                    OutputStream outputStream = new FileOutputStream(loadFile);
                    InputStream inputStream = zipFile.getInputStream(zipEntry);
                    while ((length = inputStream.read(b)) > 0){
                        outputStream.write(b, 0, length);
                        outputStream.flush();
                    }
                    inputStream.close();
                    outputStream.close();
                }
            }
            System.out.println("unzip file ["+zipFile+"] completed");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=zipFile){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void unZipAllFileToSameFolder(String zipfile, String destDir) {
        destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;
        byte b[] = new byte[1024];
        int length;
        ZipFile zipFile=null;
        try {
            zipFile = new ZipFile(new File(zipfile));
            Enumeration enumeration = zipFile.getEntries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements()) {
                zipEntry = (ZipEntry) enumeration.nextElement();
                if (zipEntry.isDirectory()) {
                     continue;
                } else {
                    String entityPath=zipEntry.getName();
                    //System.out.println(entityPath.indexOf("/"));
                    String fileName=entityPath.contains("/")? entityPath.substring(entityPath.lastIndexOf("/"),entityPath.length()):entityPath;
                    File destFile=new File(destDir+fileName);
                    if(!destFile.exists()){

                    }
                    OutputStream outputStream = new FileOutputStream(destFile);
                    InputStream inputStream = zipFile.getInputStream(zipEntry);
                    while ((length = inputStream.read(b)) > 0){
                        outputStream.write(b, 0, length);
                        outputStream.flush();
                    }
                    inputStream.close();
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=zipFile){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("unzip file ["+zipFile+"] completed");
    }

    public static void unZipSubFolder(String zipfile,String subFolderName,String fileSuffix, String destDir) {
        destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;
        File destFolder=new File(destDir);
        if(!destFolder.exists()){
            destFolder.mkdirs();
        }
        byte b[] = new byte[1024];
        int length;
        ZipFile zipFile=null;
        try {
            zipFile = new ZipFile(new File(zipfile));
            Enumeration enumeration = zipFile.getEntries();
            ZipEntry zipEntry = null;
            while (enumeration.hasMoreElements()) {
                zipEntry = (ZipEntry) enumeration.nextElement();
                String entityPath=zipEntry.getName().toLowerCase();
                if (zipEntry.isDirectory()) {
                    continue;
                } else if(entityPath.contains(subFolderName)&&entityPath.endsWith(fileSuffix)) {
                    //System.out.println(entityPath.indexOf("/"));
                    //String fileName=entityPath.contains("/")? entityPath.substring(entityPath.lastIndexOf("/"),entityPath.length()):entityPath;
                    String fileName=entityPath.substring(entityPath.lastIndexOf("/"),entityPath.length());
                    File destFile=new File(destDir+fileName);
                    OutputStream outputStream = new FileOutputStream(destFile);
                    InputStream inputStream = zipFile.getInputStream(zipEntry);
                    while ((length = inputStream.read(b)) > 0){
                        outputStream.write(b, 0, length);
                        outputStream.flush();
                    }
                    inputStream.close();
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=zipFile){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("unzip file ["+zipFile+"] completed");
    }
    public static int getFileNumInZip(String zipPath){
        int count=0;
        ZipFile zipFile=null;
        try {
           zipFile=new ZipFile(new File(zipPath));
            Enumeration enumeration = zipFile.getEntries();
            while(enumeration.hasMoreElements()){
              enumeration.nextElement();
              count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null!=zipFile){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }

    public static int getFileNumInSubFolderOfZip(String zipPath,String subFolderName,String fileSuffix){
        int count=0;
        ZipFile zipFile=null;
        try {
            zipFile=new ZipFile(new File(zipPath));
            Enumeration enumeration = zipFile.getEntries();
            while(enumeration.hasMoreElements()){
                ZipEntry zipEntry=(ZipEntry)enumeration.nextElement();
                String entityPath=zipEntry.getName().toLowerCase();
                if (zipEntry.isDirectory()) {
                    continue;
                } else if(entityPath.contains(subFolderName)&&entityPath.endsWith(fileSuffix)) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null!=zipFile){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }



    public static void main(String[] args) throws Exception {
        //ZipUtil.zip("E:\\hwc\\proGate\\image\\中文", "E:\\hwc\\proGate\\image\\中文.zip");
        //ZipUtil.unZip("E:\\hwc\\proGate\\image\\中文.zip", "E:\\hwc\\proGate\\image\\test");
        //System.out.println(getFileNumInZip("D:\\xampp\\htdocs\\pictures.zip"));
       String destFolder="C:\\unzipfolderccc";
       //ZipUtil.unZip4ContainsSubFolder("C:\\imgs.zip",destFolder);
       //ZipUtil.unZip4ContainsSubFolder("C:\\unzipfoldera.zip",destFolder);
        ZipUtil.unZipSubFolder("C:\\unzipfolderxxx.zip", "imgs", ".jpg", destFolder);
        System.out.println(ZipUtil.getFileNumInSubFolderOfZip("C:\\unzipfolderxxx.zip", "imgs", ".jpg"));
    }
}