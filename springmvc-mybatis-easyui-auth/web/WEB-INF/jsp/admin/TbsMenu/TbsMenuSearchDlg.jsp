<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 高级查询  tbsMenuSearchDlg-->
<div id="tbsMenuSearchDlg">
	<form id="tbsMenuSearchFm" method="post">
		<table>
			<tr>
				<th>条件</th>
				<th>字段名</th>
				<th>条件</th>
				<th>值</th>
			</tr>
			<tr>
				<td>
					<select name="searchAnds">
						<option value="&&"></option>
						<option value="||">或者</option>
						<option value="&&">并且</option>
					</select>
				</td>
				<td>
					<select name="searchColumnNames">
						<option value="id"></option>
						<option value="id">唯一</option>
						<option value="parentId">父节点</option>
						<option value="name">名称</option>
						<option value="isMenu">菜单类型</option>
						<option value="type">系统类型</option>
						<option value="sortNumber">排序</option>
						<option value="url">地址</option>
						<option value="status">状态</option>
						<option value="createTime">时间</option>
					</select>
				</td>
				<td>
					<select name="searchConditions">
						<option value="="></option>
						<option value="=">等于</option>
						<option value="<>">大于小于</option>
						<option value="<">小于</option>
						<option value=">">大于</option>
						<option value="Like">模糊</option>
					</select>
				</td>
					<td><input name="searchVals" size="18"> <a href="#">日期框</a> <a href="#" onclick="tbsMenuSearchRemove(this)">删除</a>
				</td>
			</tr>
		</table>
	</form>
</div>
