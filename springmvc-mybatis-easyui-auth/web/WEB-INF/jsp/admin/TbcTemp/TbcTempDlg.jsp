<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbcTempDlg -->
<div id="tbcTempDlg">
	<form id="tbcTempFm" method="post">
		<table>
			<tr>
				<td><label> 序列: </label></td>
				<td><input name="id" class="easyui-validatebox" readonly="readonly" /></td>

				<td><label> 地址: </label></td>
				<td><input name="href" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 名称: </label></td>
				<td><input name="text" class="easyui-validatebox" /></td>

			</tr>

		</table>
	</form>
</div>