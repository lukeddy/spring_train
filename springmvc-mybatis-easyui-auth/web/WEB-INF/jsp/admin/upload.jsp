<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="/admin/common/js/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/admin/common/js/uploadify/uploadify.css">
<script type="text/javascript">
$(function() {
    $("#fileUpload").uploadify({
        height : 30,
        width : 120,
        swf : '/admin/common/js/uploadify/uploadify.swf',
        uploader : '/admin/uploadify.html;jsessionid=<%=session.getId()%>',
        cancelImg :'/admin/common/js/uploadify/uploadify-cancel.png',
       // formData : {'userName':'','content':''},
        debug : true,
        fileObjName : 'file',
        buttonText : '上传',
        fileTypeExts : '*.jpg;*.jpge;*.gif;*.png;*.*',
        onUploadSuccess :function(file, data, response){  
        //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data);
        //$("#tempFileName").val(file.name);
        //$("#"+idName).val(data);
         //  alert("上传成功");
        }  
    });
});
</script>
<div>
	<input id="fileUpload" name="file" type="file"/>
</div>
