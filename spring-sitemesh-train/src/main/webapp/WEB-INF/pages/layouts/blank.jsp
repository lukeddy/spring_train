<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><decorator:title default="springmvc sitemesh测试-模版2" /></title>
	<%@include file="/WEB-INF/pages/include/head.jsp"%>
	<decorator:head />
</head>
<body>
<jsp:include page="../include/navbar.jsp"/>
<div id="page" class="container">
	<div id="header">
		<h3 class="page-header">我是模版2</h3>
	</div>
	<br/>
	<div id="content">
		<decorator:body />
	</div>
</div>
<div class="footer-wrapper">
	<%--<jsp:include page="../include/footer.jsp"/>--%>
</div>
</body>
</html>