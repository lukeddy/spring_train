<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 高级查询  tbsUserSearchDlg-->
<div id="tbsUserSearchDlg">
	<form id="tbsUserSearchFm" method="post">
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
						<option value="id">主键</option>
						<option value="username">用户名</option>
						<option value="password">密码</option>
						<option value="time">登录时间</option>
						<option value="ip">ip</option>
						<option value="count">次数</option>
						<option value="isLock">锁定</option>
						<option value="lockTime">锁定时间</option>
						<option value="failCount">失败次数</option>
						<option value="isAdmin">管理员</option>
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
					<td><input name="searchVals" size="18"> <a href="#">日期框</a> <a href="#" onclick="tbsUserSearchRemove(this)">删除</a>
				</td>
			</tr>
		</table>
	</form>
</div>
