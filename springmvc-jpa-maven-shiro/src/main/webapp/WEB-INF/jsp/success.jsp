<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]> <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]> <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]> <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js"> <!--<![endif]-->
<head>
    <%--<meta http-equiv="refresh" content="5; url=${pageContext.request.contextPath}/user/list"/>--%>
    <script>
        var totalSecond=2;
        function showTime(){
            var timer=document.getElementById("timer");
            timer.innerHTML=totalSecond--;
            if(totalSecond<=0){
                location.href = "${pageContext.request.contextPath}/user/list";
                clearInterval(inter);
            }
        }

        var inter = setInterval("showTime()",1000);
    </script>
</head>
<body>
<p class="msg">登录成功,<span style="font-weight: bolder;font-size: 16px;" id="timer">2</span>秒后页面将自动跳转,如果您的浏览器没有自动跳转,<a href="${pageContext.request.contextPath}/user/list">请点击这里</a></p>
</body>
</html>