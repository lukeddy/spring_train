package util.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public final class SessionUtil {
	protected static final Logger logger = Logger.getLogger(SessionUtil.class);
	private static final String USER = "user";    //用户
	private static final String VERIFY_CODE = "verifyCode";//验证码
   // private static final List<String> authUrls;
  
	/**
	  * 设置session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	 /**
	  * 获取session的值
	  * @param request
	  * @param key
	  * @param value
	  */
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 /**
	  * 删除Session值
	  * @param request
	  * @param key
	  */
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	 /**
	  * 设置用户信息 到session
	  * @param request
	  * @param user
	  */
	 public static void setUser(HttpServletRequest request,Object user){
		 request.getSession(true).setAttribute(USER, user);
	 }
	 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static Object getUser(HttpServletRequest request){
		return request.getSession(true).getAttribute(USER);
	 }
	 
	 
	 /**
	  * 从session中获取用户信息
	  * @param request
	  * @return SysUser
	  */
	 public static void removeUser(HttpServletRequest request){
		removeAttr(request, USER);
	 }
	 
	 
	 /**
	  * 设置验证码 到session
	  * @param request
	  * @param user
	  */
	 public static void setVerifyCode(HttpServletRequest request,String sessionVerifyCode){
		 request.getSession(true).setAttribute(VERIFY_CODE, sessionVerifyCode);
	 }
	 
	 
	 /**
	  * 从session中获取验证码
	  * @param request
	  * @return SysUser
	  */
	 public static String getVerifyCode(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(VERIFY_CODE);
	 }
	 
	 
	 /**
	  * 从session中获删除验证码
	  * @param request
	  * @return SysUser
	  */
	 public static void removeVerifyCode(HttpServletRequest request){
		removeAttr(request, VERIFY_CODE);
	 }

	/**
	 * 
	 * <br>
	 * <b>功能：</b>清除所有Session<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-15 <br>
	 * 
	 * @param request
	 */
	public static void removeSessionAll(HttpSession session) {
		if (session != null) {
			java.util.Enumeration<?> e = session.getAttributeNames();
			while (e.hasMoreElements()) {
				String sessionName = (String) e.nextElement();
				System.out.println("removeSessionName:" + sessionName);
				session.removeAttribute(sessionName);
			}
		}
	}
}
