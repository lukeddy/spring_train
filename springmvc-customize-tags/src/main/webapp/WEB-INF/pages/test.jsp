<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>${fns:getConfig("appName")} 自定标记库函数</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <p class="page-header">自定义标记库示例</p>
    <div class="row text-left">
        <p>标题：${fns:getConfig("cfg.title")}</p>
        <p>
            内容：${fns:getConfig("cfg.content")}
        </p>
    </div>
    <br/>
    <br/>
    <p>
        <a href="${ctx}/" class="btn btn-default">返回首页</a>
        <a href="javascript:void(0);" class="btn btn-success" id="showDialog">点我有惊喜</a>
    </p>
    <div id="modelbox" class="row">
        <sys:alert dialogID="myMsgBox" content="Spring是一个牛逼的框架，jQuery是一个牛逼的框架，Bootstrap也是个牛逼的框架" btnID="showDialog"/>
    </div>
</div>

</body>
</html>
