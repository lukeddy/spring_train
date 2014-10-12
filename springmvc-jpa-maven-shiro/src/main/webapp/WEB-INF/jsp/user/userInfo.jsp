<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../common/header.jsp"/>
<div id="page">
    <jsp:include page="../common/nav.jsp"/>
    <div id="wrapper">
        <h2>用户详情</h2>
        <c:if test="${ not empty user}">
            ID:${user.id} <br/>
            Account:${user.account}<br/>
            Name:${user.nickname}<br/>
            Password:${user.password}<br/>
        </c:if>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
