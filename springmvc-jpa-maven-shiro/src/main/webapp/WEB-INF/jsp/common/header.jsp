<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

<head>
    <jsp:include page="meta.jsp"/>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <title>Admin Login</title>
    <style>
        #page{
            width:960px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<!--设置全局变量一定要添加scope问session级别的-->
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>