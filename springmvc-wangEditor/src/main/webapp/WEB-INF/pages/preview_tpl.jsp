<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <style>
        h3{
            text-align: center;
        }
        img{
            FLOAT: none;
            display: block;
            max-width:100%;
            height:auto;
        }
        p{
            padding-left: 15px;
            padding-right: 15px;
            font-size: 12px;
            line-height: 1.5;
            text-align: left;
            margin-bottom:20px;
            text-align:left;
            color:#5b5b5b;
        }
    </style>
</head>
<body>
${fd.content}
<c:if test="${empty fd.content}">
  <h1 style="text-align: center;margin:40%;color:lightgrey;">预览区域</h1>
</c:if>
</body>
</html>