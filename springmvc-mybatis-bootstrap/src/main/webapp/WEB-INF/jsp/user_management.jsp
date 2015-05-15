<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="common/header_content.jsp"/>
</head>
<body>
<jsp:include page="common/page_header.jsp"/>

<div class="container">
    <div class="main-wrapper">
        <div class="page-header">
            <h1>
                用户管理
            </h1>
        </div>
        <c:if test="${not empty suc_msg}">
            <p class="text-center alert alert-success">${suc_msg}</p>
        </c:if>
        <c:if test="${not empty fail_msg}">
            <p class="text-center alert alert-danger">${fail_msg}</p>
        </c:if>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">用户列表</div>
                <div class="panel-body" style="max-height: 300px;overflow-y:scroll;">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>角色</th>
                                    <th>上一次登录时间</th>
                                    <th>可用状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${not empty userList}">
                                    <c:forEach items="${userList}" var="c">
                                        <tr>
                                            <td>
                                                    ${c.id}
                                            </td>
                                            <td>
                                                    ${c.username}
                                            </td>
                                            <td>
                                                <c:if test="${c.lockState==0}">
                                                    可用
                                                </c:if>
                                                <c:if test="${c.lockState==1}">
                                                    不可用
                                                </c:if>
                                            </td>
                                            <td>
                                                <c:if test="${c.role==1}">
                                                    超级管理员
                                                </c:if>
                                                <c:if test="${c.role==2}">
                                                    开发人员
                                                </c:if>
                                                <c:if test="${c.role==3}">
                                                    测试人员
                                                </c:if>
                                                <c:if test="${c.role==4}">
                                                    项目管理人员
                                                </c:if>
                                            </td>
                                            <td>${c.lastLoginTimeFormated}</td>
                                            <td align="center">
                                                <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                                                        <%--<form name="vUserListForm" id="vUserListForm"  action="" method="post"></form>--%>
                                                    <a class="blue changeUserPwd" href="javascript:void(0)" data-id="${c.id}"
                                                       data-username="${c.username}"
                                                       data-href="${contextPath}/sys/changePwd?id=${c.id}" title="修改密码">
                                                        修改密码
                                                    </a>
                                                    <c:if test="${c.lockState==0}">
                                                        <a class="blue deleteSingleContest"
                                                           href="${contextPath}/sys/changeLockState?id=${c.id}&lockState=1"
                                                           title="锁定账户">
                                                            锁定帐号
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${c.lockState==1}">
                                                        <a class="blue deleteSingleContest"
                                                           href="${contextPath}/sys/changeLockState?id=${c.id}&lockState=0"
                                                           title="解锁账户">
                                                            解锁帐号
                                                        </a>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr/>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">添加用户</div>
                <div class="panel-body">
                    <div class="col-md-6">
                        <form role="form" name="addUserForm" id="addUserForm" action="${contextPath}/sys/addUser" method="post">
                            <div class="form-group col-md-12">
                                <label for="role">角色:</label>
                                <select required name="role" id="role" class="form-control">
                                    <option value="">--选择角色--</option>
                                    <option value="4">项目管理人员</option>
                                    <option value="3">测试人员</option>
                                    <option value="2">开发人员</option>
                                    <option value="1">超级管理员</option>
                                </select>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="username">用户名:</label>
                                <input required type="text" name="username" class="form-control" placeholder="" id="username"/>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="password">密  码:</label>
                                <input required  type="password" name="pwd" id="password" class="form-control" value="" placeholder=""/>
                            </div>
                            <div class="form-group col-md-12">
                                <button type="submit" class="btn btn-primary" id="btnSubmitForm">添加</button>
                                <input  type="reset" class="btn" value="重置"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div id="modalBox">
            <!--修改系统用户密码弹出框-->
            <div class="modal fade" id="dialogChangePwd">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h6 class="modal-title">
                                修改用户"<b id="currUserName"></b>"的密码
                            </h6>
                        </div>
                        <form role="form" name="changePwdForm" id="changePwdForm" action="${contextPath}/sys/changePwd"
                              method="post">
                            <div class="modal-body" id="modal-body">
                                <input type="hidden" name="id" value="" id="currId"/>

                                <div class="form-group">
                                    <div>新密码:</div>
                                    <p>
                                        <input required type="password" name="newPwd" id="newPwd" class="form-control"
                                               value="" placeholder=""/>
                                    </p>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <fieldset id="changePwdFormField">
                                    <div class="form-group">
                                        <div class="btn-group">
                                            <button type="submit" class="btn btn-primary" id="btnChangePwd">修改</button>
                                            <button type="button" class="btn btn-default" id="btnChangePwdCancel"
                                                    data-dismiss="modal">取消
                                            </button>
                                        </div>

                                    </div>
                                </fieldset>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/page_footer.jsp"/>
<script type="text/javascript">
    $(function () {
        //初始化表单验证
        window.ParsleyValidator.setLocale('zh_cn');
        $('#changePwdForm').parsley();
        $('#addUserForm').parsley();

        //修改密码弹出框
        $('.changeUserPwd').click(function () {
            var currId = $(this).attr('data-id');
            var currUserName = $(this).attr('data-username');
            $('#dialogChangePwd').find('#currId').val(currId);
            $('#dialogChangePwd').find('#currUserName').html(currUserName);
            $('#dialogChangePwd').modal("show");
        });
    });
</script>
</body>
</html>

