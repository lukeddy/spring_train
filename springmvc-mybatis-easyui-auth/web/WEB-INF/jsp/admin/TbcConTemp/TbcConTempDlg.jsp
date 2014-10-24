<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbcConTempDlg -->
<div id="tbcConTempDlg">
	<form id="tbcConTempFm" method="post">
		<table>
			<tr>
				<td><label> id: </label></td>
				<td><input name="id" class="easyui-validatebox" readonly="readonly" /></td>

				<td><label> type: </label></td>
				<td><input name="type" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> title: </label></td>
				<td><input name="title" class="easyui-validatebox" /></td>

				<td><label> text: </label></td>
				<td><input name="text" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> image: </label></td>
				<td><input name="image" class="easyui-validatebox" /></td>

			</tr>

		</table>
	</form>
</div>