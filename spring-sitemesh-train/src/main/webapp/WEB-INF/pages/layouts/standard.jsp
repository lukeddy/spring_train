<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><decorator:title default="springmvc sitemesh测试" /></title>
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<decorator:head />
</head>
<body>
<jsp:include page="../include/navbar.jsp"/>
<div id="page" class="container">
	<div id="header">
		<form action="" class="form-inline text-left" role="form">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="关键字"/>
				<button class="btn btn-default">搜索</button>
			</div>
		</form>
	</div>
	<br/>
	<div id="content">
		<decorator:body />

		<div class="jumbotron">
			<h2>springmvc sitemesh示例</h2>
			<p>sitemesh3是通过一个filter来对response进行拦截处理，在response提交之前，它会把response流里的数据备份起来，清空流，然后用request.getRequestDispatcher(装饰页URI).forward()来取得渲染后的装饰页，然后再把装饰页里的标签替换成之前备份的对应内容。</p>
			<p>也就是说sitemesh不关心你的页面是如何生成的，它只是将两者拼接起来，不管你用的是JSP，Volocity, FreeMarker 还是 SpringMVC, Struts, wicket。</p>
			<p>这样一来你就可以灵活地控制页面的生成方式。你可以把sitemesh3当成一个中间浏览器，他使用用户浏览器发送过来的request对象发送了两次(或以上)请求，第一次是原来的请求， 第二次是对装饰页面的请求，然后把这两个请求结果拼接起来返回给用户浏览器</p></p>
			<p><a class="btn btn-lg btn-primary" href="https://github.com/rstoyanchev/spring-mvc-jquery-sitemesh" role="button" target="_blank">参考：https://github.com/rstoyanchev/spring-mvc-jquery-sitemesh</a></p>
		</div>
	</div>
</div>
<div class="footer-wrapper">
	<jsp:include page="../include/footer.jsp"/>
</div>
</body>
</html>