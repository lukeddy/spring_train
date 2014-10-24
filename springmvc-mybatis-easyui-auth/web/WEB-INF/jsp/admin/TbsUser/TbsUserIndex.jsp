<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	//Add and Edit
	function tbsUserGridAddAndEdit(title, url, type) {
		if (type == 1) { //edit
			var rows = $('#tbsUserGrid').datagrid('getSelections');
			if (rows.length != 1) {
				$.messager.alert('消息', '请钩择一行数据!', 'info');
				return;
			}
		}
		$('<div/>').dialog({
			href : '/admin/TbsUser/TbsUserDlg.html',
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
					tbsUserGridSubmit(url);
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
					$('#tbsUserFm').form('clear');
					$('#tbsUserJoinTbsRole').combogrid({
						url : '/admin/TbsRole/listData.html?t='+ Math.random(),
						panelWidth : 460,
						textField : 'name',
						frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
						columns:[ [  
				                {field:'id',title:'主键',width:100},
				                {field:'name',title:'角色',width:100},
				                {field:'text',title:'权限名称',width:100},
				                {field:'createTime',title:'创建时间',width:130}
				        ] ]
					});
				} else {
					var rows = $('#tbsUserGrid').datagrid('getSelections');
					if (rows.length == 1) {
						$('#tbsUserFm').form('load', rows[0]);
						$('#tbsUserJoinTbsRole').combogrid({
							url : '/admin/TbsRole/listData.html?userId='+rows[0].id,
							panelWidth : 460,
							textField : 'name',
							frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
							columns:[ [  
						            {field:'id',title:'主键',width:100},
						            {field:'name',title:'角色',width:100},
						            {field:'text',title:'权限名称',width:100},
						            {field:'createTime',title:'创建时间',width:130}
						    ] ]
						});
					} else {
						$.messager.alert('消息', '请钩择一行数据!', 'info');
					}
				}
			}
		});
	}
	
	//Del
	function tbsUserGridDel() {
		var rows = $('#tbsUserGrid').datagrid('getSelections');
		if (rows.length > 0) {
		    var ids = '';
			for ( var i = 0; i < rows.length; i++) {
						ids += 'ids=' + rows[i].id + '&';
			}
			ids = ids.substring(0, ids.length - 1);
			var url = '/admin/TbsUser/del.html?' + ids;
			$.messager.confirm('Confirm','确定要删除选择的数据吗?', function(r) {
				if (r) {
				    $.get(url, function(result){
						if (result.success){ 
							$('#tbsUserGrid').datagrid('reload');
							$('#tbsUserGrid').datagrid('clearSelections');
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
	function tbsUserGridReload() {
		$('#tbsUserGrid').datagrid('reload',{});
	}
	
	//tbsUserGridSubmit  submit
	function tbsUserGridSubmit(url) {
	    $('#tbsUserFm').form('submit',{  
	        url: url,  
	        onSubmit: function(){  
	            return $(this).form('validate');  
	        },  
	        success: function(result){  
	            var result = eval('('+result+')');  
	            if (result.success){  
	                $('#tbsUserDlg').dialog('close');      // close the dialog 
					$('#tbsUserGrid').datagrid('reload');    // reload the user data
	            } else {  
	            	$.messager.show({ title: 'Error',msg: result.msg }); 
	            }  
	        }  
	    });
	}
	
	//高级搜索 del row
	function tbsUserSearchRemove(curr) {
		if ($(curr).closest('table').find('tr').size() > 2) {
			$(curr).closest('tr').remove();
		} else {
			alert('该行不允许删除');
		}
	}
	
	//高级查询
	function tbsUserSearch() {
		$('<div/>').dialog({
			href : '/admin/TbsUser/TbsUserSearchDlg.html',
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
					$('#tbsUserGrid').datagrid('reload',serializeObjectEx($('#tbsUserSearchFm')));
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
		<table id="tbsUserGrid"  class="easyui-datagrid"  data-options="	
			url:'/admin/TbsUser/listData.html?t='+ Math.random(),
			frozenColumns : [ [ {field : 'ck',checkbox : true}] ],
			columns:[ [  
			{field:'id',title:'主键', formatter: function(value,row,index){
			    if(row.id=='null'){
			    	row.id='';
			    }
			    return row.id;
			}},			
			{field:'username',title:'用户名', formatter: function(value,row,index){
			    if(row.username=='null'){
			    	row.username='';
			    }
			    return row.username;
			}},			
			{field:'password',title:'密码', formatter: function(value,row,index){
			    if(row.password=='null'){
			    	row.password='';
			    }
			    return row.password;
			}},			
			{field:'time',title:'登录时间', formatter: function(value,row,index){
			    if(row.time=='null'){
			    	row.time='';
			    }
			    return row.time;
			}},			
			{field:'ip',title:'ip', formatter: function(value,row,index){
			    if(row.ip=='null'){
			    	row.ip='';
			    }
			    return row.ip;
			}},			
			{field:'count',title:'次数', formatter: function(value,row,index){
			    if(row.count=='null'){
			    	row.count='';
			    }
			    return row.count;
			}},			
			{field:'isLock',title:'锁定', formatter: function(value,row,index){
			    if(row.isLock=='null'){
			    	row.isLock='';
			    }
			    return row.isLock;
			}},			
			{field:'lockTime',title:'锁定时间', formatter: function(value,row,index){
			    if(row.lockTime=='null'){
			    	row.lockTime='';
			    }
			    return row.lockTime;
			}},			
			{field:'failCount',title:'失败次数', formatter: function(value,row,index){
			    if(row.failCount=='null'){
			    	row.failCount='';
			    }
			    return row.failCount;
			}},			
			{field:'isAdmin',title:'管理员', formatter: function(value,row,index){
			    if(row.isAdmin=='null'){
			    	row.isAdmin='';
			    }
			    return row.isAdmin;
			}}			
			] ],
			toolbar:'#tbsUserGridToolbar'
		"/>
		
		<!-- datagrid toolbar -->
		<div id="tbsUserGridToolbar">
			<div style="margin-bottom:5px">
				<a href="#" onclick="javascript:tbsUserGridAddAndEdit('添加  tbsUser','/admin/TbsUser/add.html',0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
				<a href="#" onclick="javascript:tbsUserGridAddAndEdit('修改  tbsUser','/admin/TbsUser/save.html',1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑 </a>  
				<a href="#" onclick="javascript:tbsUserGridDel()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除</a>
				<a href="#" onclick="javascript:tbsUserGridReload()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导出</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">导入</a>
				<%-- 
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">后退</a>
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">前进</a>
				--%>
				<!-- tbsUserGridToolbarSearch -->
				<input class="easyui-searchbox" data-options="
					menu :'#tbsUserGridToolbarSearch',
					prompt :'模糊查询',
					searcher : function(value,name){
					var str='{'+name+':\''+value+'\'}';
		            var obj = eval('('+str+')');
		            //console.dir(obj);
					$('#tbsUserGrid').datagrid('reload',obj);
					}
				"/>
				<div id="tbsUserGridToolbarSearch">
					<div name="id">主键</div>
					<div name="username">用户名</div>
					<div name="password">密码</div>
					<div name="time">登录时间</div>
					<div name="ip">ip</div>
					<div name="count">次数</div>
					<div name="isLock">锁定</div>
					<div name="lockTime">锁定时间</div>
					<div name="failCount">失败次数</div>
					<div name="isAdmin">管理员</div>
				</div>
				<a href="#" onclick="javascript:tbsUserSearch()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
			</div>
		</div>
	</div>
<!--  <div>-->