<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//Add and Edit
	function tbsRoleGridAddAndEdit(title, url, type) {
		if (type == 1) { //edit
			var rows = $('#tbsRoleGrid').datagrid('getSelections');
			if (rows.length != 1) {
				$.messager.alert('消息', '请钩择一行数据!', 'info');
				return;
			}
		}
		$('<div/>').dialog({
			href : '/admin/TbsRole/TbsRoleDlg.html',
			modal : true,
			title : title,
			top : '15%',
			left : '30%',
			width : 600,
			resizable:true,
			buttons : [ {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					tbsRoleGridSubmit(url);
					$(this).closest('.window-body').dialog('destroy');
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				if (type == 0) {
					$('#tbsRoleFm').form('clear');
					$('#roleAuthTree').combotree({url:'/admin/TbsMenu/comboxTree.html?t='+ Math.random()});
				} else {
					var rows = $('#tbsRoleGrid').datagrid('getSelections');
					if (rows.length == 1) {
						$('#tbsRoleFm').form('load', rows[0]);
						$('#roleAuthTree').combotree({ url:'/admin/TbsMenu/comboxTree.html?roleId='+rows[0].id });
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function tbsRoleGridDel() {
		var rows = $('#tbsRoleGrid').datagrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '/admin/TbsRole/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#tbsRoleGrid').datagrid('reload');
							$('#tbsRoleGrid').datagrid('clearSelections');
						} else {  
						    $.messager.show({ title: 'Error', msg : result.msg }); 
						} 
				    }, 'json');
				}
			});
		} else {
			$.messager.alert('消息', '请选择要删除的数据!','info');
		}
	}
	
	//Reload
	function tbsRoleGridReload() {
		$('#tbsRoleGrid').datagrid('reload',{});
	}
	
	//tbsRoleGridSubmit  submit
	function tbsRoleGridSubmit(url) {
	    $('#tbsRoleFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#tbsRoleDlg').dialog('close');      // close the dialog 
					$('#tbsRoleGrid').datagrid('reload');    // reload the user data
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function tbsRoleSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function tbsRoleSearch() {
		$('<div/>').dialog({
			href : '/admin/TbsRole/TbsRoleSearchDlg.html',
			modal : true,
			title : '高级查询',
			top : 120,
			width : 480,
			buttons : [ {
				text : '增加一行',
				iconCls : 'icon-add',
				handler : function() {
					var currObj = $(this).closest('.panel').find('table');
					currObj.find('tr:last').clone().appendTo(currObj);
					//currObj.find('tr:last td *[disabled]').removeAttr("disabled");
				}
			}, {
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					$('#tbsRoleGrid').datagrid('reload',serializeObjectEx($('#tbsRoleSearchFm')));
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$(this).closest('.window-body').dialog('destroy');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
</script>
    
	<!-- 中  datagrid-->
    <div data-options="region:'center',border : false">
		<!-- datagrid toolbar -->
		<table id="tbsRoleGrid"  class="easyui-datagrid"  data-options="	
			url:'/admin/TbsRole/listData.html?t='+ Math.random(),
			frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
			columns:[ [  
			{field:'id',title:'主键', formatter: function(value,row,index){
			    if(row.id=='null'){
			    	row.id='';
			    }
			    return row.id;
			}},			
			{field:'name',title:'角色', formatter: function(value,row,index){
			    if(row.name=='null'){
			    	row.name='';
			    }
			    return row.name;
			}},			
			{field:'text',title:'权限名称', formatter: function(value,row,index){
			    if(row.text=='null'){
			    	row.text='';
			    }
			    return row.text;
			}},			
			{field:'createTime',title:'创建时间', formatter: function(value,row,index){
			    if(row.createTime=='null'){
			    	row.createTime='';
			    }
			    return row.createTime;
			}}			
			] ],
			toolbar:'#tbsRoleGridToolbar'
		"/>
		
		<!-- datagrid toolbar -->
		<div id="tbsRoleGridToolbar">
			<div style="margin-bottom:5px">
				<a href="#" onclick="javascript:tbsRoleGridAddAndEdit('添加  tbsRole','/admin/TbsRole/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="#" onclick="javascript:tbsRoleGridAddAndEdit('修改  tbsRole','/admin/TbsRole/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="#" onclick="javascript:tbsRoleGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="#" onclick="javascript:tbsRoleGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				<%-- 
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- tbsRoleGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#tbsRoleGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
					var str='{'+name+':\''+value+'\'}';
		            var obj = eval('('+str+')');
		            //console.dir(obj);
					$('#tbsRoleGrid').datagrid('reload',obj);
					}
				"/>
				<div id="tbsRoleGridToolbarSearch">
					<div name="id">主键</div>
					<div name="name">角色</div>
					<div name="text">权限名称</div>
					<div name="createTime">创建时间</div>
				</div>
				<a href="#" onclick="javascript:tbsRoleSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->