package com.tzq.util;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class SessionFilter extends OncePerRequestFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 不过滤的uri
		String[] notFilter = new String[] { "login", "index","videoupload","logout" };

		// 请求的uri
		String uri = request.getRequestURI();

		// uri中包含background时才进行过滤
		if (uri.indexOf("admin") != -1) {
			// 是否过滤
			boolean doFilter = true;
			for (String s : notFilter) {
				if (uri.indexOf(s) != -1) {
					// 如果uri中包含不过滤的uri，则不进行过滤
					doFilter = false;
					break;
				}
			}
			if (doFilter) {
				// 执行过滤
				// 从session中获取登录者实体
				Object obj = request.getSession().getServletContext().getAttribute("appUser");
				if (null == obj) {
					// 如果session中不存在登录者实体，则弹出框提示重新登录
					// 设置request和response的字符集，防止乱码
                    //request.getSession().getServletContext().removeAttribute("appUser");
					request.setCharacterEncoding("UTF-8");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					String loginPage = "/";
					StringBuilder builder = new StringBuilder();
					builder.append("<script type=\"text/javascript\">");
					builder.append("alert('Session expired, please re-login!');");
					builder.append("window.top.location.href='");
					builder.append(loginPage);
					builder.append("';");
					builder.append("</script>");
					out.print(builder.toString());
				} else {
					// 如果session中存在登录者实体，则继续
                       Logger.getLogger("SessionFilter").info("sessionFilter:"+request.getSession().getId()+"===="+request.getSession().getServletContext().getAttribute("loginedSessionId"));
                       Object appSessionId= request.getSession().getServletContext().getAttribute("loginedSessionId");
                       if(request.getSession().getId().equals(appSessionId.toString())){
                          filterChain.doFilter(request, response);
                       }else{
                           // request.getRequestDispatcher("/admin/logout").forward(request,response);
                           request.setCharacterEncoding("UTF-8");
                           response.setCharacterEncoding("UTF-8");
                           PrintWriter out = response.getWriter();
                           String loginPage = "/";
                           StringBuilder builder = new StringBuilder();
                           builder.append("<script type=\"text/javascript\">");
                           builder.append("alert('Another user has login in somewhere else!');");
                           builder.append("window.top.location.href='");
                           builder.append(loginPage);
                           builder.append("';");
                           builder.append("</script>");
                           out.print(builder.toString());
                       }
                }
			} else {
				// 如果不执行过滤，则继续
				filterChain.doFilter(request, response);
			}
		} else {
			// 如果uri中不包含admin，则继续
			filterChain.doFilter(request, response);
		}
	}

}
