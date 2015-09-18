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
    <link rel="stylesheet" href="${contextPath}/static/css/wangEditor-1.3.9.css"/>
    <link rel="stylesheet" href="${contextPath}/static/js/webuploader-0.1.5/webuploader.css"/>
    <link rel="stylesheet" href="${contextPath}/static/css/myuploader.css"/>
    <style>
        .uploaded-photo{
            display:inline-block;
            width:30px;
            height:30px;
            margin:3px;
        }
    </style>
</head>
<body>
<mytag:alert sucMsg="${suc_msg}" failMsg="${fail_msg}"/>
<h3 class="page-header text-center">Hello ${appName}</h3>
<hr/>
 <div class="container">
     <div class="row">

         <table class="table">
             <caption>
                 <p><b>生成页面地址：</b><a href='<c:url value="${previewURI}"/> ' target="_blank">${previewURI}</a></p>
             </caption>
             <tr>
                 <td><label>图片上传区域</label> </td>
                 <td>
                     <div class="row">
                         <div id="upload_box" class="row text-center">
                             <div id="uploaderbox">
                                 <table class="table table-striped text-center" id="thelist"></table>
                                 <div class="btns" id="webuploaddiv">
                                     <div id="picker">点击选择图片</div>
                                 </div>
                             </div>
                         </div>
                         <span id="errormsg" style="margin-right:10px;color: red;font-weight:bold;"></span>
                         <%--<button type="button" class="btn btn-default" id="uploadBtn">开始上传</button>--%>
                     </div>
                     <br/>
                     <div class="row">
                         <table class="table" id="uploadedPhotoTable">
                             <tr>
                                 <th>已上传图片</th>
                                 <th>图片地址</th>
                             </tr>
                             <tr>
                                 <td><img src=""  class="uploaded-photo"/></td>
                                 <td>图片地址</td>
                             </tr>
                         </table>
                     </div>
                 </td>
             </tr>
             <tr>
                 <td><label for="title">标题</label></td>
                 <td><input type="text" class="form-input" name="title" id="title" value="${fd.title}" placeholder="文章标题"/></td>
             </tr>
             <tr>
                 <td><label for="content">文章内容</label></td>
                 <td>
                       <textarea name="content" id='content' style='height:500px; width:100%;'>
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
<script src="${contextPath}/static/js/wangeditor/wangEditor-1.3.9.min.js"></script>
<script src="${contextPath}/static/js/webuploader-0.1.5/webuploader.min.js"></script>
<script type="text/javascript">
    $(function(){
        //初始化Editor
        var editor = $('#content').wangEditor();
    });
</script>
<script>
    var BASE_URL="${contextPath}",
     SERVER_UPLOAD_URL="${contextPath}/doUploadPhoto",
     PARAM_NAME="photoFile",
     PREVIEW_URL="${contextPath}/preview",
     WEB_UPLOADER_PATH="${contextPath}/static/js/webuploader-0.1.5",
     SWF_PATH=BASE_URL+"/static/js/webuploader-0.1.5/Uploader.swf",
     uploader;
</script>
<%--<script src="${contextPath}/static/js/myuploader.js"></script>--%>
<script>

    $(function(){
        initWebUploader(SWF_PATH,SERVER_UPLOAD_URL,PARAM_NAME)
    });

    /**
     *初始化上传组件
     */
    function initWebUploader(swf_url,server_upload_url,param_name){
        if (!WebUploader.Uploader.support()) {
            alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
            throw new Error('WebUploader does not support the browser you are using.');
        }

        uploader = WebUploader.create({
            auto: true,
            swf:swf_url,
            server:server_upload_url,
            pick: '#picker',
            resize: false,
            fileVal:param_name,
            fileNumLimit:50,
            formData:null,
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            },
        });
        $("#picker .webuploader-pick").click(function(){
            $("#picker :file").click();
        });
        uploader.on( 'fileQueued', function(file) {
            var appendedHTML=  '<tr id="photo-item'+file.id+'">';
                appendedHTML+= '<td>'+file.name+'</td>';
                appendedHTML+= '<td id="stateBox'+file.id+'">';
                appendedHTML+= '<p class="state">等待上传...</p>';
                appendedHTML+= '<p class="progress">';
                appendedHTML+= '<span class="progress-bar"></span>';
                appendedHTML+= '</p>' ;
                appendedHTML+= '<span class="percentage"></span>%';
                appendedHTML+= '</td>';
                appendedHTML+= '</tr>';
            $('#thelist').append(appendedHTML);
        });

        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $photoItem = $( '#photo-item'+file.id ),
                    $percentBar = $photoItem.find('.progress .progress-bar'),
                    $percentage=$photoItem.find('.percentage');
            if ( !$percentBar.length ) {
                $percentBar = $('<div class="progress progress-striped active">' +
                                   '<div class="progress-bar" role="progressbar" style="width: 0%"></div>' +
                                '</div>').appendTo( $photoItem ).find('.progress-bar');
            }
            $photoItem.find('p.state').text('正在上传...');
            $percentBar.css( 'width', percentage * 100 + '%' );
            $percentage.html(percentage*100);
        });
        uploader.on( 'uploadSuccess', function( file,response) {
            if (response.status) {
                    console.log(response);
                    var uploadedPhotoURL=BASE_URL+response.data;
                    var html='<tr>';
                        html+='<td><img src="'+uploadedPhotoURL+'"  class="uploaded-photo"/></td>';
                        html+='<td>'+uploadedPhotoURL+'</td>';
                        html+='</tr>';
                    $('#uploadedPhotoTable').append(html);
                   //TODO 移除已上传文件
                   $('#thelist').find('#photo-item'+file.id).remove();
            }else{
                $("#errormsg").html(response.msg);
                $("#thelist").html("");
                uploader.reset();
            }
        });

        uploader.on( 'uploadError', function( file ) {
            $( '#'+file.id ).find('p.state').text('上传出错了!');
        });

        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').fadeOut();
        });

        //$("#uploadBtn").bind("click",function(){
        //    uploader.upload();
        //});
    }

</script>