<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<title></title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />

	<!-- basic styles -->
	<link href="${contextPath}/assets/js/bootstrap3.3.2/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="${contextPath}/assets/css/font-awesome-4.3.0/css/font-awesome.min.css" />

	<link rel="stylesheet" href="${contextPath}/assets/css/ace.min.css" />
	<link rel="stylesheet" href="${contextPath}/assets/css/ace-rtl.min.css" />

	<!-- inline styles related to this page -->
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="${contextPath}/assets/js/html5shiv.min.js"></script>
	<script src="${contextPath}/assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>
<div class="container">
	<div class="row" style="width:360px;margin:10% auto 0 auto;">
		<form method="post" action="${contextPath}/login">
			<h2 class="form-signin-heading text-center">xxx系统</h2>
			<c:if test="${not empty fail_msg}">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<strong>出错了!</strong> ${fail_msg}
				</div>
			</c:if>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">用户名:</div>
					<input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">密&nbsp;码：</div>
					<input type="password" class="form-control" name="pwd" id="password" placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">验证码:</div>
					<input type="text" class="form-control" style="display:inline-block;width:120px;margin-right:13px;" name="validateCode" id="validateCode" placeholder="请输入验证码"><img id="validateCodeImg" src="${contextPath}/validateCode"/>&nbsp;&nbsp;<a href="#" onclick="javascript:reloadValidateCode();">看不清？</a>
				</div>
			</div>
			<div class="form-group">
				<div class="pull-right"></div>
			</div>
			<button class="btn btn-lg btn-success btn-block" type="submit">登录</button>
		</form>
	</div>
</div>
</body>
</html>
<script src="${contextPath}/assets/js/jquery2.1.3/jquery.min.js"></script>
<script src="${contextPath}/assets/js/bootstrap3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function reloadValidateCode() {
		$("#validateCodeImg").attr("src", "${contextPath}/validateCode?data=" + new Date() + Math.floor(Math.random() * 24));
	}
</script>
