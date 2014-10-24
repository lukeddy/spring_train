<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
   $(document).ready(function() {
		$(".markItUp").markItUp(mySettings); //mySettings
		//$(".markItUp").css({width:'98%'});
		//$(".markItUp markItUpEditor").css({width:'88.8%'});
   });
   function httpClientIndexTest1(type,title){
      var queryString = $('#httpClientForm').formSerialize();
      queryString+="&"+$('#httpClientForm1').formSerialize()
      queryString+="&"+$('#httpClientForm2').formSerialize()
      var url = '/admin/TbsHttpRequest/http.html?type='+type+'&t='+Math.random();
      //alert(queryString);
      $.post(url, queryString,function(data){
           $('<div/>').dialog({
			            height : $(window).height()-150,
			            width : $(window).width()-300,
			            href : '/admin/TbsHttpRequest/httpDialog.html?t='+Math.random(),
			           // content : data,
						cache: false,
						resizable:true,
						modal : true,
						title : title,
						onClose : function() {
							$(this).dialog('destroy');
						},
						onLoad : function(){
						     $('#httpMarkItUp').val(data);
						}
			  });
      }); 
   }
</script>
<style>
 .markItUp{width: 98%;}
</style>
</style>
<div id="dialog" style="display: none;"><textarea id="httpget" style="height: 450px;" class="markItUp"></textarea></div>
<!--Dlg AddAndEdit -->
<div id="tabs" class="easyui-tabs" data-options="fit : true,border : false">
	<div title="导航页" style="padding:10px;">
	   <form id="httpClientForm" method="post">
			<table>
				<tr>
					<td><label> 主键: </label></td>
					<td><input name="id"  size="60" readonly="readonly" value=""/></td>
				</tr>
				<tr>
					<td><label> 任务名称: </label></td>
					<td><input name="name"  size="60" value="采集咿呀网数据实例"/></td>
				</tr>
				<tr>
					<td><label> 请求头信息: </label></td>
					<td><textarea name="header" class="markItUp" style="height: 100px;width: 88.8%;" cols="52" rows="20" >{"User-Agent":"Mozilla/5.0 (Windows NT 5.1; rv:7.0) Gecko/20100101 Firefox/7.0","Content-Type":"application/x-www-form-urlencoded"}</textarea></td>
					<td><label>必须使用JSON格式 </label></td>
				</tr>
				<tr>
					<td><label> 所有分类地址: </label></td>
					<td><input name="firstPage" size="60" value="http://www.yy606.com/about/siteMap.shtml"/></td>
				</tr>
				<tr>
					<td><label>编码: </label></td>
					<td><input name="encoding" size="60" value="UTF-8"/></td>
					<td>对返回数据进行编码</td>
				</tr>
				<tr>
                  <td colspan="2" align="center">
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(1,'抓取测试')">抓取测试</a>
                  </td>
                  <td>抓取的源码将会转换成XML格式 </td>
                </tr>
				<tr>
				<td><label> 过滤 : </label></td>
					<td><input name="listName" size="60" value="*"/></td>
					<td>*代表所有 指定采集参照以下格式如: 儿童笑话|民间笑话</td>
				</tr>
				<tr>
					<td><label> jquery 表达式 : </label></td>
					<td><textarea name="regexs" class="markItUp" style="height: 100px;width: 88.8%;" cols="52" rows="20">html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a</textarea>
					<td><label> 使用 jquery 选择器, 支持多条需用|分隔 </label></td>
				</tr>
				<tr>
					<td><label>属性: </label></td>
					<td><input name="arrtSplit" size="60" value="href,text"/></td>
					<td>多个用逗号进行分割 src,href,text,html... 组用|分隔。 特殊标签ownText,outerHtml,document</td>
				</tr>
				<tr>
                  <td colspan="2" align="center">
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(2,'规则测试')">规则测试 </a>
                  </td>
                </tr>
                <tr>
					<td><label> 入库 表达式 : </label></td>
					<td><textarea name="inserts" class="markItUp" style="height: 100px;width: 88.8%;" cols="52" rows="20">INSERT INTO TbsHttpResponse(href,text) VALUES({0-0},{0-1});</textarea>
					<td><label> 示例： INSERT INTO TbcTemp(id,href,text) VALUES([id],{0-0},{0-1});支持[id] 多条用|分隔 </label></td>
				</tr>
				<tr>
                  <td colspan="2" align="center">
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(3,'查看SQL')">查看SQL </a>
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(4,'入库')">入库 </a>
                  </td>
                </tr>
			</table>
		</form>
	</div>
	<div title="列表页" style="padding:10px;">
	    <form id="httpClientForm1" method="post">
	      <table>
	            <tr>
				  <td><lable>导航页地址:</lable></td>
				  <td colspan="2">
				     <input name="listUrlRegex" value="{0-0}"/>
				     <label>必须为URL地址,导航页url属性表达式</label>
				     <a style="margin-left: 10px;" href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(5,'抓取测试')">抓取测试</a>
				  </td>
				</tr>
				<tr>
                  <td>请求第:</td>
                  <td colspan="2">
                       <label><input name="listUrlTest" type="text"  value="1"  />条</label>
                  </td>
                </tr>
                <tr>
                  <td>开始页:</td>
                  <td colspan="2">
                       <label><input name="listPageId" type="text"  value="1"  /></label>
                       <label>结束页:<input name="pageSize" type="text"  value="*"  /></label>
                  </td>
                </tr>
                <tr>
                  <td>地址分割符:</td>
                  <td colspan="2">
                       <label><input name="listSplitUrlChar" type="text"  value="/" size="1" /></label>
                       <label>把请求的URL用指定的分隔符进行 str.split(url) 动态组装取值 方式 0,1,2..</label>
                  </td>
                </tr>
                <tr>
                  <td>动态组装:</td>
                  <td colspan="2">
                       <label><input name="listSplitUrl" type="text"  value="0+/+/+1+2+/+3+/+4+/+(*)" /></label>
                       <label>例   0+/+/+1+2+/+3+/+4+/+(*) 必须使用+进行连接(*)将被替换成页码</label>
                  </td>
                </tr>
                <tr>
                  <td></td>
                  <td colspan="2">
                        <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(6,'查看地址测试')">查看地址测试</a>
                        <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(7,'抓取页面测试')">抓取页面测试</a>
                  </td>
                </tr>
               <tr>
					<td><label> jquery 表达式 : </label></td>
					<td><textarea name="listRegexs" class="markItUp" style="height: 100px;width: 88.8%;" cols="52" rows="20">html>body>div[class=container]>div[class=index_left04 fl]>div[class=list_ulli border]>div[class=list_l_01]>div[class=list_l_04]>div[class=fl]>a|html>body>div[class=container]>div[class=index_left04 fl]>div[class=list_ulli border]>div[class=list_l_01]>div[class=list_l_04]>div[class=fl]>a>span</textarea>
					<td><label> 使用 jquery 选择器, 支持多条需用|分隔 </label></td>
				</tr>
				<tr>
					<td><label>属性: </label></td>
					<td><input name="listAttrSplit" size="60" value="href,text|text"/></td>
					<td>多个用逗号进行分割 src,href,text,html... 组用|分隔。 特殊标签ownText,outerHtml,document</td>
				</tr>
				<tr>
                  <td colspan="2" align="center">
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(8,'规则测试')">规则测试 </a>
                  </td>
                </tr>
                <tr>
					<td><label> 入库 表达式 : </label></td>
					<td><textarea name="listInserts" class="markItUp" style="height: 100px;width: 88.8%;" cols="52" rows="20">INSERT INTO TbcListTemp(id,href,text) VALUES([id],{0-0},{0-1});|INSERT INTO TbcListTemp(id,text) VALUES([id],{1-0});</textarea>
					<td><label> 示例： INSERT INTO TbcTemp(id,href,text) VALUES([id],{0-0},{0-1});支持[id] 多条用|分隔 </label></td>
					<!-- INSERT INTO TbcListTemp(id,href,type,text) VALUES([id],{0-0},{0-1},{1-0}); -->
				</tr>
				<tr>
                  <td colspan="2" align="center">
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(9,'查看SQL')">查看SQL </a>
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(18,'入库')">入库 </a>
                  </td>
                </tr>
		  </table>
		</form>
	</div>
	<div title="内容页" style="padding:10px;">
	   <form id="httpClientForm2" method="post">
	      <table>
	           <tr>
				  <td><lable>列表页地址:</lable></td>
				  <td colspan="2">
				     <input name="contUrlRegex" value="{0-0}"/>
				     <label>必须为URL地址,导航页url属性表达式</label>
				     <a style="margin-left: 10px;" href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(10,'抓取测试')">抓取测试</a>
				  </td>
				</tr>
				<tr>
                  <td>请求第:</td>
                  <td colspan="2">
                       <label><input name="contUrlTest" type="text"  value="0"  />条</label>
                       <a style="margin-left: 10px;" href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(11,'抓取测试')">抓取测试</a>
                  </td>
                </tr>
                <tr>
					<td><label> jquery 表达式 : </label></td>
					<td><textarea name="contRegexs" class="markItUp" style="height: 100px;width: 88.8%;" cols="52" rows="20">html>body>div.container>div.index_left04>div.index_left07a>span.fl>a:eq(2)|html>body>div.container>div.index_left04>div.border>div.news_dt06>h2>strong|html>body>div.container>div.index_left04>div.border>div.news_dt06>p:eq(4)</textarea>
					<td><label> 使用 jquery 选择器, 支持多条需用|分隔 </label></td>
					<!-- text|text|text|src (类型,标题,内容,图片) -->
					<!-- html>body>div.container>div.index_left04>div.index_left07a>span.fl>a:eq(2)|html>body>div.container>div.index_left04>div.border>div.news_dt06>h2>strong|html>body>div.container>div.index_left04>div.border>div.news_dt06>p:eq(4)|html>body>div.container>div.index_left04>div.border>div.news_dt06>center>div>img -->
				    <!-- INSERT INTO TbcConTemp(id,TYPE,title,image,TEXT) VALUES([id],{0-0},{1-0},{2-0},{3-0});-->
				</tr>
				<tr>
					<td><label>属性: </label></td>
					<td><input name="contAttrSplit" size="60" value="href,text"/></td>
					<td>多个用逗号进行分割 src,href,text,html... 组用|分隔。 特殊标签ownText,outerHtml,document</td>
				</tr>
				<tr>
                  <td colspan="2" align="center">
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(12,'规则测试')">规则测试 </a>
                  </td>
                </tr>
                <tr>
					<td><label> 入库 表达式 : </label></td>
					<td><textarea name="contInserts" class="markItUp" style="height: 100px;width: 88.8%;" cols="52" rows="20">INSERT INTO TbcListTemp(id,href,text) VALUES([id],{0-0},{0-1});|INSERT INTO TbcTemp(id,href,text) VALUES([id],{0-0},{1-0});</textarea>
					<td><label> 示例： INSERT INTO TbcTemp(id,href,text) VALUES([id],{0-0},{0-1});支持[id] 多条用|分隔 </label></td>
				</tr>
				<tr>
                  <td colspan="2" align="center">
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(13,'查看SQL')">查看SQL </a>
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(14,'入库')">入库 </a>
                  </td>
                </tr>
		  </table>
		</form>
	</div>
	<div title="抓取" style="padding:10px;">
	   <form id="httpClientForm3" method="post">
	      <table>
	          <tr>
				 <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(15,'开始抓取')">开始抓取</a>
			  </tr>
	      </table>
	   </form>
	</div>
</div>
