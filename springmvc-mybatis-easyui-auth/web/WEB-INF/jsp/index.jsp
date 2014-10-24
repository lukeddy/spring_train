<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>开源模板系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="/common/assets/css/bootstrap.css" rel="stylesheet">
<link href="/common/assets/css/bootstrap-responsive.css" rel="stylesheet">
<link href="/common/assets/css/docs.css" rel="stylesheet">
<link href="/common/assets/js/google-code-prettify/prettify.css" rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="/common/assets/js/html5shiv.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="/common/assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="/common/assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="/common/assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="/common/assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="/common/assets/ico/favicon.png">
</head>

<body style="padding-top: 10px;" data-spy="scroll" data-target=".bs-docs-sidebar">
	<!-- Navbar -->
	<!--  
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="brand" href="#">源码下载</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class=""><a href="#">简介</a></li>
						<li class="active"><a href="#">框架</a></li>
						<li class=""><a href="#">模板</a></li>
						<li class=""><a href="#">依赖</a></li>
						<li class=""><a href="#">演示</a></li>
						<li class=""><a href="#">生成</a></li>
						<li class=""><a href="#">自定义</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	-->
	<!-- Subhead -->
	
	<div class="container">
		<header class="jumbotron subhead" id="overview" style="padding-top: -10px;">
			<h2>开源模板系统</h2>
				<p class="lead">
				    开源模板是由咿呀网 WOLF 开发 模板功能包括 权限管理树 菜单管理树 自定义数据采集  代码生成 源码编辑 增加 删除 修改 按列查询 高级查询 导入 导出 上传 下载...等功能  主框架技术 easyUi+springMvc+mybatis+mysql 等
				</p>
		</header>
	</div>

	<div class="container">
		<!-- Docs nav -->
		<div class="row">
			<div class="span3 bs-docs-sidebar">
				<ul class="nav nav-list bs-docs-sidenav">
					<li><a href="#download-bootstrap"><i class="icon-chevron-right"></i>项目下载</a></li>
					<li><a href="#demo-dir"><i class="icon-chevron-right"></i>目录结构</a></li>
					<li><a href="#dmeo-run"><i class="icon-chevron-right"></i>项目运行</a></li>
					<li><a href="#contents"><i class="icon-chevron-right"></i>功能说明</a></li>
					<li><a href="#html-template"><i class="icon-chevron-right"></i>贡献源码</a></li>
					<!-- <li><a href="#examples"><i class="icon-chevron-right"></i> 关于我们</a></li> -->
				</ul>
			</div>
			<div class="span9">

				<!-- Download -->
				<section id="download-bootstrap" style="padding-top: 0px;">
					<div class="page-header">
						<h3>1. 项目下载</h3>
					</div>
					<p class="lead">目前为1.0版  官方qq群  22160972  82260139 </p>
					<p>该项目是由 <a href="javascript:window.open('http://www.yy606.com')">www.yy606.com</a> <a href="javascript:window.open('www.mn606.com')">www.mn606.com</a>  参与人员参与人员: linux架构师 肖财高、 系统架构师 罗泽林、J2EE工程师 邓亚云 武兴华、安卓工程师 刘剑、UI设计师  邹燕</p>
					<p>
						你可以通过访问 <a href="javascript:window.open('http://demo.cms606.com/download.html')">http://demo.cms606.com/download.html</a> 进行选择下载, 你也可以通过 <a href="javascript:window.open('http://demo.cms606.com/admin/login.html')">登录</a> ->模板管理->模板生成 进行自定义生成下载 <br> 项目源文件 <a href="http://demo.cms606.com/downloadFile.html?url=cms606-wolf-lib.tar.gz">cms606-wolf-demo-1.0.tar.gz</a> <br> 项目sql文件 <a href="http://demo.cms606.com/downloadFile.html?url=cms606-wolf-lib.tar.gz">cms606-wolf-1.0.sql</a> <br> 项目lib库 <a href="http://demo.cms606.com/downloadFile.html?url=cms606-wolf-lib.tar.gz">cms606-wolf-lib-1.0.tar.gz</a> <br>
					</p>
					<p>演示地址    <a href="javascript:window.open('http://demo.cms606.com/admin/login.html')">http://demo.cms606.com/admin/login.html</a></p>
					<p align="right">405645010@qq.com</p>
				</section>
				
				<section id="demo-dir" style="padding-top: 0px;">
					<div class="page-header">
						<h3>2. 目录结构</h3>
					</div>
<pre class="prettyprint">
  demo/
  	src/
  		bean/
  			model
  		controller/
  			admin
  				...
  			view
  		dao/
  			mapper
  		service
  		util/
  			browser
  			core
  			spring/
  				interceptor
  			verify
  		test
  		dataSourceConfig.properties
  		remark.txt
  		spring-common.xml
  		spring-servlet.xml
  		log4j.properties
  	WebRoot/
  		admin
  		common
  		WEB-INF/
  			jsp/
  				admin/
  					index.jsp
  					login.jsp
  					...
  				index.jsp
  			lib/
  				*.jar
</pre>
				</section>

				<!-- File structure -->
				<section id="dmeo-run" style="padding-top: 0px;">
					<div class="page-header">
						<h3>3.项目运行</h3>
					</div>
					<p>1、把下载的 lib 目录放到 WebRoot\WEB-INF\ 下</p>
					<p>2、把下载的 SQL文件导入到 Mysql数据库</p>
					<p>3、找到 src 目录下面的 dataSourceConfig.properties 配置文件 修改</p>
					<pre>
dbcp.driverClassName=com.mysql.jdbc.Driver
dbcp.url=jdbc:mysql://127.0.0.1:3306/****** 数据库名称
dbcp.username=****** 用户名
dbcp.password=****** 密码
					</pre>
					<p>4、配置本地域名  打开  C:\Windows\System32\drivers\etc\hosts 最下面添加 </p>
					<pre>
127.0.0.1         test.com
					</pre>
					<p>5、配置tomcat域名访问  找到 如  D:\apache-tomcat-7.0.39\conf\server.xml 添加或修改 </p>
					<pre>
&lt;Host name="test.com"  appBase="webapps" unpackWARs="true" autoDeploy="true"&gt;
	&lt;Context path="" docBase="D:\apache-tomcat-7.0.39\webapps\demo"  reloadable="true" crossContext="true" /&gt; 
&lt;/Host&gt;
					</pre> 
					<p>6、部署完毕 启动  tomcat 打开 http://test.com:8080/admin/login.html</p>
				</section>

				<!-- Contents -->
				<section id="contents" style="padding-top: 0px;">
					<div class="page-header">
						<h3>4. 功能说明</h3>
					</div>
					<p class="lead">
					  这里只是简单的说明  详请<a href="http://demo.cms606.com/admin/login.html">登录</a>查看
					</p>
					<h4>系统管理</h4>
					<strong>菜单管理</strong>
					<p>
						添加菜单 可以选择是功能还是菜单,如果选择添加菜单 刷新页面后 会看到多了一个菜单 如果是功能不会被显示
						添加功能 你需要给功能设置一个 URL地址  在菜单导航里面可以直接被调用   菜单只有2级 多了的无效 
						暂实现功能 有 添加 删除 编辑 刷新 查询 高级查询   具体以demo为主
					</p>
					<strong>角色管理</strong>
					<p>
					    你可以给这个角色进行授权  授权来自于菜单树   角色跟菜单是多对多关系,如果你不是admin账户 你访问的所以地址
					   都必须要有权限，  用户关联角色  角色关联菜单 用户跟角色多对多关系  角色跟菜单多对多,角色控制菜单是否显示  这样不同的用户登录显示的菜单就不一样 ,如果用户未授权登录是没有菜单的  也没有其他可访问的权限
					   给主菜单下的当个菜单授权了 其他菜单不会显示 只有授权的菜单才会呈现  未授权的功能不能被调用 
					   注意 角色不是用户不能登录的
					</p>
					<strong>用户管理</strong>
					<p>
					      你可以添加一个登录用户  注意  isAdmin属性为0 是超级管理管  不会被拦截器拦截  他可以访问所有,
					  isAdmin属性不为0的都会被拦截器验证  不为0的 你需要关系角色并且角色是关联过菜单的  否则登录不会有菜单
					</p>
					<strong>源码编辑</strong>
					<p>
					   	源码编辑 是 WebRoot下面的一棵树  可以编辑JSP页面以及class下面的配置文件等  过滤掉了.class.jar等不可编辑的文件
					   	有upload 文件夹 download文件夹 用户自定义生成文件在 download文件夹下面的AutoCreate文件夹下面  生成过后可以去查看生成的java源码文件
					</p>
					<strong>采集管理</strong>
					<p>
					       自定义数据采集  支持 任意规范的 导航页  列表 内容页 数据采集  可直接入库 由于设计是自定义 所以你需要建表  也需要你提供jsoup表达式 
					        由 httpclient4.x jsoup实现   对 httpclient进行了封装   jsoup进行解析  上面有个现成的例子 你可以测试    
					</p>
					<strong>数据管理</strong>
					<p>
					     数据管理   主要实现操作   增加 删除 修改 查询 刷新 高级查询  导入 导出 等功能   业务扩展如果新增加了一张表或多张表可以导出你的sql结构  <a href="http://demo.cms606.com/admin/login.html">登录</a> 模板系统来给你生成
						这些通用功能包括jsp页面, 粘贴你的sql到textarea里面  你可以选择不加模板表  选择简洁模式生成  点击生成 即可  把生成的文件下载下来  放到相应的目录  
					   然后在系统管理->菜单管理->tree树里面找到数据管理添加一个菜单 设置地址为/admin/表名/*.*(index.html) 即可 添加完成  刷新页面就完成了这些模板功能  
					</p>
				</section>

				<!-- HTML template -->
				<section id="html-template" style="padding-top: 0px;">
					<div class="page-header">
						<h3>5. 贡献源码</h3>
					</div>
					<p class="lead">
						如果你有比较好的源码你可以通过这里上传 代码尽量规则  要有说明 以便我们更快的集成到模板   我们会在代码里面注释由××提供 我们感谢你的提供!
					</p>
					<p>邮件 405645010@qq.com</p>
				</section>



				<!-- Examples -->
				<!-- 
				<section id="examples" style="padding-top: 0px;">
					<div class="page-header">
						<h3>6. 关于我们</h3>
					</div>
					<p class="lead">
					网站简介: 开源模板系统是由深资J2EE技术人员创建
					参与人员: linux架构师 肖财高、 系统架构师 罗泽林、J2EE工程师 邓亚云 武兴华、安卓工程师 刘剑、UI设计师  邹燕。
					开源项目: 我们正在组建一套J2EE开源系统 框架技术为SpringMVC+Mybaties apache等其他开源框架组建而成 敬请期待！
					</p>
					 
					<ul class="thumbnails bootstrap-examples">
						<li class="span3"><a class="thumbnail" href="examples/starter-template.html"> <img src="/common/assets/img/examples/bootstrap-example-starter.png" alt=""> </a>
							<h4>Starter template</h4>
							<p>A barebones HTML document with all the Bootstrap CSS and JavaScript included.</p></li>
						<li class="span3"><a class="thumbnail" href="examples/hero.html"> <img src="/common/assets/img/examples/bootstrap-example-marketing.png" alt=""> </a>
							<h4>Basic marketing site</h4>
							<p>Featuring a hero unit for a primary message and three supporting elements.</p></li>
						<li class="span3"><a class="thumbnail" href="examples/fluid.html"> <img src="/common/assets/img/examples/bootstrap-example-fluid.png" alt=""> </a>
							<h4>Fluid layout</h4>
							<p>Uses our new responsive, fluid grid system to create a seamless liquid layout.</p></li>

						<li class="span3"><a class="thumbnail" href="examples/marketing-narrow.html"> <img src="/common/assets/img/examples/bootstrap-example-marketing-narrow.png" alt=""> </a>
							<h4>Narrow marketing</h4>
							<p>Slim, lightweight marketing template for small projects or teams.</p></li>
						<li class="span3"><a class="thumbnail" href="examples/justified-nav.html"> <img src="/common/assets/img/examples/bootstrap-example-justified-nav.png" alt=""> </a>
							<h4>Justified nav</h4>
							<p>Marketing page with equal-width navigation links in a modified navbar.</p></li>
						<li class="span3"><a class="thumbnail" href="examples/signin.html"> <img src="/common/assets/img/examples/bootstrap-example-signin.png" alt=""> </a>
							<h4>Sign in</h4>
							<p>Barebones sign in form with custom, larger form controls and a flexible layout.</p></li>

						<li class="span3"><a class="thumbnail" href="examples/sticky-footer.html"> <img src="/common/assets/img/examples/bootstrap-example-sticky-footer.png" alt=""> </a>
							<h4>Sticky footer</h4>
							<p>Pin a fixed-height footer to the bottom of the user's viewport.</p></li>
						<li class="span3"><a class="thumbnail" href="examples/carousel.html"> <img src="/common/assets/img/examples/bootstrap-example-carousel.png" alt=""> </a>
							<h4>Carousel jumbotron</h4>
							<p>A more interactive riff on the basic marketing site featuring a prominent carousel.</p></li>
					</ul>
				</section>
				 -->
			</div>
		</div>

	</div>

	<!-- Footer -->
	<footer class="footer">
		<div class="container">
			开源编程 湘ICP备1200044-3号 CopyRight©2012-2020, www.cms606.COM,All Rights; QQ：405645010
		</div>
	</footer>

	<!-- Le javascript -->
<!--  	<script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>-->
	<script src="/common/assets/js/jquery.js"></script>
	<script src="/common/assets/js/bootstrap-transition.js"></script>
	<script src="/common/assets/js/bootstrap-alert.js"></script>
	<script src="/common/assets/js/bootstrap-modal.js"></script>
	<script src="/common/assets/js/bootstrap-dropdown.js"></script>
	<script src="/common/assets/js/bootstrap-scrollspy.js"></script>
	<script src="/common/assets/js/bootstrap-tab.js"></script>
	<script src="/common/assets/js/bootstrap-tooltip.js"></script>
	<script src="/common/assets/js/bootstrap-popover.js"></script>
	<script src="/common/assets/js/bootstrap-button.js"></script>
	<script src="/common/assets/js/bootstrap-collapse.js"></script>
	<script src="/common/assets/js/bootstrap-carousel.js"></script>
	<script src="/common/assets/js/bootstrap-typeahead.js"></script>
	<script src="/common/assets/js/bootstrap-affix.js"></script>
	<script src="/common/assets/js/holder/holder.js"></script>
	<script src="/common/assets/js/google-code-prettify/prettify.js"></script>
	<script src="/common/assets/js/application.js"></script>
</body>
</html>
