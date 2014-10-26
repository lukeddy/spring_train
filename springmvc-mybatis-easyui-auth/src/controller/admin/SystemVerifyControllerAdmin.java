
package controller.admin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.verify.VerifyCode;

/**
 * <br>
 * <b>功能：</b>类功能描述<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2012-10-19 <br>
 * <b>版权所有：<b>版权所有(C) 2012 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
@Controller
@RequestMapping("/admin")
public class SystemVerifyControllerAdmin {
    
    @RequestMapping("/verifyCode")
	public void  verifyCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			//设置浏览器不缓存本页
			// Set to expire far in the past.   
	        response.setDateHeader("Expires", 0);   
	        // Set standard HTTP/1.1 no-cache headers.   
	        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");   
	        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).   
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");   
	        // Set standard HTTP/1.0 no-cache header.   
	        response.setHeader("Pragma", "no-cache");   
	
			//生成验证码，写入用户session
			String verifyCode=VerifyCode.generateTextCode(VerifyCode.TYPE_NUM_UPPER,4,"0oOilJI1");
			request.getSession().setAttribute(VerifyCode.VERIFY_TYPE_COMMENT,verifyCode);
			System.out.println("verifyCode="+verifyCode);
			
			//输出验证码给客户端
			response.setContentType("image/jpeg");
			/*
			    textCode 文本验证码
				width 图片宽度
				height 图片高度
				interLine 图片中干扰线的条数
				randomLocation 每个字符的高低位置是否随机
				backColor 图片颜色，若为null，则采用随机颜色
				foreColor 字体颜色，若为null，则采用随机颜色
				lineColor 干扰线颜色，若为null，则采用随机颜色
			*/
			BufferedImage bim=VerifyCode.generateImageCode(verifyCode, 65, 22, 8,true,Color.WHITE,Color.BLACK,null);
			ServletOutputStream out=response.getOutputStream();
			ImageIO.write(bim, "JPEG",out);	
			try{
				out.flush();
			}finally{
				out.close();
			}
	}

}

