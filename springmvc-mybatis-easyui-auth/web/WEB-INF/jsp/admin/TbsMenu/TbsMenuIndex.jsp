<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//Add and Edit
	function tbsMenuGridAddAndEdit(title, url, type) {
		if (type == 1) { //edit
			var rows = $('#tbsMenuGrid').datagrid('getSelections');
			if (rows.length != 1) {
				$.messager.alert('消息', '请钩择一行数据!', 'info');
				return;
			}
		}
		$('<div/>').dialog({
			href : '/admin/TbsMenu/TbsMenuDlg.html',
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
					tbsMenuGridSubmit(url);
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
				$('#tbsMenuComboxTree').combotree({multiple:false,url:'/admin/TbsMenu/comboxTree.html?t='+ Math.random()});
				if (type == 0) {
					$('#tbsMenuFm').form('clear');
				} else {
					var rows = $('#tbsMenuGrid').datagrid('getSelections');
					if (rows.length == 1) {
						$('#tbsMenuFm').form('load', rows[0]);
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function tbsMenuGridDel() {
		var rows = $('#tbsMenuGrid').datagrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '/admin/TbsMenu/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#tbsMenuGrid').treegrid('reload');
							$('#tbsMenuGrid').datagrid('clearSelections');
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
	function tbsMenuGridReload() {
		$('#tbsMenuGrid').treegrid('reload');
	}
	
	//tbsMenuGridSubmit  submit
	function tbsMenuGridSubmit(url) {
	    $('#tbsMenuFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#tbsMenuDlg').dialog('close');      // close the dialog 
	            	$('#tbsMenuGrid').treegrid('reload');
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function tbsMenuSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function tbsMenuSearch() {
		$('<div/>').dialog({
			href : '/admin/TbsMenu/TbsMenuSearchDlg.html',
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
					$('#tbsMenuGrid').datagrid('reload',serializeObjectEx($('#tbsMenuSearchFm')));
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
		<table id="tbsMenuGrid"  class="easyui-treegrid"  data-options="
			url:'/admin/TbsMenu/gridTree.html?t='+ Math.random(),
			columns:[ [  
			{field:'id',title:'唯一', formatter: function(value,row,index){
			    if(row.id=='null'){
			    	row.id='';
			    }
			    return row.id;
			}},			
			{field:'parentId',title:'父节点', formatter: function(value,row,index){
			    if(row.parentId=='null'){
			    	row.parentId='';
			    }
			    return row.parentId;
			}},			
			{field:'name',title:'名称', formatter: function(value,row,index){
			    if(row.name=='null'){
			    	row.name='';
			    }
			    return row.name;
			}},			
			{field:'isMenu',title:'菜单类型', formatter: function(value,row,index){
			    if(row.isMenu=='null'){
			    	row.isMenu='';
			    }
			    return row.isMenu;
			}},			
			{field:'type',title:'系统类型', formatter: function(value,row,index){
			    if(row.type=='null'){
			    	row.type='';
			    }
			    return row.type;
			}},			
			{field:'sortNumber',title:'排序', formatter: function(value,row,index){
			    if(row.sortNumber=='null'){
			    	row.sortNumber='';
			    }
			    return row.sortNumber;
			}},			
			{field:'url',title:'地址', formatter: function(value,row,index){
			    if(row.url=='null'){
			    	row.url='';
			    }
			    return row.url;
			}},			
			{field:'status',title:'状态', formatter: function(value,row,index){
			    if(row.status=='null'){
			    	row.status='';
			    }
			    return row.status;
			}},			
			{field:'createTime',title:'时间', formatter: function(value,row,index){
			    if(row.createTime=='null'){
			    	row.createTime='';
			    }
			    return row.createTime;
			}}			
			] ],
			toolbar:'#tbsMenuGridToolbar'
		"/>
		
		<!-- datagrid toolbar -->
		<div id="tbsMenuGridToolbar">
			<div style="margin-bottom:5px">
				<a href="#" onclick="javascript:tbsMenuGridAddAndEdit('添加  tbsMenu','/admin/TbsMenu/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="#" onclick="javascript:tbsMenuGridAddAndEdit('修改  tbsMenu','/admin/TbsMenu/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="#" onclick="javascript:tbsMenuGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="#" onclick="javascript:tbsMenuGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				<%-- 
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- tbsMenuGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#tbsMenuGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
					$.get('/admin/TbsMenu/gridTree.html',{name:name,value:value},function(data){
						    $('#tbsMenuGrid').treegrid('loadData',data);
					},'json');
					}
				"/>
				<div id="tbsMenuGridToolbarSearch">
					<div name="id">唯一</div>
					<div name="parentId">父节点</div>
					<div name="name">名称</div>
					<div name="isMenu">菜单类型</div>
					<div name="type">系统类型</div>
					<div name="sortNumber">排序</div>
					<div name="url">地址</div>
					<div name="status">状态</div>
					<div name="createTime">时间</div>
				</div>
				<a href="#" onclick="javascript:tbsMenuSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->