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

<h1 class="page-header text-center">Spring MVC - 多种视图导出 (JSON, XML, PDF, Excel)</h1>
<section class="container text-center">
	<a class="btn btn-success" href="${contextPath}/controller/test" class="label label-info">普通页面</a>
	<a class="btn btn-success" href="${contextPath}/controller/get.json" class="label label-info">输出JSON数据</a>
	<a class="btn btn-success" href="${contextPath}/controller/get.xml" class="label label-info">输出XML数据</a>
	<a class="btn btn-success" href="${contextPath}/controller/get.pdf" class="label label-info">PDF导出</a>
	<a class="btn btn-success" href="${contextPath}/controller/get.xlsx" class="label label-info">Excel导出</a>
</section>
</body>
</html>