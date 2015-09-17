<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>上传图片</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/static/js/dropzone-4.0.1/dropzone.min.css"/>
    <link rel="stylesheet" href="${contextPath}/static/css/wangEditor-1.3.9.css"/>
</head>
<body>
<div class="container">
    <h3 class="page-header">
        上传图片
    </h3>
    <div class="row">
        <div style="max-height:400px;z-index:10;">
            <form id="uploadPhoto" class="dropzone" action="${contextPath}/doUploadPhoto" method="post"></form>
        </div>
    </div>
    <br/>
    <div class="row pull-right"><a class="btn btn-primary" href="javascript:void(0);" >关闭</a></div>
</div>
</body>
</html>
<script src="${contextPath}/static/js/jquery-1.10.2.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${contextPath}/static/js/dropzone-4.0.1/dropzone.min.js"></script>
<script src="${contextPath}/static/js/common.js"></script>
<script type="text/javascript">
    $(function(){
        initDropzone();
    });

    /**
     * 初始化上传组建
     * */
    function initDropzone(){
        try {
            $("#uploadPhoto").dropzone({
                paramName: "photoFile",
                maxFilesize: 20, //单位M
                acceptedFiles: "image/jpeg,image/png",
                addRemoveLinks : true,
                dictDefaultMessage :
                        '<img src="${contextPath}/static/images/img_upload.png" style="cursor:pointer;background-color: #0066cc; padding: 20px; border-radius: 110px;"/><br/>\
                        <span class="bigger-100 bolder">拖拽图片或点击上传</span>'
                ,
                dictResponseError: '文件上传失败!',

                //change the previewTemplate to use Bootstrap progress bars
                //previewTemplate: "<div class=\"dz-preview dz-file-preview\">\n  <div class=\"dz-details\">\n    <div class=\"dz-filename\"><span data-dz-name></span></div>\n    <div class=\"dz-size\" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class=\"progress progress-small progress-striped active\"><div class=\"progress-bar progress-bar-success\" data-dz-uploadprogress></div></div>\n  <div class=\"dz-success-mark\"><span></span></div>\n  <div class=\"dz-error-mark\"><span></span></div>\n  <div class=\"dz-error-message\"><span data-dz-errormessage></span></div>\n</div>"
            });
        } catch(e) {
            alert('上传组建，不支持您的浏览器!');
        }
    }
</script>

