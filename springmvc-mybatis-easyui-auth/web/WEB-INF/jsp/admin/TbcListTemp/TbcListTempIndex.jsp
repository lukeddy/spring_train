<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//Add and Edit
	function tbcListTempGridAddAndEdit(title, url, type) {
		if (type == 1) { //edit
			var rows = $('#tbcListTempGrid').datagrid('getSelections');
			if (rows.length != 1) {
				$.messager.alert('消息', '请钩择一行数据!', 'info');
				return;
			}
		}
		$('<div/>').dialog({
			href : '/admin/TbcListTemp/TbcListTempDlg.html',
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
					tbcListTempGridSubmit(url);
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
					$('#tbcListTempFm').form('clear');
				} else {
					var rows = $('#tbcListTempGrid').datagrid('getSelections');
					if (rows.length == 1) {
						$('#tbcListTempFm').form('load', rows[0]);
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function tbcListTempGridDel() {
		var rows = $('#tbcListTempGrid').datagrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '/admin/TbcListTemp/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#tbcListTempGrid').datagrid('reload');
							$('#tbcListTempGrid').datagrid('clearSelections');
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
	function tbcListTempGridReload() {
		$('#tbcListTempGrid').datagrid('reload',{});
	}
	
	//tbcListTempGridSubmit  submit
	function tbcListTempGridSubmit(url) {
	    $('#tbcListTempFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#tbcListTempDlg').dialog('close');      // close the dialog 
					$('#tbcListTempGrid').datagrid('reload');    // reload the user data
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function tbcListTempSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function tbcListTempSearch() {
		$('<div/>').dialog({
			href : '/admin/TbcListTemp/TbcListTempSearchDlg.html',
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
					$('#tbcListTempGrid').datagrid('reload',serializeObjectEx($('#tbcListTempSearchFm')));
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
		<table id="tbcListTempGrid"  class="easyui-datagrid"  data-options="	
			url:'/admin/TbcListTemp/listData.html?t='+ Math.random(),
			frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
			columns:[ [  
			{field:'id',title:'id', formatter: function(value,row,index){
			    if(row.id=='null'){
			    	row.id='';
			    }
			    return row.id;
			}},			
			{field:'href',title:'href', formatter: function(value,row,index){
			    if(row.href=='null'){
			    	row.href='';
			    }
			    return row.href;
			}},			
			{field:'type',title:'type', formatter: function(value,row,index){
			    if(row.type=='null'){
			    	row.type='';
			    }
			    return row.type;
			}},			
			{field:'text',title:'text', formatter: function(value,row,index){
			    if(row.text=='null'){
			    	row.text='';
			    }
			    return row.text;
			}}			
			] ],
			toolbar:'#tbcListTempGridToolbar'
		"/>
		
		<!-- datagrid toolbar -->
		<div id="tbcListTempGridToolbar">
			<div style="margin-bottom:5px">
				<a href="#" onclick="javascript:tbcListTempGridAddAndEdit('添加  tbcListTemp','/admin/TbcListTemp/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="#" onclick="javascript:tbcListTempGridAddAndEdit('修改  tbcListTemp','/admin/TbcListTemp/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="#" onclick="javascript:tbcListTempGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="#" onclick="javascript:tbcListTempGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				<%-- 
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- tbcListTempGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#tbcListTempGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
					var str='{'+name+':\''+value+'\'}';
		            var obj = eval('('+str+')');
		            //console.dir(obj);
					$('#tbcListTempGrid').datagrid('reload',obj);
					}
				"/>
				<div id="tbcListTempGridToolbarSearch">
					<div name="id">id</div>
					<div name="href">href</div>
					<div name="type">type</div>
					<div name="text">text</div>
				</div>
				<a href="#" onclick="javascript:tbcListTempSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->