<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 高级查询  tbsHttpRequestSearchDlg-->
<div id="tbsHttpRequestSearchDlg">
	<form id="tbsHttpRequestSearchFm" method="post">
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
						<option value="id">序列号</option>
						<option value="name">名称</option>
						<option value="header">请求头信息(JSON格式)</option>
						<option value="firstPage">所有分类地址</option>
						<option value="encoding">编码</option>
						<option value="listName">列表过滤</option>
						<option value="regexs">jquery表达式</option>
						<option value="arrtSplit">属性</option>
						<option value="inserts">入库语句</option>
						<option value="listUrlRegex">请求属性</option>
						<option value="listUrlTest">测试行数</option>
						<option value="listPageId">开始页</option>
						<option value="listSplitUrlChar">分割符</option>
						<option value="listSplitUrl">分页表达式</option>
						<option value="listRegexs">jquery表达式</option>
						<option value="listAttrSplit">属性</option>
						<option value="listInserts">入库语句</option>
						<option value="contUrlRegex">URL表达式</option>
						<option value="contUrlTest">测试Url</option>
						<option value="contRegexs">选择器</option>
						<option value="contAttrSplit">熟悉</option>
						<option value="contInserts">入库表达式</option>
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
					<td><input name="searchVals" size="18"> <a href="#">日期框</a> <a href="#" onclick="tbsHttpRequestSearchRemove(this)">删除</a>
				</td>
			</tr>
		</table>
	</form>
</div>
