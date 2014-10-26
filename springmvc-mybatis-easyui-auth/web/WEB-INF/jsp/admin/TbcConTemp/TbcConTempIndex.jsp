<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//Add and Edit
	function tbcConTempGridAddAndEdit(title, url, type) {
		if (type == 1) { //edit
			var rows = $('#tbcConTempGrid').datagrid('getSelections');
			if (rows.length != 1) {
				$.messager.alert('消息', '请钩择一行数据!', 'info');
				return;
			}
		}
		$('<div/>').dialog({
			href : '/admin/TbcConTemp/TbcConTempDlg.html',
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
					tbcConTempGridSubmit(url);
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
					$('#tbcConTempFm').form('clear');
				} else {
					var rows = $('#tbcConTempGrid').datagrid('getSelections');
					if (rows.length == 1) {
						$('#tbcConTempFm').form('load', rows[0]);
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function tbcConTempGridDel() {
		var rows = $('#tbcConTempGrid').datagrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '/admin/TbcConTemp/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#tbcConTempGrid').datagrid('reload');
							$('#tbcConTempGrid').datagrid('clearSelections');
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
	function tbcConTempGridReload() {
		$('#tbcConTempGrid').datagrid('reload',{});
	}
	
	//tbcConTempGridSubmit  submit
	function tbcConTempGridSubmit(url) {
	    $('#tbcConTempFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#tbcConTempDlg').dialog('close');      // close the dialog 
					$('#tbcConTempGrid').datagrid('reload');    // reload the user data
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function tbcConTempSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function tbcConTempSearch() {
		$('<div/>').dialog({
			href : '/admin/TbcConTemp/TbcConTempSearchDlg.html',
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
					$('#tbcConTempGrid').datagrid('reload',serializeObjectEx($('#tbcConTempSearchFm')));
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
		<table id="tbcConTempGrid"  class="easyui-datagrid"  data-options="	
			url:'/admin/TbcConTemp/listData.html?t='+ Math.random(),
			frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
			columns:[ [  
			{field:'id',title:'id', formatter: function(value,row,index){
			    if(row.id=='null'){
			    	row.id='';
			    }
			    return row.id;
			}},			
			{field:'type',title:'type', formatter: function(value,row,index){
			    if(row.type=='null'){
			    	row.type='';
			    }
			    return row.type;
			}},			
			{field:'title',title:'title', formatter: function(value,row,index){
			    if(row.title=='null'){
			    	row.title='';
			    }
			    return row.title;
			}},			
			{field:'text',title:'text', formatter: function(value,row,index){
			    if(row.text=='null'){
			    	row.text='';
			    }
			    return row.text;
			}},			
			{field:'image',title:'image', formatter: function(value,row,index){
			    if(row.image=='null'){
			    	row.image='';
			    }
			    return row.image;
			}}			
			] ],
			toolbar:'#tbcConTempGridToolbar'
		"/>
		
		<!-- datagrid toolbar -->
		<div id="tbcConTempGridToolbar">
			<div style="margin-bottom:5px">
				<a href="#" onclick="javascript:tbcConTempGridAddAndEdit('添加  tbcConTemp','/admin/TbcConTemp/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="#" onclick="javascript:tbcConTempGridAddAndEdit('修改  tbcConTemp','/admin/TbcConTemp/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="#" onclick="javascript:tbcConTempGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="#" onclick="javascript:tbcConTempGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				<%-- 
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- tbcConTempGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#tbcConTempGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
					var str='{'+name+':\''+value+'\'}';
		            var obj = eval('('+str+')');
		            //console.dir(obj);
					$('#tbcConTempGrid').datagrid('reload',obj);
					}
				"/>
				<div id="tbcConTempGridToolbarSearch">
					<div name="id">id</div>
					<div name="type">type</div>
					<div name="title">title</div>
					<div name="text">text</div>
					<div name="image">image</div>
				</div>
				<a href="#" onclick="javascript:tbcConTempSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->