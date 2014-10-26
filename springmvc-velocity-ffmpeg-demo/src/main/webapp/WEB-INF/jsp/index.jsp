<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>

<head>
    <title>Admin Login</title>
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/redmond/jquery-ui-1.8.21.custom.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/jqGrid/ui.jqgrid.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/js/bootstrap/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css">

    <script type="text/javascript" src="${contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jqGrid/grid.locale-en.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery-ui-1.8.21.custom.min.js"></script>

    <style>
        #loginBox{
            width:500px;
            margin:20px auto;
            border:1px solid #ddd;
            padding:10px 60px;
            background:#fff;
        }
    </style>

</head>
<body>

<div class="container">
    <div class="row" id="loginBox">
        <form role="form" action="admin/login">
            <h1>Log in</h1>
            <p style="color:red !important;" id="loginError">${login_error}</p>
            <div class="form-group has-success has-feedback">
                <label for="username">Your Username</label>
                <input type="text" class="form-control" placeholder="admin" required="required" name="username" id="username">
                <span class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>
            <div class="form-group has-success has-feedback">
                <label class="control-label" for="password">Your Password</label>
                <input type="password" class="form-control" type="password" placeholder="admin" required="required" name="password" id="password">
                <span class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>
            <p class="login button" style="margin-bottom: 0px;">
                <input type="submit" class="btn btn-default" value="Login">
            </p>
        </form>
    </div>

    </div>

</div>


</body>
</html>
