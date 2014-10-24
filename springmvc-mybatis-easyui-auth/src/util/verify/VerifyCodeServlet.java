package util.verify;
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.awt.Color;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.imageio.ImageIO;

import util.verify.VerifyCode;

/**
 * 向浏览器输出验证码
 * @author org.javachina
 *
 */
public class VerifyCodeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public VerifyCodeServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
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
				BufferedImage bim=VerifyCode.generateImageCode(verifyCode, 70, 22, 15,true,Color.WHITE,Color.BLACK,null);
				ServletOutputStream out=response.getOutputStream();
				ImageIO.write(bim, "JPEG",out);	
				try{
					out.flush();
				}finally{
					out.close();
				}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
