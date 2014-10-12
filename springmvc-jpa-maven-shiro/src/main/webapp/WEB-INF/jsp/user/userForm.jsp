<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="../common/header.jsp"/>
<div id="page">
    <jsp:include page="../common/nav.jsp"/>
    <h2>添加用户</h2>
    <form:form method="POST" action="${contextPath}/user/addUser" modelAttribute="user">
        <table>
            <c:if test="${not empty message}">
                <tr>
                    <td colspan="2" style="border:1px solid red;font-weight: bold;font-size: 20px;padding:6px;text-align: center;">${message}</td>
                </tr>
            </c:if>
            <tr>
                <td><form:label path="account">Account:</form:label></td>
                <td><form:input path="account"/></td>
            </tr>
            <tr>
                <td><form:label path="nickname">Nick Name:</form:label></td>
                <td><form:input path="nickname"/></td>
            </tr>
            <tr>
                <td><form:label path="password">Password:</form:label></td>
                <td><form:input path="password"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit"/>
                </td>
                <td>
                    <input type="reset" value="Reset"/>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<jsp:include page="../common/footer.jsp"/>