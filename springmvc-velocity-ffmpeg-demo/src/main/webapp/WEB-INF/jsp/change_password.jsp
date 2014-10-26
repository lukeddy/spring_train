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

    <script type="text/javascript" src="${contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jqGrid/grid.locale-en.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/jquery-ui-1.8.21.custom.min.js"></script>

</head>

<body>
<div class="container">
    <jsp:include page="navigation.jsp"/>
    <div class="main">
        <div class="wraper">
            <h2 style="margin-bottom:2px;margin-top:6px;">Change Password</h2>

            <p style="color:red;padding:6px;margin-top:0px;margin-bottom:0px;">${changepwd_success}${changepwd_error}</p>

            <form class="changepwd_form" method="post" action="changepwd">
                <table width="100%" style="table-layout:fixed;">
                    <tr>
                        <td class="need" style="width:10px;">*</td>
                        <td style="width:106px;">Old Password：</td>
                        <td style="width:280px;"><input type="password" value="" name="oldPwd" class="inputxt" datatype="*4-18"
                                                        nullmsg="Please enter old password！"
                                                        errormsg="Password's length should be more than 4 characters,and less than 18 characters！"/>
                        </td>
                        <td>
                            <div class="Validform_checktip"></div>
                            <div class="info">Password's length should be more than 4 characters,and less than 18
                                characters！
                                <span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="need" style="width:10px;">*</td>
                        <td style="width:106px;">New Password：</td>
                        <td style="width:210px;">
                            <input type="password" value="" name="newPwd" class="inputxt"
                                   datatype="*4-18" nullmsg="Please enter new password！"
                                   errormsg="Password's length should be more than 4 characters,and less than 18 characters！"/>
                        </td>
                        <td>
                            <div class="Validform_checktip"></div>
                            <div class="info">Password's length should be more than 4 characters,and less than 18
                                characters！
                                <span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="need">*</td>
                        <td>Repeat Password：</td>
                        <td><input type="password" value="" name="repassword" class="inputxt" recheck="newPwd"
                                   datatype="*4-18" nullmsg="Please confirm the password！"
                                   errormsg="Two entered passwords do not match！"/></td>
                        <td>
                            <div class="Validform_checktip"></div>
                            <div class="info">Please confirm your password<span class="dec"><s class="dec1">&#9670;</s><s
                                    class="dec2">
                                &#9670;</s></span></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <input type="submit" value="submit"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(function() {
        $("input:submit").button();
        $(".changepwd_form").Validform();  //validate use this line;
        var getInfoObj = function() {
            return     $(this).parents("td").next().find(".info");
        }
        $("[datatype]").focusin(
                function() {
                    if (this.timeout) {
                        clearTimeout(this.timeout);
                    }
                    var infoObj = getInfoObj.call(this);
                    if (infoObj.siblings(".Validform_right").length != 0) {
                        return;
                    }
                    infoObj.show().siblings().hide();

                }).focusout(function() {
                    var infoObj = getInfoObj.call(this);
                    if (infoObj.siblings(".Validform_right").length != 0) {
                        return;
                    }
                    this.timeout = setTimeout(function() {
                        infoObj.hide().siblings(".Validform_wrong,.Validform_loading").show();
                    }, 0);

                });
        $(".changepwd_form").Validform({
            tiptype:2,
            usePlugin:{
                passwordstrength:{
                    minLen:4,
                    maxLen:18,
                    trigger:function(obj, error) {
                        if (error) {
                            obj.parent().next().find(".passwordStrength").hide().siblings(".info").show();
                        } else {
                            obj.removeClass("Validform_error").parent().next().find(".passwordStrength").show().siblings().hide();
                        }
                    }
                }
            }
        });
    });
</script>