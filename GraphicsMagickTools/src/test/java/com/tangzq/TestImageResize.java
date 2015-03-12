package com.tangzq;

import com.tangzq.service.HelloService;
import com.tangzq.utils.ResizeImgUtil;
import junit.framework.Assert;
import org.im4java.core.IM4JavaException;
import org.im4java.core.Info;
import org.im4java.core.InfoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TestImageResize {
    @Autowired
    private HelloService helloService;

    @Test
    public void testSayHello() {
        Assert.assertEquals("Hello world!", helloService.sayHello());
    }

    @Test
    public void testGetImgInfo(){
        try {
            Info info= ResizeImgUtil.getImgInfo("C:\\Users\\Administrator\\Desktop\\gmtest\\testimg.jpg");
            Assert.assertNotNull(info);
            System.out.println(info.getImageFormat()+","+info.getImageGeometry()+","+info.getImageWidth()+","+info.getImageHeight());
            Enumeration<String> properties=info.getPropertyNames();
           if(null!=properties){
               while(properties.hasMoreElements()){
                   String name=properties.nextElement();
                   System.out.println(name+","+info.getProperty(name));
               }
           }
        } catch (InfoException e) {
            e.printStackTrace();
        }

    }

    /**
     * 等比压缩图片测试
     */
    @Test
    public void testResizeImg(){
        String sourceFilePath="C:\\Users\\Administrator\\Desktop\\gmtest\\mongodb.jpg";
        String destFilePath="C:\\Users\\Administrator\\Desktop\\gmtest\\mongodb80x90.jpg";
        try {
            ResizeImgUtil.resizeIMG(sourceFilePath, destFilePath, 80, 90);
            File file=new File(destFilePath);
            if(file.exists()){
                System.out.println("resize image successfully");
            }else{
                System.out.println("resize image failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testResizeImages(){
        String sourceFolder="C:\\Users\\Administrator\\Desktop\\gmtest\\sourcefolder";
        String destFolder="C:\\Users\\Administrator\\Desktop\\gmtest\\destfolder";
        try {
            ResizeImgUtil.resizeImages(sourceFolder, destFolder, ResizeImgUtil.ImageSizeEnum.SIZE_1136);
            ResizeImgUtil.resizeImages(sourceFolder, destFolder, ResizeImgUtil.ImageSizeEnum.SIZE_960);
            ResizeImgUtil.resizeImages(sourceFolder, destFolder, ResizeImgUtil.ImageSizeEnum.SIZE_854);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IM4JavaException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 压缩裁剪图片测试,
     */
    @Test
    public void testCropImage(){
        String sourceFilePath="C:\\Users\\Administrator\\Desktop\\gmtest\\mongodb.jpg";
        String destFilePath="C:\\Users\\Administrator\\Desktop\\gmtest\\testimg_cropimage200x100.jpg";
        try {
            Info info=ResizeImgUtil.getImgInfo(sourceFilePath);
            int sWidth=info.getImageWidth();
            int sHeight=info.getImageHeight();
            ResizeImgUtil.cropImage(sourceFilePath,destFilePath,sWidth,sHeight,200,100);
            File file=new File(destFilePath);
            if(file.exists()){
                System.out.println("crop image successfully");
            }else{
                System.out.println("crop image failed");
            }
        } catch (InfoException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
