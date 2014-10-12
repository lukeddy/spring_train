<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]> <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]> <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]> <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js"> <!--<![endif]-->

<head>
    <link type="text/css" rel="stylesheet" href="css/login.css">

    <title>Admin Login</title>
    <style>
        table{
            background:#add8e6;
        }
        td{
            border:1px solid #87cefa;
        }
    </style>
</head>
<body>

<div id="wrapper">
    <div class="animate form" id="login">
       <c:if test="${not empty userList}">
           <table>
           <c:forEach items="${userList}" var="user">
                 <tr><td>${user.name}</td><td>${user.pwd}</td><td><a href="${contextPath}/showUser/${user.id}">详情</a></td></tr>
           </c:forEach>
           </table>
       </c:if>
    </div>
    <ul>
        <li><a href="${contextPath}/users.json">JSON数据</a></li>
        <li><a href="${contextPath}/user.xml">XML数据</a></li>
        <li><a href="${contextPath}/rssfeed">RSS数据</a></li>
    </ul>
</div>
</body>
</html>
