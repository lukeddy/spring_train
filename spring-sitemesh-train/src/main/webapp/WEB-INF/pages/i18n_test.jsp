<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/taglib.jsp" %>
<html>
<head>
    <script src="${ctxStatic}/js/i18n/messages_${currentLocale}.js" type="text/javascript"></script>
    <script>
        function showMsg() {
            alert(msg_test);
        }
    </script>
</head>
<body>
<h1>SpringMVC i18n国际化测试示例</h1>
语言 : <a href="${ctx}/i18n/?lang=en">English</a>|
<a href="${ctx}/i18n/?lang=zh">Chinese</a>|<a href="${ctx}/i18n/changeLang">Change</a>

<h3>
    welcome.springmvc : <spring:message code="welcome.springmvc"/>
</h3>
Current Locale : ${pageContext.response.locale}

<c:if test="${not empty i18n_msg}">
    <h1>${i18n_msg}</h1>
</c:if>
<p>
    <button class="btn btn-default" type="button" onclick="showMsg()"><spring:message code="btn.show.js.msg"/></button>
</p>
<br/>
</body>
</html>

