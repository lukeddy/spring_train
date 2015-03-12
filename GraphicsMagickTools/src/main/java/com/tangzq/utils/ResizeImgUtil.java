package com.tangzq.utils;


import org.apache.commons.io.FileUtils;
import org.im4java.core.*;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-8-31
 * Time: 下午2:37
 */
public class ResizeImgUtil {


    static Properties props = null;

    static {
        try {
            props = PropertiesLoaderUtils.loadAllProperties("graphics_server.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String TOOL_PATH = props.getProperty("IM4JAVA_TOOLPATH");

    /**
     * 按比例压缩图片
     * @param srcPath
     * @param destPath
     * @param destWidth
     * @param destHeigth
     * @throws IOException
     * @throws IM4JavaException
     * @throws InterruptedException
     */
    public static void resizeIMG(String srcPath, String destPath, int destWidth, int destHeigth) throws IOException, IM4JavaException, InterruptedException {

        IMOperation op = new IMOperation();
        ConvertCmd cmd = new ConvertCmd(true);
        //cmd.setAsyncMode(true);
        op.addImage();
        op.resize(destWidth, destHeigth, "^");
        op.quality(50d);
        op.addImage();
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.indexOf("win") != -1) {
            //linux下不要设置此值，不然会报错
            cmd.setSearchPath(TOOL_PATH);
        }
            cmd.run(op, srcPath, destPath);

    }

    /**
     * 图片裁剪,等比压缩
     *
     * @param srcPath 源图片
     * @param desPath 处理后图片
     * @param sw      源图片宽
     * @param sh      源图片高
     * @param dw      处理后图片宽
     * @param dh      处理后图片高
     * @throws Exception
     */
    public static void cropImage(String srcPath, String desPath, int sw, int sh, int dw, int dh) throws Exception {
        if (sw <= 0 || sh <= 0 || dw <= 0 || dh <= 0) {
            return;
        }

        IMOperation op = new IMOperation();
        op.addImage();

        // 如果源图宽度和高度都小于目标宽高，则仅仅压缩图片
        if ((sw <= dw) && (sh <= dh)) {
            op.resize(sw, sh);
        }

        // 如果源图宽度小于目标宽度，并且源图高度大于目标高度
        if ((sw <= dw) && (sh > dh)) {
            op.resize(sw, sh);                          // 压缩图片
            op.append().crop(sw, dh, 0, (sh - dh) / 2); // 切割图片
        }

        // 如果源宽度大于目标宽度，并且源高度小于目标高度
        if ((sw > dw) && (sh <= dh)) {
            op.resize(sw, sh);
            op.append().crop(dw, sh, (sw - dw) / 2, 0);
        }

        // 如果源图宽、高都大于目标宽高
        if (sw > dw && sh > dh) {
            float ratiow = (float) dw / sw; // 宽度压缩比
            float ratioh = (float) dh / sh; // 高度压缩比

            // 宽度压缩比小（等）于高度压缩比（是宽小于高的图片）
            if (ratiow >= ratioh) {
                int ch = (int) (ratiow * sh);                                   // 压缩后的图片高度
                op.resize(dw, ch);                                              // 按目标宽度压缩图片
                op.append().crop(dw, dh, 0, (ch > dh) ? ((ch - dh) / 2) : 0);   // 根据高度居中切割压缩后的图片
            } else {                                                             // （宽大于高的图片）
                int cw = (int) (ratioh * sw);                                   // 压缩后的图片宽度
                op.resize(cw, dh);                                              // 按计算的宽度进行压缩
                op.append().crop(dw, dh, (cw > dw) ? ((cw - dw) / 2) : 0, 0);   // 根据宽度居中切割压缩后的图片
            }
        }

        op.addImage();
        ConvertCmd convert = new ConvertCmd(true);

        convert.run(op, srcPath, desPath);// BufferedImage or String
    }

    /**
     * 取得图片信息
     * @param fileName
     * @return
     * @throws InfoException
     */
    public static Info getImgInfo(String fileName) throws InfoException {
        System.setProperty("im4java.useGM", "true");
        Info imageInfo = new Info(fileName, true);
        return imageInfo;

    }


    /**
     * 按照指定大小压缩图片
     * @param sourceFolder
     * @param destFolder
     * @param imageSizeEnum
     * @throws IM4JavaException
     * @throws IOException
     * @throws InterruptedException
     */
    public static void resizeImages(String sourceFolder, String destFolder, ImageSizeEnum imageSizeEnum) throws IM4JavaException, IOException, InterruptedException {

        String[] extensions = new String[]{"jpg"};
        Collection<File> files = FileUtils.listFiles(new File(sourceFolder), extensions, true);
        for (File f : files) {
            Info imgInfo = getImgInfo(f.getAbsolutePath());
            int[][] resoultions = new int[3][2];
            if (imgInfo.getImageWidth() < imgInfo.getImageHeight()) {

                resoultions[0][0] = (int) imgInfo.getImageWidth() * 1136 / imgInfo.getImageHeight();
                resoultions[0][1] = 1136;
                resoultions[1][0] = (int) imgInfo.getImageWidth() * 960 / imgInfo.getImageHeight();
                resoultions[1][1] = 960;
                resoultions[2][0] = (int) imgInfo.getImageWidth() * 854 / imgInfo.getImageHeight();
                resoultions[2][1] = 854;

            } else {

                resoultions[0][0] = 1136;
                resoultions[0][1] = (int) imgInfo.getImageHeight() * 1136 / imgInfo.getImageWidth();
                resoultions[1][0] = 960;
                resoultions[1][1] = (int) imgInfo.getImageHeight() * 960 / imgInfo.getImageWidth();
                resoultions[2][0] = 854;
                resoultions[2][1] = (int) imgInfo.getImageHeight() * 854 / imgInfo.getImageWidth();

            }

            String desDir = destFolder;
            if (imageSizeEnum == ImageSizeEnum.SIZE_1136) {
                resizeIMG(f.getAbsolutePath(), desDir + File.separator + f.getName(), resoultions[0][0], resoultions[0][1]);
            } else if (imageSizeEnum == ImageSizeEnum.SIZE_960) {
                resizeIMG(f.getAbsolutePath(), desDir + File.separator + f.getName(), resoultions[1][0], resoultions[1][1]);
            } else if (imageSizeEnum == ImageSizeEnum.SIZE_854) {
                resizeIMG(f.getAbsolutePath(), desDir + File.separator + f.getName(), resoultions[2][0], resoultions[2][1]);
            }
        }
    }

    public enum ImageSizeEnum {
        SIZE_1136, SIZE_960, SIZE_854
    }
}
