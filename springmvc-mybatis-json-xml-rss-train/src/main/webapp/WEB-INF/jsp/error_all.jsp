<%@ page import="org.apache.log4j.Logger" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger("DAILY_ALL");
%>
<%
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    String message = (String) request.getAttribute("javax.servlet.error.message");
    String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
    String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
    Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");
    if(t == null) {
        t = (Throwable) request.getAttribute("exception");
    }
    Class<?> exception = (Class<?>) request.getAttribute("javax.servlet.error.exception_type");
    String exceptionName = "";
    if (exception != null) {
        exceptionName = exception.getName();
    }
    if (statusCode == null) {
        statusCode = 0;
    }
    if (statusCode == 500) {
        LOGGER.error(statusCode + "|" + message + "|" + servletName + "|" + uri + "|" + exceptionName + "|" + request.getRemoteAddr(), t);
    }
    else if (statusCode == 404) {
        LOGGER.error(statusCode + "|" + message + "|" + servletName + "|" + uri + "|" + request.getRemoteAddr());
    }

    String queryString = request.getQueryString();
    String url = uri + (queryString == null || queryString.length() == 0 ? "" : "?" + queryString);
    url = url.replaceAll("&amp;", "&").replaceAll("&", "&amp;");

    if(t != null) {
        LOGGER.error("error", t);
    }
%>
<html>
    <head>
        <title>Error</title>
    </head>
    <body>
    <%
        if (statusCode == 404) {
            out.println("Sorry,we can not find the page you are visiting,please contact the admin.<br/><br/>");
            out.println("<a href=\"" + url + "\">refresh,to check the page whether existing</a><br/>");
        }
        else {
            out.println("Sorry,a problem happened,please<a href=\"" + url + "\">refresh</a> to visit again,or go to other page.<br/><br/>");
        }
    %>
    <a href="javascript:void(0);" onclick="history.go(-1)">Back to previous page.</a><br/><br/>
    </body>
</html>
