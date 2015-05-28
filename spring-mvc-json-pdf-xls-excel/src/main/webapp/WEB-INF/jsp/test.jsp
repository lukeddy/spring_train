<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Spring MVC - 多种视图输出数据 (JSON, XML, PDF, Excel)</title>
  	<link href="${contextPath}/static/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<section class="container text-center">
	<h1 class="page-header text-center">Spring MVC - 多种视图导出 (JSON, XML, PDF, Excel)</h1>
	<p class="text-left"><a class="btn btn-link" href="${contextPath}/" class="label label-info">返回</a></p>
	<h3 class="text-center">${hello}</h3>
</section>
</body>
</html>