<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>百度UEditor示例</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<h3 class="page-header text-center">${appName}</h3>
<hr/>
 <%--<div class="container">--%>
     <%--<form action="${contextPath}/saveData" method="post">--%>
     <%--<p>--%>
         <%--<label for="title">--%>
             <%--标题--%>
         <%--</label>--%>
         <%--<input type="text" name="title" id="title" value="${fd.title}" placeholder="文章标题"/>--%>
     <%--</p>--%>
     <%--<div class="row">--%>
         <%--<label for="content">文章内容</label>--%>
         <%--<textarea name="content" id='content' style='height:300px; width:100%;'>--%>
           <%--${fd.content}--%>
         <%--</textarea>--%>
     <%--</div>--%>
     <%--<div class="form-group">--%>
         <%--<button type="submit" class="btn btn-primary">提交</button>--%>
     <%--</div>--%>
     <%--</form>--%>
     <%--<p>生成页面地址：<a href="${contextPath}/${previewURI}" target="_blank">${previewURI}</a></p>--%>
 <%--</div>--%>
    <!-- 加载编辑器的容器 -->
    <div class="container">
        <script id="container" name="content" type="text/plain">
            这里写你的初始化内容
        </script>
    </div>
</body>
</html>
<script src="${contextPath}/static/js/jquery-1.10.2.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="${contextPath}/static/js/ueditor1.4.3.1/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${contextPath}/static/js/ueditor1.4.3.1/ueditor.all.min.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('container');
</script>