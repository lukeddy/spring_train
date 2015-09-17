<%@ tag language="java" pageEncoding="UTF-8"%>
<%--<%@ include file="/WEB-INF/views/include/taglib.jsp"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="sucMsg" type="java.lang.String" required="true" description="成功消息"%>
<%@ attribute name="failMsg" type="java.lang.String" required="true" description="失败消息"%>
<c:if test="${not empty sucMsg}">
    <p class="text-center alert alert-success alert-dismissible msgele" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            ${sucMsg}
    </p>
</c:if>
<c:if test="${not empty failMsg}">
    <p class="text-center alert alert-danger alert-dismissible msgele" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            ${failMsg}
    </p>
</c:if>
