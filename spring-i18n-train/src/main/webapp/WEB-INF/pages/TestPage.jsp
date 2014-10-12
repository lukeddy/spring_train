<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<c:set var="currentLocale" value="${pageContext.response.locale}" scope="session"/>
<html>
<body>
<h1>Spring MVC internationalization example</h1>

Language : <a href="${contextPath}/?lang=en">English</a>|
<a href="${contextPath}/?lang=zh">Chinese</a>|<a href="${contextPath}/changeLang">Change</a>

<h3>
	welcome.springmvc : <spring:message code="welcome.springmvc"/>
</h3>


Current Locale : ${pageContext.response.locale}

<c:if test="${not empty i18n_msg}">
    <h1>${i18n_msg}</h1>
</c:if>

<div>
    <button type="button" onclick="showMsg()"><spring:message code="btn.show.js.msg"/></button>
</div>
</body>
</html>
<script src="${contextPath}/js/i18n/messages_${currentLocale}.js" type="text/javascript"></script>
<script>
    function showMsg(){
        alert(msg_test);
    }
</script>