<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags/alert" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<html>
<head>
    <meta charset="utf-8"/>
    <title>自定义函数示例</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/static/js/dropzone-4.0.1/dropzone.min.css"/>
    <link rel="stylesheet" href="${contextPath}/static/css/wangEditor-1.3.9.css"/>
</head>
<body>
<mytag:alert sucMsg="${suc_msg}" failMsg="${fail_msg}"/>
<h3 class="page-header text-center">Hello ${appName}</h3>
<hr/>
 <div class="container">
     <form action="${contextPath}/saveData" method="post">
     <div class="row">
         <table class="table">
             <caption>
                 <p>生成页面地址：<a href='<c:url value="${previewURI}"/> ' target="_blank">${previewURI}</a></p>
             </caption>
             <tr>
                 <td><label for="title">标题</label></td>
                 <td><input type="text" class="form-input" name="title" id="title" value="${fd.title}" placeholder="文章标题"/></td>
                 <td>图片列表<a href="javascript:void(0);" id="btnUpload">[上传图片]</a></td>
                 <td>

                 </td>
             </tr>
             <tr>
                 <td><label for="content">文章内容</label></td>
                 <td colspan="3">
                       <textarea name="content" id='content' style='height:300px; width:100%;'>
                           ${fd.content}
                       </textarea>
                 </td>
             </tr>
         </table>
     </div>
     <br/>
     <div class="form-group">
         <button type="submit" class="btn btn-primary">提交</button>
         <button type="button" class="btn btn-default">预览</button>
     </div>
     </form>
 </div>
</body>
</html>
<script src="${contextPath}/static/js/jquery-1.10.2.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${contextPath}/static/js/dropzone-4.0.1/dropzone.min.js"></script>
<script src="${contextPath}/static/js/wangeditor/wangEditor-1.3.9.min.js"></script>
<script src="${contextPath}/static/js/common.js"></script>
<script type="text/javascript">
    $(function(){
        //初始化Editor
        var editor = $('#content').wangEditor();
        //点击"上传图片"事件
        $('#btnUpload').on("click",function(){
            var url="${contextPath}/pageUpload";
            openwinx(url,"winUploadPhotos",878,666);
        });

    });
</script>
