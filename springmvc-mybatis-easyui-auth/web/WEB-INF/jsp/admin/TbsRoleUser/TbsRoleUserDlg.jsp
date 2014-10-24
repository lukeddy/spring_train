<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbsRoleUserDlg -->
<div id="tbsRoleUserDlg">
	<form id="tbsRoleUserFm" method="post">
		<table>
			<tr>
				<td><label> 主键: </label></td>
				<td><input name="id" class="easyui-validatebox" readonly="readonly" /></td>

				<td><label> 用户: </label></td>
				<td><input name="userId" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 角色: </label></td>
				<td><input name="roleId" class="easyui-validatebox" /></td>

			</tr>

		</table>
	</form>
</div>