package util.spring.interceptor;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import util.core.MethodUtil;
import util.spring.SessionUtil;

public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		logger.debug(url);
		if (excludeUrls.contains(url)) {
			return true;
		} else {
			//SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
			String isAdmin=(String) SessionUtil.getAttr(request, "isAdmin");
			if (null!=isAdmin && isAdmin.equals("0")) {// 超管不需要验证权限
				return true;
			} else {
				@SuppressWarnings("unchecked")
				List<String> urls = (List<String>) SessionUtil.getAttr(request, "authUrls");
				if (null!=urls && urls.contains(url)) {
					return true;
				} else {
					//upload.html;JSESSIONID:5CB625DAC2456D7E00D8C74D61444544 形式登录
/*					for(Cookie cookie: request.getCookies()){  
						String JSESSIONID=cookie.getName();
						if("JSESSIONID".equalsIgnoreCase(JSESSIONID)){
							 if(request.getSession().getId().equalsIgnoreCase(cookie.getValue())){
								 return true;
							 }
						}
					}*/
					StringBuffer sb=new StringBuffer();
					sb.append("if(confirm(\"");
					sb.append("无访问权限 -[" + url + "]-联系管理员或确定登录!");
					sb.append("\")){");
					sb.append("window.location.href =\"/admin/login.html\"");
					sb.append("}");
					//System.out.println("无访问权限 -您没有访问此资源的权限！<br/>请联系超管赋予您<br/>[" + url + "]<br/>的资源访问权限！");
					MethodUtil.getInstance().toSript(response,sb.toString());
					//request.getRequestDispatcher("/admin/login.html").forward(request, response);
					return false;
				}
			}
		}
	}
}
