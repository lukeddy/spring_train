<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>

<head>
    <title>Files Manage</title>
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/redmond/jquery-ui-1.8.21.custom.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/jqGrid/ui.jqgrid.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/js/bootstrap/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css">
    <link type="text/css" rel="stylesheet" href="${contextPath}/js/uploadify/uploadify.css">

    <script type="text/javascript" src="${contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jqGrid/grid.locale-en.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery-ui-1.8.21.custom.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/uploadify/jquery.uploadify-3.1.min.js"></script>

</head>
<body>
 <div class="container">
     <jsp:include page="navigation.jsp"/>
     <div id="mywrapper" >
         <div class="contentArea">
             <p class="uploadtitle">
                 Admin Uploaded File(s)
             </p>
             <p>
                 <input type="checkbox" name="isDim16x9" id="isDim16x9"/>The dimension is 16:9?
             </p>
             <div>
                 <p>
                     <input type="file" name="file_upload" id="file_upload" />
                 <div id="uploadBtnArea">
                     <a id="uploadBtn" class="uploadBtn" href="javascript:$('#file_upload').uploadify('upload','*')">Start Upload</a>
                     <a id="cancelBtn" class="uploadBtn" href="javascript:$('#file_upload').uploadify('cancel','*')">Cancel All</a>
                 </div>
                 <div style="margin-top:20px;">
                     <p>
                         <label for="title" style="padding-left:26px;"> Title: </label>
                         <input type="text" placeholder="enter title" required="required" name="title" id="title">
                     </p>

                     <p>
                         <label for="subtitle"> Sub Title: </label>
                         <input type="text" placeholder="enter subtitle" required="required" name="subtitle" id="subtitle">
                     </p>
                 </div>
                 </p>
             </div>
         </div>
     </div>
 </div>
</body>
</html>
<script>
    $(function() {
        $('#file_upload').uploadify({
            'buttonText':'Browse',
            'auto'     : false,
            'method'   : 'post',
            'fileObjName' : 'myfile',
            'swf': '/js/uploadify/uploadify.swf',
            'fileTypeExts' : '*.mp4; *.3gp; *.mov;*.avi;*.webm;*.wmv;*.flv',
            'uploader': 'videoupload',
            'debug':false,
            'onUploadStart' : function(file) {
                var isDim16x9= ($("#isDim16x9").attr("checked")=="checked")?1:0;
                var title=$("input[name='title']").val();
                if(!title){
                    title="Default title";
                }
                var subtitle=$("input[name='subtitle']").val();
                if(!subtitle){
                    subtitle="Default sub title";
                }
                $("#file_upload").uploadify("settings", "formData", {'isDim16x9':isDim16x9,'title':title,'subtitle':subtitle});
            }
            // Put your options here
        });
        $("#uploadBtnArea").insertAfter($("#file_upload-button"));
    });
</script>