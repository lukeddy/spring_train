<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="common/header.jsp"/>
<div id="page">
    <p><a href="${contextPath}">首页</a></p>
    <form action="${contextPath}/user/register" method="post">
        <ul>
            <c:if test="${not empty message}">
              <li><p style="border:1px solid red;font-weight: bold;font-size: 20px;padding:6px;text-align: center;">${message}</p></li>
            </c:if>
            <li>姓　名：<input type="text" name="account" /> </li>
            <li>密　码：<input type="text" name="password" /> </li>
            <li>昵　称：<input type="text" name="nickname" /> </li>
            <li><input type="submit" value="确认" /> </li>
        </ul>
    </form>
</div>
<jsp:include page="common/footer.jsp"/>