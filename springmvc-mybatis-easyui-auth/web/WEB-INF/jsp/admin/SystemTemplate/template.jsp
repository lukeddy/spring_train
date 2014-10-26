<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$("#temaplate").markItUp(mySettings); //mySettings
	$('#temaplateSubmit').bind('click',function(){
		$('#temaplateForm').form('submit', {
			url : '/admin/template/save.html',
			success : function(data) {
				var result=eval('('+data+')');
				if (result.success)
					$.messager.alert('info', result.msg, 'info');
				else
					$.messager.alert('info', result.msg, 'error');
			}
		});
	});
</script>
<style>
.markItUp{
   width: 98%;
}
</style>
<div style="padding: 5px;">
	<form id="temaplateForm" method="post">
		<textarea id="temaplate" style="width: 96%" rows="20" name="text" />
		<table>
			<tr>
				<td><label>项目名称</label></td>
				<td><input type="text" name="zipName"/></td>
				<td>
				 <input type="checkbox" name="isAdd" checked="checked"><label> 加上模板表</label>
				</td>
				<td><select name="type">
				 <option value="6" selected="selected">所有模式生成</option>
				 <option value="5" >简洁模式生成</option>
				</select></td>
				<td><label>后缀名</label></td>
				<td><input type="text" name="suffix" value=".do"></td>
				<td>
				<a href="#" id="temaplateSubmit" class="easyui-linkbutton">构建</a>
				</td>
				<td>
				 <a href="javascript:window.open('/download.html?url=AutoCreate/')" id="temaplateSubmit" class="easyui-linkbutton">去下载</a>
				</td>
			</tr>
		</table>
	</form>
</div>


