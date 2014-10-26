<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>欢迎使用CMS606后台管理系统</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<!-- easyui-->
<script type="text/javascript" src="/admin/common/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/admin/common/js/easyui/jquery.easyui.min.js"></script>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="/admin/common/js/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/admin/common/js/easyui/themes/icon.css" />
<!-- common-->
<script type="text/javascript" src="/admin/common/js/util.js"></script>
<!-- cookie插件 -->
<script type="text/javascript" src="/admin/common/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/admin/common/js/jquery.form.js"></script>
<!-- markitup 插件 -->
<script type="text/javascript" src="/admin/common/js/markitup/jquery.markitup.js"></script>
<script type="text/javascript" src="/admin/common/js/markitup/sets/default/set.js"></script>
<link rel="stylesheet" type="text/css" href="/admin/common/js/markitup/skins/markitup/style.css" />
<link rel="stylesheet" type="text/css" href="/admin/common/js/markitup/sets/default/style.css" />
<script type="text/javascript">
	//tabs
	function tabs(title, url) {
		if ($('#tab').tabs('exists', title)) {
			$('#tab').tabs('select', title);
		} else {
			$('#tab').tabs('add', {
				border : false,
				title : title,
				href : url,
				closable : true
			});
		}
	}
	function exit() {
		$.messager.confirm('Confirm', '确定要退出吗?', function(r) {
			if (r) {
				window.location.href = "/admin/exit.html?t=" + Math.random();
			}
		});
	}
</script>
</head>
<body id="body" class="easyui-layout">

	<!-- 上北  start-->
	<div data-options="region:'north'" style="height: 30px;overflow: visible;">
		<div style="float: left;padding: 5px;">
		  <font size="4">咿呀网开源-模板权限菜单数据管理系统-使用技术EasyUI+SpringMVC+Mybatis+Mysql  QQ交流群  22160972 82260139</font>
		</div>
		<div style="float: right;">
		   <strong style="color: red;">${sessionScope.tbsUserBean.username} 欢迎光临!</strong>  
		   <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_Menu_0'">更换皮肤</a> 
		    <div id="layout_north_Menu_0">
				<div>black</div>
				<div>bootstrap</div>
				<div>default</div>
				<div>gray</div>
				<div>metro</div>
				<div>cupertino</div>
			</div>
			<a href="javascript:exit();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'">退出</a>
		</div>
	</div>

	<!-- 左西 start -->
	<div data-options="region:'west'" style="width: 200px;" title="功能导航">
		<div class="easyui-accordion" data-options="fit : true,border : false">
			<c:forEach var="menu" items="${menus}">
				<c:choose>
					<c:when test="${menu.type==1}">
						<div title="${menu.name}"
							data-options="
			                 isonCls:'icon-save',
			                 tools:[{
			                     iconCls : 'icon-reload',
			                     handler : function() {
			                        $('#tree').tree('reload');
			                     }
			                 },{
			                    iconCls : 'icon-redo',
			                    handler : function() {
			                       var node = $('#tree').tree('getSelected');
			                       if(node){
			                          $('#tree').tree('expandAll', node.target);
			                       }else{
			                          $('#tree').tree('expandAll');
			                       }
			                    }
			                 },{
			                    iconCls : 'icon-undo',
			                    handler : function() {
				                    var node = $('#tree').tree('getSelected');
				                    if (node) {
				                      $('#tree').tree('collapseAll', node.target);
				                    }else{
				                      $('#tree').tree('collapseAll');
				                    }
			                    }
			                 }]
			             ">
			             	<ul id="tree" class="easyui-tree" data-options="
								url:'${menu.url}',
								onSelect : function(node) {
									var type = node.attributes.type;
									if (type == 'folder') {
										return;
									}
									var src = node.attributes.url;
									var args = node.attributes.path;
									var text = node.attributes.text;
									var id = node.id;
									var url = src + '?path=' + args + '&id=' + id + '&t=' + Math.random();
									tabs(text, url);
								}
							" />
			             </div>
					</c:when>
					<c:otherwise>
						<div title="${menu.name}">
							<c:forEach var="tbsMenuBean" items="${menu.childs}">
								<ul style="margin:0px; padding:0px;">
									<li style="line-height: 160%;text-decoration: none;list-style-type:none;" onmouseover="this.style.backgroundColor='#E0ECFF'" onmouseout="this.style.backgroundColor='white'"><a onclick="tabs('${tbsMenuBean.name}','${tbsMenuBean.url}')" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'" style="cursor: pointer;display:block;color:black;text-decoration: none;border-bottom :1px dotted #D8D8D8;padding:3px;padding-left: 5px;">${tbsMenuBean.name}</a></li>
								</ul>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>


	<!-- 中心 start-->
	<div data-options="region:'center'" title="欢迎使用CMS606管理系统" style="overflow:hidden;">
		<div id="tab" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="welcome" style="padding:20px;overflow:hidden;">
				<div style="margin-top:20px;">
					<h3>Welcome to system</h3>
				</div>
			</div>
		</div>
	</div>


	<!-- 右东 start -->
	<!-- <div data-options="region:'east',split:true" style="width:180px;">east</div> -->
	<!--     <div data-options="region:'east',iconCls:'icon-reload',split:true"
		title="Tree Menu" style="width:180px;">
		<ul class="easyui-tree" data-options="url:'tree_data.json'"></ul>
	</div> -->

	<!-- 下南 start-->
	<div data-options="region:'south'" style="overflow:hidden;">
		<!-- class="panel-header panel-header-noborder" -->
		<div class="panel-header panel-header-noborder" style="text-align: center;">
			版权所有 @ <a href="mailto:405645010@qq.com">WOLF</a>
		</div>
	</div>

</body>
</html>