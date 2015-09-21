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
        .wrapper{
            width:1400px;
            margin:auto;
        }
        .uploaded-photo{
            display:inline-block;
            width:30px;
            height:30px;
            margin:3px;
        }
        .copy-msg{
            font-size: 10px;
            font-weight: bold;
            display: inline-block;
            width: 66px;
            text-align:center;
        }
    </style>
</head>
<body>
 <div class="wrapper">
     <mytag:alert sucMsg="${suc_msg}" failMsg="${fail_msg}"/>
     <h3 class="page-header text-center">Hello ${appName}</h3>
     <hr/>
     <div class="col-md-3 text-center">
         <iframe id="previewFrame" src="${contextPath}/previewTpl" frameborder="0" height="100%"></iframe>
     </div>
     <div class="col-md-9" style="border-right:1px dotted gray;">
         <form id="myForm" action="${contextPath}/saveData" method="post">
             <table class="table">
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
                                     <th>图片</th>
                                     <th>图片地址</th>
                                     <th>操作</th>
                                 </tr>
                                 <tr>
                                     <td><img src="http://static.bootcss.com/www/assets/img/codeguide.png"  class="uploaded-photo"/></td>
                                     <td>http://static.bootcss.com/www/assets/img/codeguide.png</td>
                                     <td>
                                         <a href="javascript:void(0)" class="btn btn-success btn-copy-link"  data-link="http://static.bootcss.com/www/assets/img/codeguide.png">
                                             <i class="glyphicon glyphicon-link"></i>拷贝图片地址
                                         </a>
                                         <span class="copy-msg"></span>
                                     </td>
                                 </tr>
                                 <tr>
                                     <td><img src="http://static.bootcss.com/www/assets/img/jqueryapi.png"  class="uploaded-photo"/></td>
                                     <td>http://static.bootcss.com/www/assets/img/jqueryapi.png</td>
                                     <td>
                                         <a href="javascript:void(0)" class="btn btn-success btn-copy-link"  data-link="http://static.bootcss.com/www/assets/img/jqueryapi.png">
                                             <i class="glyphicon glyphicon-link"></i>拷贝图片地址
                                         </a>
                                         <span class="copy-msg"></span>
                                     </td>
                                 </tr>
                             </table>
                         </div>
                     </td>
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
             <br/>
             <div class="form-group text-right">
                 <button type="submit" class="btn btn-primary">提交</button>
                 <button type="button" class="btn btn-default" id="btnPreview">预览</button>
             </div>
         </form>
         <form action="${contextPath}/preview" id="previewForm" method="post" target="_blank">
             <input type="hidden" name="content" value=""/>
         </form>
     </div>
 </div>
</body>
</html>
<script src="${contextPath}/static/js/jquery-1.10.2.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${contextPath}/static/js/wangeditor/wangEditor-1.3.9.min.js"></script>
<script src="${contextPath}/static/js/webuploader-0.1.5/webuploader.min.js"></script>
<script src="${contextPath}/static/js/zeroclipboard-2.2.0/ZeroClipboard.min.js"></script>
<script>
    //定义全局变量
    var BASE_URL="${contextPath}",
            SERVER_UPLOAD_URL="${contextPath}/doUploadPhoto",
            PARAM_NAME="photoFile",
            PREVIEW_URL="${contextPath}/preview",
            WEB_UPLOADER_PATH="${contextPath}/static/js/webuploader-0.1.5",
            SWF_PATH=BASE_URL+"/static/js/webuploader-0.1.5/Uploader.swf",
            uploader,
            copyClient;

    $(function(){
        //初始化Editor
        var editor = $('#content').wangEditor({
            'onchange': function(html){
                //html参数即编辑器内容的源码
                //console.log(html);
                updatePreviewContent(html);
            }
        });

        //初始化上传组件
        initWebUploader(SWF_PATH,SERVER_UPLOAD_URL,PARAM_NAME);
        //初始化复制组件
        initZeroClipboard();
        //预览按妞事件
        $('#btnPreview').on("click",function(){
            $('#previewForm').find('input[name="content"]').val($('#content').val());
            $('#previewForm').submit();
        });
    });


    /**
     * 更新预览页面内容
     * */
    function updatePreviewContent(content){
        $('#previewFrame').contents().find("body").html(content);
    }

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
                    //将服务器端返回图片地址放到已上传列表中
                    var uploadedPhotoURL=BASE_URL+response.data;
                    var html='<tr>';
                        html+='<td><img src="'+uploadedPhotoURL+'"  class="uploaded-photo"/></td>';
                        html+='<td>'+uploadedPhotoURL+'</td>';
                        html+=' <td>';
                        html+=' <a href="javascript:void(0)" class="btn btn-success btn-copy-link"  data-link="'+uploadedPhotoURL+'">';
                        html+='<i class="glyphicon glyphicon-link"></i>拷贝图片地址';
                        html+='</a>';
                        html+='<span class="copy-msg"></span>';
                        html+='</td>';
                        html+='</tr>';
                    $('#uploadedPhotoTable').append(html);

                   //移除已上传文件
                   $('#thelist').find('#photo-item'+file.id).remove();
                   //重新初始化拷贝组件
                   initZeroClipboard();
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

    /**
    *初始化拷贝组件
     */
    function initZeroClipboard(){
        ZeroClipboard.config( { swfPath: BASE_URL+"/static/js/zeroclipboard-2.2.0/ZeroClipboard.swf" } );
        ZeroClipboard.destroy();
        copyClient = new ZeroClipboard($('.btn-copy-link'));
        copyClient.on("ready",function(){
            copyClient.on("copy",function(event){
                //console.log(event.target);
                //alert("开始拷贝");
                $('.copy-msg').empty();
                copyClient.setData("text/plain",$(event.target).attr("data-link"));
            });
            copyClient.on("aftercopy",function(event){
                //alert("拷贝完成");
                $(event.target).parent().find('.copy-msg').html("复制成功!");
            });
        });
        copyClient.on("error", function() {
            ZeroClipboard.destroy();
            $('.copy-msg').html("出错了，无法使用拷贝功能!");
        });
    }
</script>