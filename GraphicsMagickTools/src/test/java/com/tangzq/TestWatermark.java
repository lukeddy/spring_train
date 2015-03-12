package com.tangzq;

import com.tangzq.utils.WatermarkUtil;
import org.junit.Test;

import java.io.File;


public class TestWatermark {

    @Test
    public void testAddWatermark() {

        try {
            String src = "C:\\Users\\Administrator\\Desktop\\gmtest\\mongo.jpg";          //需要加水印的源图片
            String desc = "C:\\Users\\Administrator\\Desktop\\gmtest\\mongo_with_watermark.jpg";   //生成的水印图片的路径
            String water = "C:\\Users\\Administrator\\Desktop\\gmtest\\water.png";     //用中文转换成的背景透明的png图片
            String fontType = "C:\\Users\\Administrator\\Desktop\\gmtest\\simsun.ttc"; //指定字体文件为宋体
            String colorStr = "ff0000"; //颜色
            int fontSize =36;

            WatermarkUtil watermark = new WatermarkUtil();
            /*
             * 把文字转化为一张背景透明的png图片
             * @param str 文字的内容
             * @param fontType 字体，例如宋体
             * @param fontSize 字体大小
             * @param colorStr 字体颜色，不带#号，例如"990033"
             * @param outfile  png图片的路径
             * @throws Exception
             */
            watermark.converFontToImage("时刻智[ShiKeZhi]", fontType, fontSize, colorStr, water);

            /*
             * 把文字的png图片贴在原图上，生成水印
             * @param srcPath		原图片路径
             * @param distPath		新图片路径
             * @param watermarkImg		水印图片路径
             * @param position		九宫格位置[1-9],从上往下,从左到右排序
             * @param x 		横向边距
             * @param y 		纵向边距
             * @param alpha		透明度
             */
            watermark.WatermarkImg(src, desc, water, 1, 20, 20, 100);

            File watermarkedFile=new File(desc);
            if(watermarkedFile.exists()){
                System.out.println("添加水印成功");
            }else{
                System.out.println("添加水印失败");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
