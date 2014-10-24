<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//Add and Edit
	function tbsRoleMenuGridAddAndEdit(title, url, type) {
		if (type == 1) { //edit
			var rows = $('#tbsRoleMenuGrid').datagrid('getSelections');
			if (rows.length != 1) {
				$.messager.alert('消息', '请钩择一行数据!', 'info');
				return;
			}
		}
		$('<div/>').dialog({
			href : '/admin/TbsRoleMenu/TbsRoleMenuDlg.html',
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
					tbsRoleMenuGridSubmit(url);
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
					$('#tbsRoleMenuFm').form('clear');
				} else {
					var rows = $('#tbsRoleMenuGrid').datagrid('getSelections');
					if (rows.length == 1) {
						$('#tbsRoleMenuFm').form('load', rows[0]);
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function tbsRoleMenuGridDel() {
		var rows = $('#tbsRoleMenuGrid').datagrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '/admin/TbsRoleMenu/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#tbsRoleMenuGrid').datagrid('reload');
							$('#tbsRoleMenuGrid').datagrid('clearSelections');
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
	function tbsRoleMenuGridReload() {
		$('#tbsRoleMenuGrid').datagrid('reload',{});
	}
	
	//tbsRoleMenuGridSubmit  submit
	function tbsRoleMenuGridSubmit(url) {
	    $('#tbsRoleMenuFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#tbsRoleMenuDlg').dialog('close');      // close the dialog 
					$('#tbsRoleMenuGrid').datagrid('reload');    // reload the user data
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function tbsRoleMenuSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function tbsRoleMenuSearch() {
		$('<div/>').dialog({
			href : '/admin/TbsRoleMenu/TbsRoleMenuSearchDlg.html',
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
					$('#tbsRoleMenuGrid').datagrid('reload',serializeObjectEx($('#tbsRoleMenuSearchFm')));
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
		<table id="tbsRoleMenuGrid"  class="easyui-datagrid"  data-options="	
			url:'/admin/TbsRoleMenu/listData.html?t='+ Math.random(),
			frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
			columns:[ [  
			{field:'id',title:'主键', formatter: function(value,row,index){
			    if(row.id=='null'){
			    	row.id='';
			    }
			    return row.id;
			}},			
			{field:'roleId',title:'角色', formatter: function(value,row,index){
			    if(row.roleId=='null'){
			    	row.roleId='';
			    }
			    return row.roleId;
			}},			
			{field:'menuIdFun',title:'功能', formatter: function(value,row,index){
			    if(row.menuIdFun=='null'){
			    	row.menuIdFun='';
			    }
			    return row.menuIdFun;
			}},			
			{field:'menuId',title:'所属菜单', formatter: function(value,row,index){
			    if(row.menuId=='null'){
			    	row.menuId='';
			    }
			    return row.menuId;
			}}			
			] ],
			toolbar:'#tbsRoleMenuGridToolbar'
		"/>
		
		<!-- datagrid toolbar -->
		<div id="tbsRoleMenuGridToolbar">
			<div style="margin-bottom:5px">
				<a href="#" onclick="javascript:tbsRoleMenuGridAddAndEdit('添加  tbsRoleMenu','/admin/TbsRoleMenu/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="#" onclick="javascript:tbsRoleMenuGridAddAndEdit('修改  tbsRoleMenu','/admin/TbsRoleMenu/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="#" onclick="javascript:tbsRoleMenuGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="#" onclick="javascript:tbsRoleMenuGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				<%-- 
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- tbsRoleMenuGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#tbsRoleMenuGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
					var str='{'+name+':\''+value+'\'}';
		            var obj = eval('('+str+')');
		            //console.dir(obj);
					$('#tbsRoleMenuGrid').datagrid('reload',obj);
					}
				"/>
				<div id="tbsRoleMenuGridToolbarSearch">
					<div name="id">主键</div>
					<div name="roleId">角色</div>
					<div name="menuIdFun">功能</div>
					<div name="menuId">所属菜单</div>
				</div>
				<a href="#" onclick="javascript:tbsRoleMenuSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->