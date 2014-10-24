/*设为首页*/
function SetHome(obj,vrl){
	try{
		obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
	}catch(e){
		if(window.netscape){
			try{
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");  
			}catch (e){
				alert('抱歉！您的浏览器不支持直接设为首页。请在浏览器地址栏输入"about:config"并回车然后将[signed.applets.codebase_principal_support]设置为"true"，点击"加入收藏"后忽略安全提示，即可设置成功。');  
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage',vrl);
		}
	}
}
/*加入收藏*/
function AddFavorite(sURL,sTitle){
	try{
		window.external.addFavorite(sURL,sTitle);
	}catch(e){
		try{window.sidebar.addPanel(sTitle,sURL,"");
		}catch(e){
			alert("加入收藏失败，请使用Ctrl+D进行添加");
		}
	}
}
function $(id){return document.getElementById(id);}
/*搜索脚本*/
function setselect(sval,ihtml){
	$("selectvalue").innerHTML=ihtml;
	$("searchtype").value=sval;
}
function sodis(){
	if($("options").style.display=="none"){
		$("options").style.display="block";
	}else{
		$("options").style.display="none";
	}
}
function showso(){$("options").style.display="block";}
function hideso(){$("options").style.display="none";}
/*设定导航*/
function setnav(navid){
	var navul=document.getElementById("nav");
	var navli=navul.getElementsByTagName("li");
	for(var i=0;i<navli.length;i++){
		navli[i].className='';
	}
	navli[navid].className='current';
}
/*计算评分*/
function average(score,digg){
	if(score ==0 || digg == 0){
		document.write("0");
	}else{
		document.write((score/digg).toFixed(1));
	}
	//return score/digg;
}
/*分页跳转*/
function getPageGoUrl(maxPage,surl){
	var str,goUrl
	var url=location.href
	pageNum=document.getElementById("pageinput").value;
	if (pageNum.length==0||isNaN(pageNum)){alert('输入页码非法');return false;}
	if(pageNum>maxPage){pageNum=maxPage;}
	pageNum=pageNum<1 || pageNum==1 ? '' : pageNum;
	location.href=surl.replace('<page>',pageNum).replace('-.','.').replace('_.','.').replace(/\?\.\w+/i,'');
}
