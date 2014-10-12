<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="common/header.jsp"/>
<script type="text/javascript" src='${contextPath}/js/jquery-1.7.2.min.js'></script>
<script type="text/javascript">
    <!--
    function reloadValidateCode() {
        $("#validateCodeImg").attr("src", "${contextPath}/validateCode?data=" + new Date() + Math.floor(Math.random() * 24));
    }
    //-->
</script>
<div id="page">
    <div>
        <c:if test="${not empty message}">
            <p class="msg">${message}</p>
        </c:if>
    </div>
    <form action="${contextPath}/login" method="post">
        <ul>
            <li>姓　名：<input type="text" name="account"/></li>
            <li>密　码：<input type="text" name="password"/></li>
            <li>验证码：<input type="text" name="validateCode"/>&nbsp;&nbsp;<img id="validateCodeImg"
                                                                             src="${contextPath}/validateCode"/>&nbsp;&nbsp;<a
                    href="#" onclick="javascript:reloadValidateCode();">看不清？</a></li>
            <li><input type="submit" value="确认"/></li>
        </ul>
    </form>
    <p><a href="${contextPath}/user/registForm">没有帐号，注册一个</a></p>
</div>
<jsp:include page="common/footer.jsp"/>