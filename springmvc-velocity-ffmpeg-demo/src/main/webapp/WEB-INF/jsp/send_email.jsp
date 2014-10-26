<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
    <title>Files Manage</title>
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/redmond/jquery-ui-1.8.21.custom.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/jqGrid/ui.jqgrid.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/js/bootstrap/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css">

    <script type="text/javascript" src="${contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jqGrid/grid.locale-en.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery-ui-1.8.21.custom.min.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="navigation.jsp"/>
    <div>
        <%--<a href="/admin/send">Send a test email</a>--%>
        <%--<br/>--%>
      <span>
       <c:if test="${not empty msg}">
           <c:out value="${msg}"/>
       </c:if>
     </span>
        <div style="margin-top: 100px;">
            <form action="${contextPath}/admin/send" method="post">
                ToEmail:<br/>
                <input type="text" name="toEmail"/>
                <input type="submit" value="Send Email">
            </form>
        </div>
    </div>
</div>
</body>
</html>
