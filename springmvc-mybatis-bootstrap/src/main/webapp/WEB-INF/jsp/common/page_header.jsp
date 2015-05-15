<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="taglibs.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!-- 顶部导航开始 -->
<div class="navbar-header pull-right" role="navigation">
    <ul class="nav ace-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><small>欢迎光临,</small>${user.username} <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="${contextPath}/logout">退出</a></li>
                </ul>
        </li>
    </ul>
</div>
<!-- 顶部导航结束 -->