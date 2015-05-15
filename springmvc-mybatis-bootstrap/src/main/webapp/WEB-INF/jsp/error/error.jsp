<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.StringWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<head>
<!-- basic styles -->
<link href="${contextPath}/assets/js/bootstrap3.3.2/css/bootstrap.min.css" rel="stylesheet"/>
<link rel="stylesheet" href="${contextPath}/assets/css/font-awesome-4.3.0/css/font-awesome.min.css"/>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="${contextPath}/assets/js/html5shiv.min.js"></script>
<script src="${contextPath}/assets/js/respond.min.js"></script>
<![endif]-->

<script src="${contextPath}/assets/js/jquery2.1.3/jquery.min.js"></script>
<script src="${contextPath}/assets/js/bootstrap3.3.2/js/bootstrap.min.js"></script>
<style>
  body{
    background: #f3f3f4;
    font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }

.box {
	position: relative;
	background: #ffffff;
	margin-bottom: 20px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	width: 100%;
	box-shadow: 0px 0 3px 0px rgba(0, 0, 0, 0.1);
}
.box .box-footer {
	border-top: 1px solid #f4f4f4;
	-webkit-border-top-left-radius: 0;
	-webkit-border-top-right-radius: 0;
	-webkit-border-bottom-right-radius: 3px;
	-webkit-border-bottom-left-radius: 3px;
	-moz-border-radius-topleft: 0;
	-moz-border-radius-topright: 0;
	-moz-border-radius-bottomright: 3px;
	-moz-border-radius-bottomleft: 3px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
	border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px;
	padding: 10px;
	background-color: #ffffff;
}
.error-template {
	padding: 40px 15px 5px 15px;
	text-align: center;
	width: 500px!important;
	margin: 0 auto;
}
.shake {
	-webkit-animation-name: shake;
	animation-name: shake;
}
.animated {
	-webkit-animation-duration: 1s;
	animation-duration: 1s;
	-webkit-animation-fill-mode: both;
	animation-fill-mode: both;
}
.error-template h1 {
	font-size: 60px;
}
.error-template h2 {
	margin-bottom: 30px;
}
.btn.btn-primary:hover, .btn.btn-primary:active, .btn.btn-primary.hover {
	background-color: #1d364f;
}
.btn.btn-primary {
	background-color: #293c4e;
}
.error-actions .btn {
	margin-right: 20px;
}

</style>
</head>
<body>
<div class="container">
    <%
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        pageContext.setAttribute("statusCode", statusCode);

        String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String queryString = request.getQueryString();
        String url = uri + (queryString == null || queryString.length() == 0 ? "" : "?" + queryString);
        pageContext.setAttribute("url", url);

        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        request.setAttribute("exception", exception);

    %>
    <c:if test="${statusCode==404}">
        <div class="row">
              <div class="col-md-12">
					<div class="box error-template animated shake">
						<i class="fa fa-rocket"></i>
						<h2>404</h2>
						<div class="error-details alert alert-danger">
							您请求的页面貌似不在这个星球!<refresh><a href='${url}' class='btn btn-link'>试着刷新再试</a></refresh>
						</div>
					</div>
			 </div>
       </div>
    </c:if>
    
    <c:if test="${statusCode!=404}">
	        <div class="row">
	              <div class="col-md-12">
						<div class="box error-template animated shake">
							<i class="fa fa-rocket"></i>
							<h2>服务端出错了！</h2>
							<div class="error-details alert alert-danger">
								服务端出了点儿问题，请稍后再试，<refresh><a href='${url}' class='btn btn-link'> 刷新再试</a></refresh>
							</div>
						</div>
				 </div>
	     </div>
    </c:if>
</div>
</body>
</html>


