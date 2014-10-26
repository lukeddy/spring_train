<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
	$("#${id}").markItUp(mySettings); //mySettings
	$('#${id}btn').bind('click', function() {
		$('#${id}myForm').form('submit', {
			success : function(data) {
				if (data == 'y')
					$.messager.alert('info', '操作成功', 'info');
				else
					$.messager.alert('info', '操作失败', 'error');
			}
		});
	});
</script>
<style>
.markItUp{
   width: 98%;
}
</style>
<div style="padding: 3px;">
	<a id="${id}btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a> 
	<form id="${id}myForm" action="/admin/saveFile.html?path=${path}" method="post">
		<textarea id="${id}" style="width: 96%" rows="20" name="textarea">${str}</textarea>
	</form>
</div>
