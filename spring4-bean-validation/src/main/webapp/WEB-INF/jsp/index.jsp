<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"></c:set>
<html>
<head>
    <title>Hibernate Validate样例</title>
</head>
<body>
<ul>
    <li><a href="${contextPath}/save?name=123">${contextPath}/save?name=123</a></li>
    <li><a href="${contextPath}/save?name=123&password=aa">${contextPath}/save?name=123&password=aa</a></li>
    <li><a href="${contextPath}/ajax?name=123">${contextPath}/ajax?name=123</a></li>
    <li><a href="${contextPath}/ajax?name=123&password=aa">${contextPath}/ajax?name=123&password=aa</a></li>
    <li><a href="${contextPath}/register?name=123&password=aa&confirmation=aabcdef">${contextPath}/register?name=123&password=aa&confirmation=aa</a></li>
    <li><a href="${contextPath}/changePassword?password=aa&confirmation=aabb">${contextPath}/changePassword?password=aa&confirmation=aabb</a></li>
</ul>
</body>
</html>