<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
   $(document).ready(function() {
		$(".markItUp").markItUp(mySettings); //mySettings
   });
   function httpClientIndexTest1(type){
      var params
      if(type==1){
         params = $('#httpClientForm input:lt(4),textarea:lt(1)').fieldSerialize();
	  }else if(type==2){
         params = $('#httpClientForm input:lt(6)').fieldSerialize();	
         params = params+"&"+$('#httpClientForm textarea:lt(2)').fieldSerialize();
	  }else if(type==3){
	     params = $('#httpClientForm input:lt(6)').fieldSerialize();	
         params = params+"&"+$('#httpClientForm textarea:lt(3)').fieldSerialize();
	  }
	  params=params+"&type="+type+"&t="+Math.random();
	  alert(params);
      var url='/admin/TbsHttpRequest/http.html?'+params;
      httpTestDialog(url); 
   }
   function  httpTestDialog(url){
        $.get(url,function(data){
               $("#httpget").val(data);
               $('#dialog').show().dialog({
			            height : 500,
			            width : 900,
						//content : '<textarea class="markItUp" >'+data+'</textarea>',
						cache: true,
						resizable:true,
						modal : true,
						title : '采集设置'
				});
         });
   }
</script>
<div id="dialog" style="display: none;"><textarea id="httpget" style="height: 450px;" class="markItUp"></textarea></div>
<!--Dlg AddAndEdit -->
<div id="tabs" class="easyui-tabs" data-options="fit : true,border : false">
	<div title="步骤一" style="padding:20px;">
		<form id="httpClientForm" method="get">
			<table>
				<tr>
					<td><label> 主键: </label></td>
					<td><input name="id"  size="60" readonly="readonly" value="1202162026386115967"/></td>
				</tr>
				<tr>
					<td><label> 任务名称: </label></td>
					<td><input name="name"  size="60" value="采集咿呀网数据实例"/></td>
				</tr>
				<tr>
					<td><label> 请求头信息: </label></td>
					<td><textarea name="header" class="markItUp" style="height: 100px;width: 380px;" >{"User-Agent":"Mozilla/5.0 (Windows NT 5.1; rv:7.0) Gecko/20100101 Firefox/7.0","Content-Type":"application/x-www-form-urlencoded"}</textarea></td>
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
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(1)">抓取测试</a>
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
					<td><textarea name="regexs" style="height: 100px;" class="markItUp">html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a</textarea>
					<td><label> 使用 jquery 选择器  匹配所有分类的DIV。 </label></td>
				</tr>
				<tr>
					<td><label>属性: </label></td>
					<td><input name="arrtSplit" size="60" value="href,text"/></td>
					<td>多个用逗号进行分割 href,id,target,text,var </td>
				</tr>
				<tr>
                  <td colspan="2" align="center">
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(2)">规则测试 </a>
                  </td>
                </tr>
                <tr>
					<td><label> 入库 表达式 : </label></td>
					<td><textarea name="insert" style="height: 100px;" class="markItUp">INSERT INTO TbsHttpResponse(href,text) VALUES({0,1},'{0,1},{1,1},{1,2});</textarea>
					<td><label> 入库。 </label></td>
				</tr>
				<tr>
                  <td colspan="2" align="center">
                     <a href="#" class="easyui-linkbutton" onclick="httpClientIndexTest1(3)">入库测试 </a>
                  </td>
                </tr>
			</table>
		</form>
	</div>
	<div title="所有分类采集" style="padding:20px;overflow:hidden;">
	       <table>
				<tr>
					<td><label> regex : </label></td>
					<td><textarea name="regex" rows="4" cols="50"></textarea>
					<td><label> 使用 regex 匹配所有分类的DIV。与xpath 二选一 </label></td>
					</td>
				</tr>
				<tr>
					<td><label> xpath : </label></td>
					<td><textarea name="regex" rows="4" cols="50"></textarea>
					<td><label> 使用 xpath 匹配所有分类的DIV。与regex 二选一 </label></td>
					<td></td>
				</tr>
			</table>
	</div>

</div>
