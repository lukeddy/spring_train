package com.tzq.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtil {
     private static String PATH_SEPARATOR = "\\" ;

    /**
     * 根据文件名称取得文件的扩展名
     * @param filename
     * @return
     */
    public static String getFileSuffix(String filename){
        return filename.substring(filename.lastIndexOf("."),filename.length());
    }
     /**
      * TAG for log messages.
      */
     static final String TAG = "FileUtils";
     public static void deleteRealFile(String filePath){
        File file=new File(filePath);
            if(file.exists()){
                file.delete();
            }
    }
     /**
      * 通过后缀判断文件是否图片文件
      *
      * @param extension
      * @return
      */
     public static boolean isPictureFile(String extension) {
           if ( ".png".equals(extension) || ".gif".equals(extension)
                   || ".jpg".equals(extension) || ".jpeg".equals(extension)
                   || ".bmp".equals(extension) || ".dib".equals(extension)) {
               return true;
          }
           return false;
     }

     /**
      * 通过后缀判断文件是否web文件
      *
      * @param extension
      * @return
      */
     public static boolean isWebFile(String extension) {
           if ( ".htm".equals(extension) || ".html".equals(extension)
                   || ".php".equals(extension)) {
               return true;
          }
           return false;
     }

     /**
      * 通过后缀判断文件是否文本文件
      *
      * @param extension
      * @return
      */
     public static boolean isTextFile(String extension) {
           if ( ".txt".equals(extension) || ".csv".equals(extension)
                   || ".xml".equals(extension)) {
               return true;
          }
           return false;
     }

     /**
      * 通过后缀判断文件是否视频文件
      *
      * @param ext
      * @return
      */
     public static boolean isMediaFile(String ext) {
           if ( ".3gp".equals(ext) || ".mp4".equals(ext) || ".m4a".equals(ext)
                   || ".mp3".equals(ext) || ".mid".equals(ext)
                   || ".xmf".equals(ext) || ".mxmf".equals(ext)
                   || ".rtttl".equals(ext) || ".rtx".equals(ext)
                   || ".ota".equals(ext) || ".imy".equals(ext)
                   || ".ogg".equals(ext) || ".wav".equals(ext)) {
               return true;
          }
           return false;
     }

     /**
      * Whether the filename is a video file.
      *
      * @param filename
      * @return
      */
     /*
      * public static boolean isVideo(String filename) { String mimeType =
      * getMimeType(filename); if (mimeType != null &&
      * mimeType.startsWith("video/")) { return true; } else { return false; } }
      */

     /**
      * Whether the URI is a local one.
      *
      * @param uri
      * @return
      */

     public static boolean isLocal(String uri) {
           if (uri != null && !uri.startsWith( "http://")) {
               return true;
          }
           return false;
     }

     /**
      * 取得扩展名 Gets the extension of a file name, like ".png" or ". jpg".
      *
      * @return Extension including the dot("."); "" if there is no extension;
      *         null if uri was null.
      */
     public static String getExtension(String path) {
           if (path == null) {
               return null;
          }

           int dot = path.lastIndexOf( ".");
           if (dot >= 0) {
               return path.substring(dot);
          } else {
               // No extension.
               return "";
          }
     }

     /**
      * 取得不包含文件名称的文件路劲 Returns the path only (without file name).
      *
      * @param file
      * @return
      */
     public static File getPathWithoutFilename(File file) {
           if (file != null) {
               if (file.isDirectory()) {
                    // no file to be split off. Return everything
                    return file;
              } else {
                   String filename = file.getName();
                   String filepath = file.getAbsolutePath();

                    // Construct path without file name.
                   String pathwithoutname = filepath.substring(0,
                             filepath.length() - filename.length());
                    if (pathwithoutname.endsWith( "/")) {
                        pathwithoutname = pathwithoutname.substring(0,
                                  pathwithoutname.length() - 1);
                   }
                    return new File(pathwithoutname);
              }
          }
           return null;
     }

     /**
      * 通过文件路劲和名称构建文件 Constructs a file from a path and file name.
      *
      * @param curdir
      * @param file
      * @return
      */
     public static File getFile(String curdir, String file) {
          String separator = "/";
           if (curdir.endsWith( "/")) {
              separator = "";
          }
          File clickedFile = new File(curdir + separator + file);
           return clickedFile;
     }

     public static File getFile(File curdir, String file) {
           return getFile(curdir.getAbsolutePath(), file);
     }

     /**
      * 删除文件及文件夹下的文件 By default File#delete fails for non-empty directories, it
      * works like " rm". We need something a little more brutual - this does the
      * equivalent of " rm -r"
      *
      * @param path
      *            Root File Path
      * @return true iff the file and all sub files/directories have been removed
      * @throws FileNotFoundException
      */
     public static boolean deleteRecursive(File path)
               throws FileNotFoundException {
           if (!path.exists())
               throw new FileNotFoundException(path.getAbsolutePath());
           boolean ret = true;
           if (path.isDirectory()) {
               for (File f : path.listFiles()) {
                   ret = ret && deleteRecursive(f);
              }
          }
           return ret && path.delete();
     }

     /**
      * 已输入流的形式复制文件
      *
      * @param fis
      * @param out
      * @throws Exception
      */
     public static void copyFile(InputStream fis, File out) throws Exception {
          FileOutputStream fos = new FileOutputStream(out);
           try {
               byte[] buf = new byte[1024];
               int i = 0;
               while ((i = fis.read(buf)) != -1) {
                   fos.write(buf, 0, i);
              }
          } catch (Exception e) {
               throw e;
          } finally {
               if (fis != null)
                   fis.close();
               if (fos != null)
                   fos.close();
          }
     }

     /**
      * 复制文件
      *
      * @param in
      * @param out
      * @throws Exception
      */
     public static void copyFile(File in, File out) throws Exception {
          FileInputStream fis = new FileInputStream(in);
          FileOutputStream fos = new FileOutputStream(out);
           try {
               byte[] buf = new byte[1024];
               int i = 0;
               while ((i = fis.read(buf)) != -1) {
                   fos.write(buf, 0, i);
              }
          } catch (Exception e) {
               throw e;
          } finally {
               if (fis != null)
                   fis.close();
               if (fos != null)
                   fos.close();
          }
     }

     /**
      * 计算某个文件夹的大小 Counts the size of a directory recursively (sum of the length
      * of all files).
      *
      * @param directory
      *            directory to inspect, must not be <code>null</code>
      * @return size of directory in bytes, 0 if directory is security restricted
      * @throws NullPointerException
      *             if the directory is <code>null</code>
      */
     public static long sizeOfDirectory(File directory) {
           if (!directory.exists()) {
              String message = directory + " does not exist";
               throw new IllegalArgumentException(message);
          }

           if (!directory.isDirectory()) {
              String message = directory + " is not a directory";
               throw new IllegalArgumentException(message);
          }

           long size = 0;

          File[] files = directory.listFiles();
           if (files == null) { // null if security restricted
               return 0L;
          }
           for ( int i = 0; i < files. length; i++) {
              File file = files[i];

               if (file.isDirectory()) {
                   size += sizeOfDirectory(file);
              } else {
                   size += file.length();
              }
          }

           return size;
     }
     
     /******************************************zip文件解压，与压缩***********************************/
     /**
      * 解压 zip文件
      *
      * @param zipFileName
      * @param outputDirectory
      * @throws Exception
      */
     public void unZip(String zipFileName, String outputDirectory)
               throws Exception {
           try {
              ZipFile zipFile = new ZipFile(zipFileName);
              Enumeration<?> e = zipFile.entries();
               ZipEntry zipEntry = null;
              createDirectory(outputDirectory, "");
               while (e.hasMoreElements()) {
                   zipEntry = ( ZipEntry) e.nextElement();
                   System. out.println( "========== 解 压 ========== "
                             + zipEntry.getName());
                    // 判断是否为一个文件夹
                    if (zipEntry.isDirectory()) {
                        String name = zipEntry.getName().trim();
                         // 因为后面带有一个/,所有要去掉
                        name = name.substring(0, name.length() - 1);
                        File f = new File(outputDirectory + File.separator + name);
                         if (!f.exists()) {
                             f.mkdir();
                        }
                         // System.out.println("*******创建根目 录*******" +
                         // outputDirectory + File.separator + name);
                   } else {
                        String fileName = zipEntry.getName();
                        fileName = fileName.replace( '\\', '/');

                         // 判断子文件是否带有目录,有创建,没有写文件
                         if (fileName.indexOf( "/") != -1) {
                             createDirectory(outputDirectory, fileName.substring(0,
                                       fileName.lastIndexOf( "/")));
                             fileName = fileName
                                       .substring(fileName.lastIndexOf( "/") + 1);
                        }

                        File f = new File(outputDirectory + File.separator
                                  + zipEntry.getName());
                        f.createNewFile();
                        InputStream in = zipFile.getInputStream(zipEntry);
                        FileOutputStream out = new FileOutputStream(f);

                         byte[] by = new byte[1024];
                         int c;
                         while ((c = in.read(by)) != -1) {
                             out.write(by, 0, c);
                        }
                        in.close();
                        out.close();

                   }
              }
          } catch (Exception ex) {
              System. out.println(ex.getMessage());
          }
          System. out.println( "^^^^^^^^^^ 解压完成 ^^^^^^^^^^");
     }

     /**
      * 压缩整个目录
      *
      * @param directory
      * @param zip
      * @throws IOException
      */
     public static final void zipDirectory(File directory, File zip)
               throws IOException {
          ZipOutputStream zos = new ZipOutputStream( new FileOutputStream(zip));
           zip(directory, directory, zos);
          zos.close();
     }

     /**
      * 压缩某个目录下的文件到 zip
      *
      * @param directory
      * @param base
      * @param zos
      * @throws IOException
      */
     private static final void zip(File directory, File base, ZipOutputStream zos)
               throws IOException {
          File[] files = directory.listFiles();
           byte[] buffer = new byte[8192];
           int read = 0;
           for ( int i = 0, n = files. length; i < n; i++) {
               if (files[i].isDirectory()) {
                    zip(files[i], base, zos);
              } else {
                   FileInputStream in = new FileInputStream(files[i]);
                   ZipEntry entry = new ZipEntry(files[i].getPath().substring(
                             base.getPath().length() + 1));
                   zos.putNextEntry(entry);
                    while (-1 != (read = in.read(buffer))) {
                        zos.write(buffer, 0, read);
                   }
                   in.close();
              }
          }
     }

     /**
      * 解压 zip文件
      *
      * @param zip
      * @param extractTo
      * @throws IOException
      */
     public static final void unzip(File zip, File extractTo) throws IOException {
          ZipFile archive = new ZipFile(zip);
           @SuppressWarnings( "rawtypes")
          Enumeration e = archive.entries();
           while (e.hasMoreElements()) {
              ZipEntry entry = (ZipEntry) e.nextElement();
              File file = new File(extractTo, entry.getName());
               if (entry.isDirectory() && !file.exists()) {
                   file.mkdirs();
              } else {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                   }

                   InputStream in = archive.getInputStream(entry);
                   BufferedOutputStream out = new BufferedOutputStream(
                              new FileOutputStream(file));

                    byte[] buffer = new byte[8192];
                    int read;

                    while (-1 != (read = in.read(buffer))) {
                        out.write(buffer, 0, read);
                   }

                   in.close();
                   out.close();
              }
          }
     }

     /**
      * 在某个文件夹下创建子文件夹
      *
      * @param directory
      * @param subDirectory
      */
     private void createDirectory(String directory, String subDirectory) {
          String dir[];
          File fl = new File(directory);
           try {
               // 如果解压文件基本目录结构不存在,新建
               if (subDirectory == "" && fl.exists() != true) {
                    // System.out.println("*******创建基本目录结 构*******"+directory);
                   fl.mkdir();
              }
               // 主要创建子目录
               else if (subDirectory != "") {
                   dir = subDirectory.replace( '\\', '/').split( "/");
                    for ( int i = 0; i < dir. length; i++) {
                        File subFile = new File(directory + File.separator + dir[i]);
                         if (subFile.exists() == false) {
                              // System.out.println("*******创建子目 录*******"+directory +
                              // File.separator + dir [i]);
                             subFile.mkdir();
                        }
                        directory += File. separator + dir[i];
                   }
              }
          } catch (Exception ex) {
              System. out.println(ex.getMessage());
          }
     }

     /**
      * 创建文件夹
      *
      * @param path
      */
     public static void mkdir(String path) {
           try {
              File dirFile = new File(path);
               if (!dirFile.exists()) {
                   dirFile.mkdir();
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
     }

     /**
      * 删除某个目录下的文件
      *
      * @param folder
      */
     public static void deleteFilesInFolder(String folder) {
          File myFolder = new File(folder);
           if (myFolder.isDirectory()) {
              File[] fList = myFolder.listFiles();
               if ( null != fList && fList. length > 0) {
                    for ( int i = 0; i < fList. length; i++) {
                         if (fList[i].isFile()) {
                             fList[i].delete();
                        }
                   }
              }
          }
     }

     /**
      * 将文件保存到指定目录
      *
      * @param in
      * @param directory
      * @param fileName
      * @return
      * @throws IOException
      */
     public static final String saveFileToFolder(InputStream in,
              String directory, String fileName) throws IOException {
          String path = directory + fileName;
          BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(path));
           byte[] buffer = new byte[1024];
           int len;

           while ((len = in.read(buffer)) >= 0)
              out.write(buffer, 0, len);

          in.close();
          out.close();
           return path;
     }

     /**
      * 移动目录
      *
      * @param oldPath
      * @param newPath
      * @return
      */
     public static void moveFolder(String oldPath, String newPath) {
           copyFolder(oldPath, newPath);
           delFolder(oldPath);
     }

     /**
      * 复制整个文件夹的内容
      *
      * @param oldPath
      *            准备拷贝的目录
      * @param newPath
      *            指定绝对路径的新目录
      * @return
      */
     public static void copyFolder(String oldPath, String newPath) {
           try {
               new File(newPath).mkdirs(); // 如果文件夹不存在 则建立新文件夹
              File a = new File(oldPath);
              String[] file = a.list();
              File temp = null;
               for ( int i = 0; i < file. length; i++) {
                    if (oldPath.endsWith(File. separator)) {
                        temp = new File(oldPath + file[i]);
                   } else {
                        temp = new File(oldPath + File.separator + file[i]);
                   }
                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        FileOutputStream output = new FileOutputStream(newPath
                                  + "/" + (temp.getName()).toString());
                         byte[] b = new byte[1024 * 5];
                         int len;
                         while ((len = input.read(b)) != -1) {
                             output.write(b, 0, len);
                        }
                        output.flush();
                        output.close();
                        input.close();
                   }
                    if (temp.isDirectory()) { // 如果是子文件夹
                         copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                   }
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
     }

     /**
      * 删除文件夹
      *
      * @param folderPath
      *            文件夹完整绝对路径
      * @return
      */
     public static void delFolder(String folderPath) {
           try {
               delAllFile(folderPath); // 删除完里面所有内容
              String filePath = folderPath;
              filePath = filePath.toString();
              java.io.File myFilePath = new java.io.File(filePath);
              myFilePath.delete(); // 删除空文件夹
          } catch (Exception e) {
              e.printStackTrace();
          }
     }

     /**
      * 删除指定文件夹下所有文件
      *
      * @param path
      *            文件夹完整绝对路径
      * @return
      * @return
      */
     public static boolean delAllFile(String path) {
           boolean bea = false;
          File file = new File(path);
           if (!file.exists()) {
               return bea;
          }
           if (!file.isDirectory()) {
               return bea;
          }
          String[] tempList = file.list();
          File temp = null;
           for ( int i = 0; i < tempList. length; i++) {
               if (path.endsWith(File. separator)) {
                   temp = new File(path + tempList[i]);
              } else {
                   temp = new File(path + File. separator + tempList[i]);
              }
               if (temp.isFile()) {
                   temp.delete();
              }
               if (temp.isDirectory()) {
                    delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                    delFolder(path + "/" + tempList[i]); // 再删除空文件夹
                   bea = true;
              }
          }
           return bea;
     }

     /**
      * 移动文件
      *
      * @param oldPath
      * @param newPath
      * @return
      */
     public static void moveFile(String oldPath, String newPath) {
           copyFile(oldPath, newPath);
           delFile(oldPath);
     }

     /**
      * 复制单个文件
      *
      * @param oldPathFile
      *            准备复制的文件源
      * @param newPathFile
      *            拷贝到新绝对路径带文件名
      * @return
      */
     public static void copyFile(String oldPathFile, String newPathFile) {
           try {
               int bytesum = 0;
               int byteread = 0;
              File oldfile = new File(oldPathFile);
               if (oldfile.exists()) { // 文件存在时
                   InputStream inStream = new FileInputStream(oldPathFile); // 读入原文件
                   FileOutputStream fs = new FileOutputStream(newPathFile);
                    byte[] buffer = new byte[1444];
                    while ((byteread = inStream.read(buffer)) != -1) {
                        bytesum += byteread; // 字节数 文件大小
                        System. out.println(bytesum);
                        fs.write(buffer, 0, byteread);
                   }
                   inStream.close();
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
     }

     /**
      * 删除文件
      *
      * @param filePathAndName
      *            文本文件完整绝对路径及文件名
      * @return Boolean 成功删除返回true遭遇异常返回false
      */
     public static boolean delFile(String filePathAndName) {
           boolean bea = false;
           try {
              String filePath = filePathAndName;
              File myDelFile = new File(filePath);
               if (myDelFile.exists()) {
                   myDelFile.delete();
                   bea = true;
              } else {
                   bea = false;
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
           return bea;
     }

     /**
      * 将路径格式化成标准形式 1. 去除../ 2. 统一路径分隔符号 3. 结果和实际文件的大小写一致
      *
      * @param filePath
      *            文件路径
      * @return 消除路径中可能存在../等
      */
     public static String formatFilePath(String filePath) {
          filePath = filePath.replace( "/", "\\").trim();
           // w00114592 20090819 对于D:这样的路径,JDK无法正确识别,必须写成D:\形式才能正确处理
           if (filePath.endsWith( ":")) {
              filePath = filePath + PATH_SEPARATOR;
          }

           // 如果包含"...",就进行代换处理.
           // l00150302 2010-10-27 DTS2010102800679
           if (filePath.contains( "...")) {
               if (filePath.startsWith( "...")) {
                   filePath = "\\" + filePath;
              }

               if (filePath.endsWith( "...")) {
                   filePath = filePath + "\\";
              }

               // 对连续的超过3个的点符号,替换成2个,这样出来的结果和C#的处理一致
              filePath = filePath.replace( "\\", "\\\\")
                        .replaceAll( "\\\\(\\.){3,}\\\\", "\\\\..\\\\").trim()
                        .replace( "\\\\", "\\");
          }

          File f = new File(filePath);

          String rtnValue = filePath;

           try {
              rtnValue = f.getCanonicalPath();
          } catch (IOException e) {
              e.printStackTrace();
          }
           // w00114592 20090819 如果格式化的是一个路径,那么去掉路径后面的分隔符,保证处理的一致性
           if (f.isDirectory() && rtnValue.endsWith(PATH_SEPARATOR )) {
              rtnValue = rtnValue.substring(0, rtnValue.length() - 1);
          }

           return rtnValue;
     }

     /**
      * 读取文本文件内容
      *
      * @param filePathAndName
      *            带有完整绝对路径的文件名
      * @param encoding
      *            文本文件打开的编码方式
      * @return 返回文本文件的内容
      */
     public static String readTxt(String filePathAndName, String encoding)
               throws IOException {
          encoding = encoding.trim();
          StringBuffer str = new StringBuffer( "");
          String st = "";
           try {
              FileInputStream fs = new FileInputStream(filePathAndName);
              InputStreamReader isr;
               if (encoding.equals( "")) {
                   isr = new InputStreamReader(fs);
              } else {
                   isr = new InputStreamReader(fs, encoding);
              }
              BufferedReader br = new BufferedReader(isr);
               try {
                   String data = "";
                    while ((data = br.readLine()) != null) {
                        str.append(data + " ");
                   }
              } catch (Exception e) {
                   str.append(e.toString());
              }
              st = str.toString();
          } catch (IOException es) {
              st = "";
          }
           return st;
     }

     /******************************************流操作***********************************/
     
     /**
      * //解决了中文乱码的问题 将输入流中的字符流保存到文件
      *
      * @param is
      *            输入流
      * @param filePath
      *            文件全路劲
      */
     public static void saveMsgToFile(InputStream is, String filePath) {
          BufferedReader br = null;
          BufferedWriter bw = null;
           try {
              br = new BufferedReader( new InputStreamReader(is, "GBK" ));// 解决了中文乱码的问题
              bw = new BufferedWriter( new OutputStreamWriter(
                         new FileOutputStream( new File(filePath))));
              String str = null;
               while ((str = br.readLine()) != null) {
                   bw.write(str + "\n");
              }
              bw.flush();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
               try {
                    if ( null != br) {
                        br.close();
                   }
                    if ( null != bw) {
                        bw.close();
                   }
              } catch (IOException e) {
                   e.printStackTrace();
              }
          }
     }

     /**
      * 解决了中文乱码问题 将输入流中的字符流保存到文件
      *
      * @param is
      *            输入流
      * @param filePath
      *            文件全路劲
      */
     public static void saveMsgToFile2(InputStream is, String filePath) {
          BufferedReader br = null;
          PrintWriter fw = null;
           try {
              br = new BufferedReader( new InputStreamReader(is, "GBK" ));// 解决了中文乱码的问题
              fw = new PrintWriter( new FileWriter(filePath, true ));
              String str = null;
               while ((str = br.readLine()) != null) {
                   fw.println(str);
              }
              fw.flush();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
               try {
                    if ( null != br) {
                        br.close();
                   }
                    if ( null != fw) {
                        fw.close();
                   }
              } catch (IOException e) {
                   e.printStackTrace();
              }
          }
     }

     /**
      * 出现中文乱码 将输入流中的字符流保存到文件
      *
      * @param is
      *            输入流
      * @param filePath
      *            文件全路劲
      */
     public static void saveMsgToFile3(InputStream is, String filePath) {
          FileOutputStream fos = null;
           try {
              fos = new FileOutputStream(filePath);
               byte[] b = new byte[1024];
               int length = 0;
               while ((length = is.read(b)) != -1) {
                   fos.write(b, 0, length);
              }
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
               try {
                    if ( null != fos) {
                        fos.close();
                   }
                    if ( null != is) {
                        is.close();
                   }
              } catch (IOException e) {
                   e.printStackTrace();
              }
          }
     }
   /******************************文件大小转换****************************/
     /**
      * 取得文件大小
      * @param f
      * @return
      * @throws Exception
      */
     public long getFileSizes(File f) throws Exception {
           long s = 0;
           if (f.exists()) {
              FileInputStream fis = null;
              fis = new FileInputStream(f);
              s = fis.available();
          } else {
              f.createNewFile();
              System.out.println( "file not found" );
          }
           return s;
     }
     /**
      * 递归取得文件夹大小
      * @param f
      * @return
      * @throws Exception
      */
     public long getFileSize(File f) throws Exception
     {
           long size = 0;
          File flist[] = f.listFiles();
           for ( int i = 0; i < flist. length; i++) {
               if (flist[i].isDirectory()) {
                   size = size + getFileSize(flist[i]);
              } else {
                   size = size + flist[i].length();
              }
          }
           return size;
     }
    /**
     *  转换文件大小(B,K,M,G)
     * @param fileS
     * @return
     */
     public String FormetFileSize( long fileS) {
          DecimalFormat df = new DecimalFormat( "#.00");
          String fileSizeString = "";
           if (fileS < 1024) {
              fileSizeString = df.format(( double) fileS) + "B";
          } else if (fileS < 1048576) {
              fileSizeString = df.format(( double) fileS / 1024) + "K";
          } else if (fileS < 1073741824) {
              fileSizeString = df.format(( double) fileS / 1048576) + "M";
          } else {
              fileSizeString = df.format(( double) fileS / 1073741824) + "G";
          }
           return fileSizeString;
     }

     /**
      *递归求取目录文件个数
      * @param f
      * @return
      */
     public long getlist(File f) {
           long size = 0;
          File flist[] = f.listFiles();
          size = flist. length;
           for ( int i = 0; i < flist. length; i++) {
               if (flist[i].isDirectory()) {
                   size = size + getlist(flist[i]);
                   size--;
              }
          }
           return size;
     }

}
