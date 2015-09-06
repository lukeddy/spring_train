<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>${fns:getConfig("appName")} 自定标记库函数</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
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
</div>

</body>
</html>
<script src="//cdn.bootcss.com/jquery/3.0.0-alpha1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>