<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbsHttpRequestDlg -->
<div id="tbsHttpRequestDlg">
	<form id="tbsHttpRequestFm" method="post">
		<table>
			<tr>
				<td><label> 序列号: </label></td>
				<td><input name="id" class="easyui-validatebox" readonly="readonly" /></td>

				<td><label> 名称: </label></td>
				<td><input name="name" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 请求头信息(JSON格式): </label></td>
				<td><input name="header" class="easyui-validatebox" /></td>

				<td><label> 所有分类地址: </label></td>
				<td><input name="firstPage" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 编码: </label></td>
				<td><input name="encoding" class="easyui-validatebox" /></td>

				<td><label> 列表过滤: </label></td>
				<td><input name="listName" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> jquery表达式: </label></td>
				<td><input name="regexs" class="easyui-validatebox" /></td>

				<td><label> 属性: </label></td>
				<td><input name="arrtSplit" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 入库语句: </label></td>
				<td><input name="inserts" class="easyui-validatebox" /></td>

				<td><label> 请求属性: </label></td>
				<td><input name="listUrlRegex" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 测试行数: </label></td>
				<td><input name="listUrlTest" class="easyui-validatebox" /></td>

				<td><label> 开始页: </label></td>
				<td><input name="listPageId" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 分割符: </label></td>
				<td><input name="listSplitUrlChar" class="easyui-validatebox" /></td>

				<td><label> 分页表达式: </label></td>
				<td><input name="listSplitUrl" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> jquery表达式: </label></td>
				<td><input name="listRegexs" class="easyui-validatebox" /></td>

				<td><label> 属性: </label></td>
				<td><input name="listAttrSplit" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 入库语句: </label></td>
				<td><input name="listInserts" class="easyui-validatebox" /></td>

				<td><label> URL表达式: </label></td>
				<td><input name="contUrlRegex" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 测试Url: </label></td>
				<td><input name="contUrlTest" class="easyui-validatebox" /></td>

				<td><label> 选择器: </label></td>
				<td><input name="contRegexs" class="easyui-validatebox" /></td>

	 		</tr>
	        <tr>
				<td><label> 熟悉: </label></td>
				<td><input name="contAttrSplit" class="easyui-validatebox" /></td>

				<td><label> 入库表达式: </label></td>
				<td><input name="contInserts" class="easyui-validatebox" /></td>

			</tr>

		</table>
	</form>
</div>