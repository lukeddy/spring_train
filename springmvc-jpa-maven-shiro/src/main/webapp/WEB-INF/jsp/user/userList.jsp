<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="../common/header.jsp"/>
<div id="page">
    <jsp:include page="../common/nav.jsp"/>
    <div id="wrapper">
        <h2>用户列表</h2>
        <table style="border:1px solid #ff6347;background: #ffffe0;">
            <c:if test="${not empty userList}">
                <c:forEach items="${userList}" var="user">
                    <tr style="border: 1px dotted #ff6347;">
                        <td>${user.account}</td>
                        <td>${user.nickname}</td>
                        <td>${user.password}</td>

                            <shiro:hasPermission name="query_permission">
                                <td><a href="${contextPath}/user/profile/${user.id}">查看</a></td>
                            </shiro:hasPermission>
                        <shiro:hasRole name="admin">
                            <shiro:hasPermission name="update_permission">
                                <td><a href="${contextPath}/user/updateForm/${user.id}">修改</a></td>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="del_permission">
                                <td><a href="${contextPath}/user/del/${user.id}">删除</a></td>
                            </shiro:hasPermission>
                        </shiro:hasRole>
                    </tr>
                </c:forEach>
            </c:if>
        </table>

        <h3>分页显示</h3>

        <c:if test="${not empty page}">
            <table style="border:1px solid #ff6347;background: #ffffe0;">
                <c:forEach items="${page.recordList}" var="user">
                    <tr style="border: 1px dotted #ff6347;">
                        <td>${user.account}</td>
                        <td>${user.nickname}</td>
                        <td>${user.password}</td>
                        <td><a href="${contextPath}/user/profile/${user.id}">查看</a></td>
                        <td><a href="${contextPath}/user/updateForm/${user.id}">修改</a></td>
                        <td><a href="${contextPath}/user/del/${user.id}">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
            <p>共${page.pageCount}页,
                当前页:<span style="font-weight: bold;">${page.currentPage}</span>,
                每页显示:${page.pageSize}<br/>
                <c:forEach var="pageNum" begin="1" end="${page.pageCount}">
                    <a href="${contextPath}/user/list/${pageNum}">${pageNum}</a>
                </c:forEach>
            </p>
        </c:if>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
