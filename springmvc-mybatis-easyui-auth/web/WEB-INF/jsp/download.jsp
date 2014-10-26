<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>权限菜单数据后台下载</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" href="/common/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="/admin/common/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/common/js/jquery.query.js"></script>
<!-- bootstrap -->
<script type="text/javascript" src="/common/js/bootstrap/js/bootstrap.min.js"></script>

<style>
h1 {
	border-bottom: 1px solid #c0c0c0;
	margin-bottom: 10px;
	padding-bottom: 10px;
	white-space: nowrap;
}

table {
	border-collapse: collapse;
}

tr.header {
	font-weight: bold;
}

td.detailsColumn {
	padding-left: 2em;
	text-align: right;
	white-space: nowrap;
}

a.icon {
	padding-left: 1.5em;
	text-decoration: none;
}

a.icon:hover {
	text-decoration: underline;
}

a.file {
	background: url("/common/images/file.png") left top no-repeat;
}

a.dir {
	background: url("/common/images/dir.png") left top no-repeat;
}

a.up {
	background: url("/common/images/topdir.png") left top no-repeat;
}

#listingParsingErrorBox {
	border: 1px solid black;
	background: #fae691;
	padding: 10px;
	display: none;
}
</style>
<script type="text/javascript">
    download($.query.get('url'));
	function download(url){
		$("table tr:gt(0)").remove();
		if(url==null || url=='null'){
			url="/downloadDir.html";
		}else{
			/*var suffix =/\.[^\.]+/.exec(url);
			if(suffix!=null){
				alert(suffix);
				url="downloadFile.html?path="+url;
				$.get("downloadFile.html",function(data){});
				return;
			}*/
			url="/downloadDir.html?url="+url;
		}
		$.get(url, function(data) {
			var trs;
			for ( var i = 0; i < data.length; i++) {
				if(i==0){
					trs += "<tr>";
						trs += "<td><a class=\"icon up\" href=\"javascript:download('"+data[i].parentDir+"')\">[上级目录]</a></td>";
						trs += "<td class=\"detailsColumn\"></td>";
						trs += "<td class=\"detailsColumn\"></td>";
					trs += "</tr>";
					trs += "<tr>";
				}
				if(data[i].isFile){
					trs += "<td><a class=\"icon file\" href=\"downloadFile.html?url="+data[i].path+"\">" + data[i].name + "</a></td>";
				}else{
					trs += "<td><a class=\"icon dir\" href=\"javascript:download('"+data[i].path+"')\">" + data[i].name + "</a></td>";
				}
					trs += "<td class=\"detailsColumn\">"+data[i].size+"</td>"
					trs += "<td class=\"detailsColumn\">"+data[i].time+"</td>"
				trs += "</tr>";
			}
			$("table").append(trs);
		}, "JSON");
	}
</script>
</head>
<body>
	<div class="row-fluid">
		<div class="spen12">
			<h1 id="header" style="margin-left: 10px;">咿呀网-模板源码下载</h1>
		</div>
		<div class="row" style="margin-left: 5px;">
			<div class="span10">
				<table class="table table-striped table-bordered table-condensed">
					<tr class="header">
						<td>名称</td>
						<td class="detailsColumn">大小</td>
						<td class="detailsColumn">修改日期</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>