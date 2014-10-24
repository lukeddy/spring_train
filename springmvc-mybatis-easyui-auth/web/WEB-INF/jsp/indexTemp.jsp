<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>智能前台CMS</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link href="common/images/favicon.ico" rel="shortcut icon" />
<link href="common/css/new.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet"
	href="http://www.bbgyy.net/style/css/base.css">
	<link type="text/css" rel="stylesheet"
		href="http://www.bbgyy.net/style/css/home.css">
		<script src="http://www.bbgyy.net/style/js/common.js"
			language="javascript"></script>
</head>
<body>
	<iframe frameborder="0" style="display: none;"></iframe>
	<div id="bdshare"
		style="right: 0px; top: 320px; position: fixed; height: 330px; overflow: hidden;">
		<img style="float:left;margin-top:58px;" alt=""
			src="http://bdimg.share.baidu.com/static/images/r2.gif"><iframe
				frameborder="0" style="position:absolute;display:none;z-index:9999;"
				id="bdsIfr"></iframe>
			<div id="bdshare_l" style="display: none; left: 24px;">
				<div id="bdshare_l_c">
					<h6>分享到</h6>
					<ul>
						<li><a class="bds_mshare mshare" href="#">一键分享</a>
						</li>
						<li><a class="bds_qzone qqkj" href="#">QQ空间</a>
						</li>
						<li><a class="bds_tsina xlwb" href="#">新浪微博</a>
						</li>
						<li><a class="bds_baidu bdsc" href="#">百度搜藏</a>
						</li>
						<li><a class="bds_renren rrw" href="#">人人网</a>
						</li>
						<li><a class="bds_tqq txwb" href="#">腾讯微博</a>
						</li>
						<li><a class="bds_bdxc bdxc" href="#">百度相册</a>
						</li>
						<li><a class="bds_kaixin001 kxw" href="#">开心网</a>
						</li>
						<li><a class="bds_tqf txpy" href="#">腾讯朋友</a>
						</li>
						<li><a class="bds_hi bdkj" href="#">百度空间</a>
						</li>
						<li><a class="bds_douban db" href="#">豆瓣网</a>
						</li>
						<li><a class="bds_tsohu shwb" href="#">搜狐微博</a>
						</li>
						<li><a class="bds_bdhome bdhome" href="#">百度新首页</a>
						</li>
						<li><a class="bds_qq qqsc" href="#">QQ收藏</a>
						</li>
						<li><a class="bds_thx thx" href="#">和讯微博</a>
						</li>
						<li><a class="bds_more" href="#">更多...</a>
						</li>
					</ul>
					<p>
						<a class="goWebsite" href="#">百度分享</a>
					</p>
				</div>
			</div>
	</div>
	<div id="bdshare_s">
		<iframe frameborder="0"
			style="position:absolute;display:none;z-index:9999;" id="bdsIfr"></iframe>
		<div id="bdshare_l" style="display: none;">
			<div id="bdshare_l_c">
				<h6>分享到</h6>
				<ul>
					<li><a class="bds_mshare mshare" href="#">一键分享</a>
					</li>
					<li><a class="bds_qzone qqkj" href="#">QQ空间</a>
					</li>
					<li><a class="bds_tsina xlwb" href="#">新浪微博</a>
					</li>
					<li><a class="bds_baidu bdsc" href="#">百度搜藏</a>
					</li>
					<li><a class="bds_renren rrw" href="#">人人网</a>
					</li>
					<li><a class="bds_tqq txwb" href="#">腾讯微博</a>
					</li>
					<li><a class="bds_bdxc bdxc" href="#">百度相册</a>
					</li>
					<li><a class="bds_kaixin001 kxw" href="#">开心网</a>
					</li>
					<li><a class="bds_tqf txpy" href="#">腾讯朋友</a>
					</li>
					<li><a class="bds_hi bdkj" href="#">百度空间</a>
					</li>
					<li><a class="bds_douban db" href="#">豆瓣网</a>
					</li>
					<li><a class="bds_tsohu shwb" href="#">搜狐微博</a>
					</li>
					<li><a class="bds_bdhome bdhome" href="#">百度新首页</a>
					</li>
					<li><a class="bds_qq qqsc" href="#">QQ收藏</a>
					</li>
					<li><a class="bds_thx thx" href="#">和讯微博</a>
					</li>
					<li><a class="bds_more" href="#">更多...</a>
					</li>
				</ul>
				<p>
					<a class="goWebsite" href="#">百度分享</a>
				</p>
			</div>
		</div>
	</div>
	<!--网站顶部-->
	<div class="top-wrap">
		<div class="welcome">欢迎回到步步高影院，喜欢就请收藏！</div>
		<ul class="toolbar">
			<li class="downdesk"><a href="/desktop.asp">下载到桌面</a>
			</li>
			<li class="topzt"><a href="/zt/index.html">专题</a>
			</li>
			<li class="topdp"><a href="/zt/dapian2012.html">2012大片</a>
			</li>
			<li class="toply"><a href="http://www.bbgyy.net/gbook.asp">留言求片</a>
			</li>
			<li class="toply"><a target="_blank"
				href="http://vip.kuaibo.com/">快播VIP</a>
			</li>
			<li class="topty"><a
				onclick="AddFavorite('http://www.bbgyy.net/?fav','步步高影院-www.bbgyy.net');"
				href="javascript:;">加入收藏</a>
			</li>
			<li class="topty"><a
				onclick="SetHome(this,'http://www.bbgyy.net/?home')"
				href="javascript:;">设为主页</a>
			</li>
		</ul>
	</div>
	<!--网站头部-->
	<div class="hd-wrap">
		<div class="logo">
			<a href="/"><img src="/style/img/logo.gif">
			</a>
		</div>
		<div class="search">
			<form target="_blank" action="http://search.bbgyy.net/search.asp"
				name="search_form">
				<input type="hidden" value="-1" id="searchtype" name="searchtype">
					<!--搜索框-->
					<div class="keyword">
						<input type="text"
							onfocus="if(this.value=='请输入搜索信息') this.value='';"
							onblur="if(this.value=='') this.value='请输入搜索信息';" value="请输入搜索信息"
							id="searchword" name="searchword">
					</div> <!--下拉框-->
					<div onmouseout="hideso();" onclick="sodis();" class="selectwrap">
						<div id="selectvalue">全部</div>
						<div onmouseover="showso();" onmouseout="hideso();"
							style="display:none;overflow:hidden;" id="options">
							<a onclick="setselect('-1','全部');" href="javascript:void(0);">全部</a>
							<a onclick="setselect('0','片名');" href="javascript:void(0);">片名</a>
							<a onclick="setselect('1','演员');" href="javascript:void(0);">演员</a>
							<a onclick="setselect('1','导演');" href="javascript:void(0);">导演</a>
							<a onclick="setselect('2','地区');" href="javascript:void(0);">地区</a>
							<a onclick="setselect('3','年代');" href="javascript:void(0);">年代</a>
						</div>
					</div> <!--搜索按钮-->
					<div class="search_btn">
						<button id="SearchBtn" type="submit"></button>
					</div>
			</form>
		</div>
	</div>
	<!--网站导航-->
	<div class="nav-wrap">
		<div class="nav">
			<ul id="nav">
				<li class="current"><a href="/">首页</a>
				</li>
				<li><a href="/list/1.html">动作片</a>
				</li>
				<li><a href="/list/2.html">喜剧片</a>
				</li>
				<li><a href="/list/3.html">爱情片</a>
				</li>
				<li><a href="/list/4.html">科幻片</a>
				</li>
				<li><a href="/list/5.html">战争片</a>
				</li>
				<li><a href="/list/6.html">恐怖片</a>
				</li>
				<li><a href="/list/7.html">剧情片</a>
				</li>
				<li><a href="/list/8.html">动画片</a>
				</li>
				<li><a href="/list/9.html">大陆剧</a>
				</li>
				<li><a href="/list/10.html">港剧</a>
				</li>
				<li><a href="/list/11.html">台剧</a>
				</li>
				<li><a href="/list/12.html">韩剧</a>
				</li>
				<li><a href="/list/13.html">日剧</a>
				</li>
				<li><a href="/list/14.html">欧美剧</a>
				</li>
				<li><a href="/list/15.html">新马泰剧</a>
				</li>
			</ul>
			<img class="hotpsn" src="/style/img/nav_new.gif">
		</div>
	</div>
	<div class="cl"></div>
	<!--热门专题-->
	<div class="hotzt">
		<ul>
			<li class="zt_tt">热门专题：</li>
			<li><a style="color:#FF0000;" href="/zt/tuijian.html">推荐大片</a>
			</li>
			<li><a href="/zt/dapian2012.html">2012大片</a>
			</li>
			<li><a href="/zt/newmovie.html">新片预告</a>
			</li>
			<li><a href="/zt/jingdian.html">经典系列</a>
			</li>
			<li><a href="/zt/hit_dy.html">电影排行榜</a>
			</li>
			<li><a href="/zt/hit_dsj.html">电视剧排行榜</a>
			</li>
			<li><a style="color:#FF0000;" href="/list/16.html">动漫</a>
			</li>
			<li><a style="color:#0066FF;" href="/list/17.html">综艺</a>
			</li>
			<li><a href="/list/18.html">体育</a>
			</li>
			<li><a href="/list/19.html">纪录</a>
			</li>
			<li><a href="/list/20.html">演唱会</a>
			</li>
			<li><a href="/list/21.html">戏曲</a>
			</li>
			<li id="lastli"><a href="/list/22.html">音乐MV</a>
			</li>
		</ul>
		<img class="hotpsh" src="/style/img/nav_hot.gif">
	</div>

	<!--导航下方广告-->
	<div class="nav_ads">
		<script src="/ads/head_banner.js" language="javascript"></script>
		<script type="text/javascript">
			/*bbgyy.com_头_960*90，创建于2011-4-1*/
			var cpro_id = 'u430962';
		</script>
		<script type="text/javascript"
			src="http://cpro.baidu.com/cpro/ui/c.js"></script>
		<script
			src="http://pos.baidu.com/ecom?di=u430962&amp;tm=BAIDU_CPRO_SETJSONADSLOT&amp;fn=BAIDU_CPRO_SETJSONADSLOT&amp;baidu_id="
			charset="utf-8" type="text/javascript"></script>
		<div style="display:none">-</div>
		<iframe scrolling="no" width="960" height="90" frameborder="0"
			align="center,center" allowtransparency="true" marginheight="0"
			marginwidth="0"
			src="http://cpro.baidu.com/cpro/ui/uijs.php?tu=u430962&amp;tn=text_default_960_90&amp;n=mafang521_cpr&amp;rsi1=90&amp;rsi0=960&amp;rad=&amp;rss0=%23FFFFFF&amp;rss1=%23FFFFFF&amp;rss2=%230000FF&amp;rss3=%23444444&amp;rss4=%23008000&amp;rss5=&amp;rss6=%23e10900&amp;rsi5=4&amp;ts=1&amp;at=6&amp;ch=0&amp;cad=1&amp;aurl=&amp;rss7=&amp;cpa=1&amp;fv=11&amp;cn=1&amp;if=16&amp;word=http%3A%2F%2Fwww.bbgyy.net%2F&amp;refer=&amp;ready=1&amp;jk=5402a5846d953e00&amp;jn=3&amp;lmt=1352440090&amp;csp=1366,768&amp;csn=1366,742&amp;ccd=24&amp;chi=2&amp;cja=false&amp;cpl=26&amp;cmi=40&amp;cce=true&amp;csl=zh-CN&amp;did=1&amp;rt=18&amp;dt=1352445453&amp;ev=67108864&amp;c01=0&amp;prt=1352445453095&amp;i3=f"
			id="cproIframe1"></iframe>
	</div>
	<!--热门专题-->
	<div class="share">
		<div class="bdshare_t bds_tools get-codes-bdshare" id="bdshare">
			<span class="bds_more">分享到：</span> <a class="bds_qzone"
				title="分享到QQ空间" href="#">QQ空间</a> <a class="bds_tsina"
				title="分享到新浪微博" href="#">新浪微博</a> <a class="bds_tqq" title="分享到腾讯微博"
				href="#">腾讯微博</a> <a class="bds_renren" title="分享到人人网" href="#">人人网</a>
			<a class="bds_tqq" title="分享到腾讯微博" href="#">腾讯微博</a> <a
				class="bds_baidu" title="分享到百度搜藏" href="#">百度搜藏</a> <a
				class="bds_tieba" title="分享到百度贴吧" href="#">百度贴吧</a> <a
				class="bds_douban" title="分享到豆瓣网" href="#">豆瓣网</a> <a
				class="bds_tsohu" title="分享到搜狐微博" href="#">搜狐微博</a> <a
				class="bds_hi" title="分享到百度空间" href="#">百度空间</a> <a class="bds_copy"
				title="分享到复制网址" href="#">复制</a>
		</div>
	</div>
	<div class="home-wrap">
		<div class="main">
			<!--推荐大片-->
			<div class="lmain">
				<div class="item">
					<h2>推荐大片</h2>
					<div class="act">
						<a href="/zt/tuijian.html">更多大片&gt;&gt;</a>
					</div>
				</div>
				<ul>
					<li><a target="_blank" class="pic" href="/html/33664.html"><img
							alt="超凡蜘蛛侠/神奇蜘蛛侠/蜘蛛侠前传/蜘蛛侠4"
							src="http://www.hao2018.com/pic/upimg/2012-6/33664.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="超凡蜘蛛侠/神奇蜘蛛侠/蜘蛛侠前传/蜘蛛侠4" href="/html/33664.html">超凡蜘蛛侠/神..</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">720P</span></li>
					<li><a target="_blank" class="pic" href="/html/38864.html"><img
							alt="敢死队2/浴血任务2/轰天猛将2"
							src="http://www.hao2018.com/pic/upimg/2012-9/20129999493082.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="敢死队2/浴血任务2/轰天猛将2" href="/html/38864.html">敢死队2/浴血..</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">HD</span></li>
					<li><a target="_blank" class="pic" href="/html/38160.html"><img
							alt="普罗米修斯/异形前传"
							src="http://www.hao2018.com/pic/upimg/2012-6/38160.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="普罗米修斯/异形前传" href="/html/38160.html">普罗米修斯/异..</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">720P</span></li>
					<li><a target="_blank" class="pic" href="/html/38068.html"><img
							alt="黑衣人3/3D黑超特警组3/MIB星际战警3"
							src="http://www.hao2018.com/pic/upimg/2012-6/38068.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="黑衣人3/3D黑超特警组3/MIB星际战警3" href="/html/38068.html">黑衣人3/3D..</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">720P</span></li>
					<li><a target="_blank" class="pic" href="/html/38017.html"><img
							alt="暂告安全/火线反击"
							src="http://www.hao2018.com/pic/upimg/2012-6/38017.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="暂告安全/火线反击" href="/html/38017.html">暂告安全/火线..</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">HD</span></li>
					<li><a target="_blank" class="pic" href="/html/35607.html"><img
							alt="复仇者联盟"
							src="http://www.hao2018.com/pic/upimg/2012-6/35607.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="复仇者联盟" href="/html/35607.html">复仇者联盟</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">HD</span></li>
					<li><a target="_blank" class="pic" href="/html/37681.html"><img
							alt="超级战舰：异形海战"
							src="http://www.hao2018.com/pic/upimg/2012-4/20124181030622252.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="超级战舰：异形海战" href="/html/37681.html">超级战舰：异形..</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">HD</span></li>
					<li><a target="_blank" class="pic" href="/html/34857.html"><img
							alt="饥饿游戏"
							src="http://www.hao2018.com/pic/upimg/2012-6/34857.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="饥饿游戏" href="/html/34857.html">饥饿游戏</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">HD</span></li>
					<li><a target="_blank" class="pic" href="/html/37086.html"><img
							alt="灵魂战车2/恶灵骑士2/3D恶灵战警：复仇时刻"
							src="http://www.hao2018.com/pic/upimg/2012-6/37086.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="灵魂战车2/恶灵骑士2/3D恶灵战警：复仇时刻" href="/html/37086.html">灵魂战车2/恶..</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">HD</span></li>
					<li><a target="_blank" class="pic" href="/html/35872.html"><img
							alt="老雷斯的故事"
							src="http://www.hao2018.com/pic/upimg/2012-6/35872.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="老雷斯的故事" href="/html/35872.html">老雷斯的故事</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">720P</span></li>
				</ul>
			</div>
			<div class="blank10"></div>
			<div class="main_ads">
				<script src="/ads/index_728.js" language="javascript"></script>
				<script type="text/javascript">
					alimama_pid = "mm_11433910_2023830_9476009";
					alimama_titlecolor = "0000FF";
					alimama_descolor = "000000";
					alimama_bgcolor = "FFFFFF";
					alimama_bordercolor = "E6E6E6";
					alimama_linkcolor = "008000";
					alimama_bottomcolor = "FFFFFF";
					alimama_anglesize = "0";
					alimama_bgpic = "0";
					alimama_icon = "0";
					alimama_sizecode = "14";
					alimama_width = 728;
					alimama_height = 90;
					alimama_type = 2;
				</script>
				<script type="text/javascript" src="http://a.alimama.cn/inf.js"></script>
				<iframe scrolling="no" frameborder="0" marginheight="0"
					marginwidth="0" id="tanx-a-mm_11433910_2023830_9476009"
					style="width: 728px; height: 90px; border: 0px none;"
					src="http://z.alimama.com/alimama.php?i=mm_11433910_2023830_9476009&amp;w=728&amp;h=90&amp;sz=14&amp;re=1366x768&amp;cah=742&amp;caw=1366&amp;ccd=24&amp;ctz=8&amp;chl=2&amp;cja=0&amp;cpl=26&amp;cmm=40&amp;cf=11.4&amp;cg=949fb51333a26ceaec13d6fab05724db&amp;ac=5768&amp;prm=88443822&amp;cas=prm&amp;cbh=2805&amp;cbw=1349&amp;sx=0&amp;sy=2801&amp;refpos=,a,null&amp;t=2&amp;tc=0000FF&amp;dc=000000&amp;bgc=FFFFFF&amp;bdc=E6E6E6&amp;lc=008000&amp;bmc=FFFFFF&amp;as=0&amp;bgp=0&amp;ic=0&amp;pf=1&amp;p4p_ai=1&amp;dx=1&amp;iss=0&amp;u=http%3A%2F%2Fwww.bbgyy.net%2F&amp;k=&amp;tt=%E6%AD%A5%E6%AD%A5%E9%AB%98%E5%BD%B1%E9%99%A2%20-%20%E7%BE%8E%E5%9B%BD%E5%A4%A7%E7%89%87%7C%E6%AC%A7%E7%BE%8E%E5%A4%A7%E7%89%87%7C%E5%A5%BD%E8%8E%B1%E5%9D%9E%E5%A4%A7%E7%89%87%7C2012%E5%A4%A7%E7%89%87%7C%E7%BB%8F%E5%85%B8%E5%A4%A7%E7%89%87&amp;r=&amp;fu=-1&amp;pageid=8dc3f6c09194dfc653cd097fccc892e9"></iframe>
			</div>
			<div class="blank10"></div>
			<!--电影-->
			<div class="lmain">
				<div class="item">
					<h2>电影</h2>
					<div class="act">
						<a target="_blank" href="/list/1.html">动作片</a>&#12288;<a
							target="_blank" href="/list/2.html">喜剧片</a>&#12288;<a
							target="_blank" href="/list/3.html">爱情片</a>&#12288;<a
							target="_blank" href="/list/4.html">科幻片</a>&#12288;<a
							target="_blank" href="/list/5.html">战争片</a>&#12288;<a
							target="_blank" href="/list/6.html">恐怖片</a>&#12288;<a
							target="_blank" href="/list/7.html">剧情片</a>&#12288;<a
							target="_blank" href="/list/8.html">动画片</a>&#12288;
					</div>
				</div>
				<ul>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/19864.html"><img alt="我配不上她/辣妹爱宅男"
							src="http://www.hao2018.com/pic/upimg/2012-6/19864.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="我配不上她/辣妹爱宅男" href="/html/19864.html">我配不上她/辣..</a>
						</p>
						<p>2010年 地区：欧美</p> <span class="txt">HD</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/26424.html"><img alt="天龙特攻队/A字特攻队/通天奇兵"
							src="http://www.hao2018.com/pic/upimg/2012-6/26424.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="天龙特攻队/A字特攻队/通天奇兵" href="/html/26424.html">天龙特攻队/A..</a>
						</p>
						<p>2010年 地区：美国</p> <span class="txt">HD</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/19559.html"><img alt="终极斗士3：赎罪"
							src="http://www.hao2018.com/pic/upimg/2012-6/19559.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="终极斗士3：赎罪" href="/html/19559.html">终极斗士3：赎罪</a>
						</p>
						<p>2010年 地区：欧美</p> <span class="txt">BD</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/18944.html"><img alt="我的名字叫罕/我的名字叫可汗"
							src="http://www.hao2018.com/pic/upimg/2012-6/18944.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="我的名字叫罕/我的名字叫可汗" href="/html/18944.html">我的名字叫罕/..</a>
						</p>
						<p>2010年 地区：印度</p> <span class="txt">HD</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/38753.html"><img alt="白鹿原"
							src="http://www.hao2018.com/pic/upimg/2012-8/201281717195536391.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="白鹿原" href="/html/38753.html">白鹿原</a>
						</p>
						<p>2012年 地区：中国大陆</p> <span class="txt">720P</span></li>
				</ul>
				<ul class="ultxt">
					<li><a href="/html/39366.html">阿尔巴特/成吉..</a>
					</li>
					<li><a href="/html/39365.html">360度/圆舞..</a>
					</li>
					<li><a href="/html/39364.html">疯狂马戏团</a>
					</li>
					<li><a href="/html/39363.html">鬼佬大哥大</a>
					</li>
					<li><a href="/html/39361.html">华纳巨星总动员..</a>
					</li>
					<li><a href="/html/39359.html">猫和老鼠：罗宾..</a>
					</li>
					<li><a href="/html/39280.html">全面回忆(20..</a>
					</li>
					<li><a href="/html/39108.html">克里夫兰秀第四季</a>
					</li>
					<li><a href="/html/27618.html">欢迎来到利雷家</a>
					</li>
					<li><a href="/html/39351.html">生死密电</a>
					</li>
					<li><a href="/html/39349.html">鬼入镜4/灵动..</a>
					</li>
					<li><a href="/html/39346.html">爱与诚</a>
					</li>
					<li><a href="/html/39343.html">大学那点事儿之..</a>
					</li>
					<li><a href="/html/39342.html">莉拉，莉拉/承..</a>
					</li>
					<li><a href="/html/39278.html">破坏王拉尔夫/..</a>
					</li>
					<li><a href="/html/39340.html">旅愁</a>
					</li>
					<li><a href="/html/39338.html">魔都传奇</a>
					</li>
					<li><a href="/html/7557.html">文雀</a>
					</li>
					<li><a href="/html/34759.html">互换身体/两男..</a>
					</li>
					<li><a href="/html/39334.html">大学那点事儿之..</a>
					</li>
					<li><a href="/html/39333.html">大学那点事儿之..</a>
					</li>
					<li><a href="/html/39329.html">美景之屋</a>
					</li>
					<li><a href="/html/39328.html">锈与骨</a>
					</li>
					<li><a href="/html/39327.html">女飞人</a>
					</li>
					<li><a href="/html/39326.html">女朋友男朋友/..</a>
					</li>
				</ul>
			</div>
			<div class="blank10"></div>
			<!--电视剧-->
			<div class="lmain">
				<div class="item">
					<h2>电视剧</h2>
					<div class="act">
						<a target="_blank" href="/list/9.html">大陆剧</a>&#12288;<a
							target="_blank" href="/list/10.html">港剧</a>&#12288;<a
							target="_blank" href="/list/11.html">台剧</a>&#12288;<a
							target="_blank" href="/list/12.html">韩剧</a>&#12288;<a
							target="_blank" href="/list/13.html">日剧</a>&#12288;<a
							target="_blank" href="/list/14.html">欧美剧</a>&#12288;<a
							target="_blank" href="/list/15.html">新马泰剧</a>&#12288;
					</div>
				</div>
				<ul>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/39347.html"><img alt="想你"
							src="http://www.hao2018.com/pic/upimg/2012-11/20121188464532120.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="想你" href="/html/39347.html">想你</a>
						</p>
						<p>2012年 地区：韩国</p> <span class="txt">第2集</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/39315.html"><img alt="大太监"
							src="http://www.hao2018.com/pic/upimg/2012-11/20121168573250514.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="大太监" href="/html/39315.html">大太监</a>
						</p>
						<p>2012年 地区：中国香港</p> <span class="txt">第5集</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/39358.html"><img alt="媳妇的美好宣言"
							src="http://www.hao2018.com/pic/upimg/2012-11/20121182221189253.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="媳妇的美好宣言" href="/html/39358.html">媳妇的美好宣言</a>
						</p>
						<p>2012年 地区：中国大陆</p> <span class="txt">第2集</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/39106.html"><img alt="绿箭侠/绿箭第一季"
							src="http://www.hao2018.com/pic/upimg/2012-10/2012101116284358561.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="绿箭侠/绿箭第一季" href="/html/39106.html">绿箭侠/绿箭第..</a>
						</p>
						<p>2012年 地区：美国</p> <span class="txt">第5集</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/30705.html"><img alt="最佳爱情"
							src="http://www.hao2018.com/pic/upimg/2012-6/30705.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="最佳爱情" href="/html/30705.html">最佳爱情</a>
						</p>
						<p>2011年 地区：韩国</p> <span class="txt">16集全</span></li>
				</ul>
				<ul class="ultxt">
					<li><a href="/html/39330.html">对对碰</a>
					</li>
					<li><a href="/html/39180.html">Piece</a>
					</li>
					<li><a href="/html/39368.html">我租了一个情人</a>
					</li>
					<li><a href="/html/39339.html">从军记</a>
					</li>
					<li><a href="/html/39306.html">望海的女人</a>
					</li>
					<li><a href="/html/39356.html">杨善洲/不曾见..</a>
					</li>
					<li><a href="/html/38508.html">风水世家</a>
					</li>
					<li><a href="/html/39288.html">大抉择/天下归心</a>
					</li>
					<li><a href="/html/39367.html">相棒第十一季</a>
					</li>
					<li><a href="/html/39362.html">奶奶再爱我一次</a>
					</li>
					<li><a href="/html/39100.html">东京全力少女</a>
					</li>
					<li><a href="/html/39114.html">不结婚</a>
					</li>
					<li><a href="/html/38026.html">依然是你</a>
					</li>
					<li><a href="/html/38729.html">闭嘴家族</a>
					</li>
					<li><a href="/html/39275.html">恋夏38度C</a>
					</li>
					<li><a href="/html/39285.html">那样芬芳</a>
					</li>
					<li><a href="/html/39092.html">音乐之乡/下一..</a>
					</li>
					<li><a href="/html/39226.html">特别使命</a>
					</li>
					<li><a href="/html/39243.html">笑脸</a>
					</li>
					<li><a href="/html/39042.html">芝加哥烈焰/风..</a>
					</li>
					<li><a href="/html/38887.html">善良的男人</a>
					</li>
					<li><a href="/html/39316.html">游击兵工厂</a>
					</li>
					<li><a href="/html/39354.html">兄弟海</a>
					</li>
					<li><a href="/html/39094.html">大风水</a>
					</li>
					<li><a href="/html/37880.html">黄色复仇草</a>
					</li>
				</ul>
			</div>
			<div class="blank10"></div>
			<!--综艺-->
			<div class="lmain">
				<div class="item">
					<h2>综艺</h2>
					<div class="act">
						<a target="_blank" href="/list/17.html">更多综艺</a>&#12288;
					</div>
				</div>
				<ul>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/38578.html"><img alt="百变大咖秀"
							src="http://www.hao2018.com/pic/upimg/2012-7/20127278371518548.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="百变大咖秀" href="/html/38578.html">百变大咖秀</a>
						</p>
						<p>2012年 地区：中国大陆</p> <span class="txt">20121108期</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/17080.html"><img alt="康熙来了"
							src="http://www.hao2018.com/pic/upimg/2012-6/17080.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="康熙来了" href="/html/17080.html">康熙来了</a>
						</p>
						<p>2010年 地区：中国台湾</p> <span class="txt">20121108期</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/16902.html"><img alt="本山快乐营"
							src="http://www.hao2018.com/pic/upimg/2012-6/16902.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="本山快乐营" href="/html/16902.html">本山快乐营</a>
						</p>
						<p>2010年 地区：中国大陆</p> <span class="txt">20121107期</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/17082.html"><img alt="军情观察室"
							src="http://www.hao2018.com/pic/upimg/2012-6/17082.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="军情观察室" href="/html/17082.html">军情观察室</a>
						</p>
						<p>2010年 地区：中国香港</p> <span class="txt">20121107期</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/27449.html"><img alt="非诚勿扰"
							src="http://www.hao2018.com/pic/upimg/2012-6/27449.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="非诚勿扰" href="/html/27449.html">非诚勿扰</a>
						</p>
						<p>2011年 地区：中国大陆</p> <span class="txt">20121104期</span></li>
				</ul>
				<ul class="ultxt">
					<li><a href="/html/16988.html">女人我最大</a>
					</li>
					<li><a href="/html/17075.html">鲁豫有约</a>
					</li>
					<li><a href="/html/31738.html">军情直播间</a>
					</li>
					<li><a href="/html/28787.html">超级全能住宅改..</a>
					</li>
					<li><a href="/html/32459.html">爱笑会议室</a>
					</li>
					<li><a href="/html/39284.html">神话放送</a>
					</li>
					<li><a href="/html/28699.html">职来职往</a>
					</li>
					<li><a href="/html/37657.html">谢天谢地，你来..</a>
					</li>
					<li><a href="/html/39029.html">小崔说立波秀</a>
					</li>
					<li><a href="/html/37417.html">家庭幽默录像</a>
					</li>
					<li><a href="/html/39003.html">2012中央电..</a>
					</li>
					<li><a href="/html/38262.html">舞魅天下第九季</a>
					</li>
					<li><a href="/html/27127.html">东方直播室</a>
					</li>
					<li><a href="/html/35780.html">养生堂</a>
					</li>
					<li><a href="/html/38591.html">超级模王大道</a>
					</li>
					<li><a href="/html/31875.html">非常了得</a>
					</li>
					<li><a href="/html/38596.html">2012年第3..</a>
					</li>
					<li><a href="/html/16991.html">国光帮帮忙</a>
					</li>
					<li><a href="/html/16936.html">锵锵三人行</a>
					</li>
					<li><a href="/html/38524.html">快播直播频道(..</a>
					</li>
					<li><a href="/html/15194.html">两天一夜/2天..</a>
					</li>
					<li><a href="/html/18440.html">王刚讲故事</a>
					</li>
					<li><a href="/html/16888.html">非常静距离</a>
					</li>
					<li><a href="/html/16872.html">Power星期天</a>
					</li>
					<li><a href="/html/16847.html">一虎一席谈</a>
					</li>
				</ul>
			</div>
			<div class="blank10"></div>
			<!--动漫-->
			<div class="lmain">
				<div class="item">
					<h2>动漫</h2>
					<div class="act">
						<a target="_blank" href="/list/16.html">更多动漫</a>&#12288;
					</div>
				</div>
				<ul>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/16700.html"><img alt="火影忍者"
							src="http://www.hao2018.com/pic/upimg/2012-6/16700.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="火影忍者" href="/html/16700.html">火影忍者</a>
						</p>
						<p>2002年 地区：日本</p> <span class="txt">第508集</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/38995.html"><img alt="中二病也要谈恋爱/中二病也要恋爱！"
							src="http://www.hao2018.com/pic/upimg/2012-9/201292917545613029.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="中二病也要谈恋爱/中二病也要恋爱！" href="/html/38995.html">中二病也要谈恋..</a>
						</p>
						<p>2012年 地区：日本</p> <span class="txt">第6集</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/36571.html"><img alt="新网球王子"
							src="http://www.hao2018.com/pic/upimg/2012-6/36571.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="新网球王子" href="/html/36571.html">新网球王子</a>
						</p>
						<p>2012年 地区：日本</p> <span class="txt">13集+OVA03</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/35416.html"><img
							alt="全职猎人2011/全职猎人重制版/全职猎人II/新全职猎人"
							src="http://www.hao2018.com/pic/upimg/2012-6/35416.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="全职猎人2011/全职猎人重制版/全职猎人II/新全职猎人" href="/html/35416.html">全职猎人201..</a>
						</p>
						<p>2011年 地区：日本</p> <span class="txt">第54集</span></li>
					<li><a blockid="3756" target="_blank" class="pic"
						href="/html/32499.html"><img alt="海贼王/海盗王"
							src="http://www.hao2018.com/pic/upimg/2012-6/32499.jpg"><span
								class="num"></span>
					</a>
						<p class="movielist_tt">
							<a title="海贼王/海盗王" href="/html/32499.html">海贼王/海盗王</a>
						</p>
						<p>1997年 地区：日本</p> <span class="txt">第571集</span></li>
				</ul>
				<ul class="ultxt">
					<li><a href="/html/38846.html">少年特警队</a>
					</li>
					<li><a href="/html/37522.html">白熊咖啡厅</a>
					</li>
					<li><a href="/html/39082.html">少年骇客：全面..</a>
					</li>
					<li><a href="/html/39112.html">心里测量者PS..</a>
					</li>
					<li><a href="/html/37536.html">到银河比赛/向..</a>
					</li>
					<li><a href="/html/39001.html">惊爆游戏</a>
					</li>
					<li><a href="/html/39038.html">绝园的暴风雨</a>
					</li>
					<li><a href="/html/39127.html">兽旋战斗</a>
					</li>
					<li><a href="/html/27476.html">卡片战斗先导者</a>
					</li>
					<li><a href="/html/38773.html">K</a>
					</li>
					<li><a href="/html/38866.html">战斗王之飓风战魂</a>
					</li>
					<li><a href="/html/39097.html">荔枝DE光之俱..</a>
					</li>
					<li><a href="/html/39048.html">超速变形Gyr..</a>
					</li>
					<li><a href="/html/39099.html">最强会长黑神第..</a>
					</li>
					<li><a href="/html/38362.html">千岁GetYou</a>
					</li>
					<li><a href="/html/39255.html">蝙蝠侠传奇第二季</a>
					</li>
					<li><a href="/html/491.html">旋风管家/旋风..</a>
					</li>
					<li><a href="/html/35491.html">甜心格格</a>
					</li>
					<li><a href="/html/38598.html">怪诞小镇第一季</a>
					</li>
					<li><a href="/html/37571.html">终极蜘蛛侠</a>
					</li>
					<li><a href="/html/39054.html">军火女王第二季</a>
					</li>
					<li><a href="/html/39023.html">T宝的悲惨日常..</a>
					</li>
					<li><a href="/html/39121.html">偶像活动/偶像..</a>
					</li>
					<li><a href="/html/39067.html">忍者神龟2012</a>
					</li>
					<li><a href="/html/38821.html">十二生肖快乐街..</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="side">
			<!--今日更新-->
			<div class="rside">
				<div class="item">
					<h2>今日更新</h2>
					<div class="act">
						<a href="/zt/new.html">更多&gt;&gt;</a>
					</div>
				</div>
				<ul style="height:564px;">
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39330.html">对对碰</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39180.html">Piece</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39368.html">我租了一个情人</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39339.html">从军记</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39306.html">望海的女人</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39356.html">杨善洲/不曾见..</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/38508.html">风水世家</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39288.html">大抉择/天下归心</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39367.html">相棒第十一季</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39366.html">阿尔巴特/成吉..</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39365.html">360度/圆舞..</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39364.html">疯狂马戏团</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39363.html">鬼佬大哥大</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39362.html">奶奶再爱我一次</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39100.html">东京全力少女</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39114.html">不结婚</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/38026.html">依然是你</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/38729.html">闭嘴家族</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39275.html">恋夏38度C</a>
					</li>
					<li><em class="red">11-09</em><a target="_blank"
						href="/html/39285.html">那样芬芳</a>
					</li>
				</ul>
			</div>
			<div class="blank10"></div>
			<!--热门电影-->
			<div class="rside">
				<div class="item">
					<h2>热门电影</h2>
				</div>
				<ul style="height:400px;">
					<li><em>08-25</em><a target="_blank" href="/html/35607.html">复仇者联盟</a>
					</li>
					<li><em>10-08</em><a target="_blank" href="/html/38864.html">敢死队2/浴血..</a>
					</li>
					<li><em>09-29</em><a target="_blank" href="/html/38160.html">普罗米修斯/异..</a>
					</li>
					<li><em>10-27</em><a target="_blank" href="/html/33664.html">超凡蜘蛛侠/神..</a>
					</li>
					<li><em>09-08</em><a target="_blank" href="/html/38068.html">黑衣人3/3D..</a>
					</li>
					<li><em>08-06</em><a target="_blank" href="/html/34857.html">饥饿游戏</a>
					</li>
					<li><em>08-07</em><a target="_blank" href="/html/37681.html">超级战舰：异形..</a>
					</li>
					<li><em>10-16</em><a target="_blank" href="/html/38663.html">消失的子弹/奇..</a>
					</li>
					<li><em>09-01</em><a target="_blank" href="/html/38017.html">暂告安全/火线..</a>
					</li>
					<li><em>09-06</em><a target="_blank" href="/html/38458.html">四大名捕</a>
					</li>
					<li><em>07-18</em><a target="_blank" href="/html/30712.html">变形金刚3：月..</a>
					</li>
					<li><em>06-26</em><a target="_blank" href="/html/26757.html">加勒比海盗4：..</a>
					</li>
					<li><em>06-29</em><a target="_blank" href="/html/31656.html">X战警：第一战..</a>
					</li>
					<li><em>06-26</em><a target="_blank" href="/html/30575.html">速度与激情5/..</a>
					</li>
				</ul>
			</div>
			<div class="blank10"></div>
			<!--热门电视剧-->
			<div class="rside">
				<div class="item">
					<h2>热门电视剧</h2>
				</div>
				<ul style="height:400px;">
					<li><em>09-01</em><a target="_blank" href="/html/38410.html">轩辕剑之天之痕..</a>
					</li>
					<li><em>08-10</em><a target="_blank" href="/html/38448.html">回到三国</a>
					</li>
					<li><em>10-14</em><a target="_blank" href="/html/39021.html">我是特种兵2/..</a>
					</li>
					<li><em>09-02</em><a target="_blank" href="/html/38741.html">北京青年</a>
					</li>
					<li><em>07-28</em><a target="_blank" href="/html/38364.html">护花危情/Mi..</a>
					</li>
					<li><em>08-12</em><a target="_blank" href="/html/33318.html">爱情公寓3/爱..</a>
					</li>
					<li><em>08-13</em><a target="_blank" href="/html/38320.html">飞虎</a>
					</li>
					<li><em>10-29</em><a target="_blank" href="/html/38900.html">新白发魔女传</a>
					</li>
					<li><em>01-06</em><a target="_blank" href="/html/36018.html">后宫甄嬛传/后宫</a>
					</li>
					<li><em>11-05</em><a target="_blank" href="/html/38953.html">雷霆扫毒</a>
					</li>
					<li><em>07-29</em><a target="_blank" href="/html/38482.html">胜女的代价</a>
					</li>
					<li><em>08-29</em><a target="_blank" href="/html/38483.html">活佛济公3</a>
					</li>
					<li><em>03-19</em><a target="_blank" href="/html/35686.html">行尸走肉第2季</a>
					</li>
					<li><em>11-05</em><a target="_blank" href="/html/39132.html">行尸走肉第三季</a>
					</li>
				</ul>
			</div>
			<div class="blank10"></div>
			<!--热门综艺-->
			<div class="rside">
				<div class="item">
					<h2>热门综艺</h2>
				</div>
				<ul style="height:400px;">
					<li><em>10-03</em><a target="_blank" href="/html/38562.html">中国好声音</a>
					</li>
					<li><em>11-03</em><a target="_blank" href="/html/16829.html">快乐大本营</a>
					</li>
					<li><em>10-07</em><a target="_blank" href="/html/16488.html">壹周立波秀</a>
					</li>
					<li><em>11-09</em><a target="_blank" href="/html/17080.html">康熙来了</a>
					</li>
					<li><em>11-05</em><a target="_blank" href="/html/27449.html">非诚勿扰</a>
					</li>
					<li><em>06-14</em><a target="_blank" href="/html/31944.html">流川枫与苍井空</a>
					</li>
					<li><em>11-02</em><a target="_blank" href="/html/16797.html">天天向上</a>
					</li>
					<li><em>11-08</em><a target="_blank" href="/html/17082.html">军情观察室</a>
					</li>
					<li><em>06-11</em><a target="_blank" href="/html/31874.html">小泽玛利亚男人..</a>
					</li>
					<li><em>11-08</em><a target="_blank" href="/html/16902.html">本山快乐营</a>
					</li>
					<li><em>08-27</em><a target="_blank" href="/html/34811.html">av事务所</a>
					</li>
					<li><em>11-08</em><a target="_blank" href="/html/31738.html">军情直播间</a>
					</li>
					<li><em>11-09</em><a target="_blank" href="/html/38578.html">百变大咖秀</a>
					</li>
					<li><em>11-06</em><a target="_blank" href="/html/32459.html">爱笑会议室</a>
					</li>
				</ul>
			</div>
			<div class="blank10"></div>
			<!--热门动漫-->
			<div class="rside">
				<div class="item">
					<h2>热门动漫</h2>
				</div>
				<ul style="height:400px;">
					<li><em>11-08</em><a target="_blank" href="/html/16700.html">火影忍者</a>
					</li>
					<li><em>11-04</em><a target="_blank" href="/html/32499.html">海贼王/海盗王</a>
					</li>
					<li><em>11-03</em><a target="_blank" href="/html/16866.html">名侦探柯南</a>
					</li>
					<li><em>11-03</em><a target="_blank" href="/html/16932.html">妖精的尾巴/魔..</a>
					</li>
					<li><em>11-04</em><a target="_blank" href="/html/35416.html">全职猎人201..</a>
					</li>
					<li><em>09-23</em><a target="_blank" href="/html/37539.html">黑子的篮球</a>
					</li>
					<li><em>03-27</em><a target="_blank" href="/html/16979.html">死神</a>
					</li>
					<li><em>11-02</em><a target="_blank" href="/html/39004.html">秦时明月之万里..</a>
					</li>
					<li><em>11-07</em><a target="_blank" href="/html/37571.html">终极蜘蛛侠</a>
					</li>
					<li><em>11-04</em><a target="_blank" href="/html/29215.html">美食的俘虏/美..</a>
					</li>
					<li><em>11-04</em><a target="_blank" href="/html/37460.html">圣斗士星矢Ω(..</a>
					</li>
					<li><em>09-26</em><a target="_blank" href="/html/38411.html">美少女死神还我..</a>
					</li>
					<li><em>09-01</em><a target="_blank" href="/html/34886.html">父与女</a>
					</li>
					<li><em>09-23</em><a target="_blank" href="/html/35527.html">机动战士高达A..</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="nav_ads">
		<script src="/ads/foot_banner.js" language="javascript"></script>
		<script type="text/javascript">
			/*bbgyy.com_尾_960*90，创建于2011-4-1*/var cpro_id = 'u430963';
		</script>
		<script type="text/javascript"
			src="http://cpro.baidu.com/cpro/ui/c.js"></script>
		<script
			src="http://pos.baidu.com/ecom?di=u430963&amp;tm=BAIDU_CPRO_SETJSONADSLOT&amp;fn=BAIDU_CPRO_SETJSONADSLOT&amp;baidu_id="
			charset="utf-8" type="text/javascript"></script>
		<div style="display:none">-</div>
		<iframe scrolling="no" width="960" height="90" frameborder="0"
			align="center,center" allowtransparency="true" marginheight="0"
			marginwidth="0"
			src="http://cpro.baidu.com/cpro/ui/uijs.php?tu=u430963&amp;tn=text_default_960_90&amp;n=mafang521_cpr&amp;rsi1=90&amp;rsi0=960&amp;rad=&amp;rss0=%23FFFFFF&amp;rss1=%23FFFFFF&amp;rss2=%230000FF&amp;rss3=%23444444&amp;rss4=%23008000&amp;rss5=&amp;rss6=%23e10900&amp;rsi5=4&amp;ts=1&amp;at=6&amp;ch=0&amp;cad=1&amp;aurl=&amp;rss7=&amp;cpa=1&amp;fv=11&amp;cn=0&amp;if=16&amp;word=http%3A%2F%2Fwww.bbgyy.net%2F&amp;refer=&amp;ready=1&amp;jk=ca82ee423458a4ea&amp;jn=3&amp;lmt=1352440090&amp;csp=1366,768&amp;csn=1366,742&amp;ccd=24&amp;chi=2&amp;cja=false&amp;cpl=26&amp;cmi=40&amp;cce=true&amp;csl=zh-CN&amp;did=2&amp;rt=20&amp;dt=1352445455&amp;pn=4|text_default_960_90|6&amp;ev=67108864&amp;c01=0&amp;prt=1352445453095&amp;i3=f"
			id="cproIframe2"></iframe>
	</div>
	<!--友情链接-->
	<div class="flink">
		<div class="item">
			<h2>友情链接</h2>
		</div>
		<ul>
			<li><a target="_blank" href="http://www.bbgyy.net/">步步高影院</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/tv.html">电视直播</a>
			</li>
			<li><a target="_blank" href="http://www.qvod163.com/">快播导航</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">美国大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">欧美大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">好莱坞大片</a>
			</li>
			<li><a target="_blank"
				href="http://www.bbgyy.net/zt/dapian2012.html">2012大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">美国电影</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">在线观看</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">2012最新电影</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">经典大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">最新大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">动作大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">科幻大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">好看的美国大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">qvod大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">快播大片</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">大片下载</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">2012年电影</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">快播</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">百度影音</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">Qvod</a>
			</li>
			<li><a target="_blank" href="http://www.qweyy.com">Q播电影</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.cc/">步步高影院</a>
			</li>
			<li><a target="_blank" href="http://www.bbgyy.net/">步步高影院</a>
			</li>
			<li><a target="_blank" href="http://www.kbsou.com/">快播搜</a>
			</li>
			<li><a target="_blank" href="http://www.921.cc">最新电影</a>
			</li>
			<li><a target="_blank" href="http://www.28183.com">最新电影</a>
			</li>
			<li><a target="_blank" href="http://www.paody.com">新电影</a>
			</li>
			<li><a target="_blank" href="http://www.176yc.com">好看的电影</a>
			</li>
			<li><a target="_blank" href="http://www.90he.com">百度影音电影</a>
			</li>
			<li><a target="_blank" href="http://www.Kan789.com">免费电影院</a>
			</li>
			<li><a target="_blank" href="http://www.cpuyy.com">核心影院</a>
			</li>
			<li><a target="_blank" href="http://www.320dy.com">320电影</a>
			</li>
		</ul>
	</div>
	<!--网站底部-->
	<div class="footer">
		<div class="links">
			<a href="/help.html" target="_blank">帮助中心</a><span>|</span><a
				target="_blank" href="/qvod.html">快播播放器</a><span>|</span><a
				style="color:#FF0000" target="_blank" href="/shenming.html">免责申明</a><span>|</span><a
				target="_blank" href="/ad.html">广告服务</a><span>|</span><a
				target="_blank" href="/lx.html">联系我们</a><span>|</span><a
				href="http://www.bbgyy.net/gbook.asp" target="_blank">意见建议</a><span>|</span><a
				href="/allmovie.html" target="_blank">网站地图</a>
		</div>
		<div class="ftxt">
			<ul>
				<li>版权声明：本网站为非赢利性站点，本网站所有内容均来源于互联网相关站点自动搜索采集信息，相关链接已经注明来源。</li>
				<li>免责声明：本网站将逐步删除和规避程序自动搜索采集到的不提供分享的版权影视。本站仅供测试和学习交流。请大家支持正版。</li>
				<li>Copyright &copy; 2008-2010 <a href="http://www.bbgyy.net/">步步高影院</a>.
					all rights reserved. 粤ICP备080123456号 <script src="/style/js/tj.js"
						language="javascript"></script>
					<script language="JavaScript"
						src="http://s16.cnzz.com/stat.php?id=1297698&amp;web_id=1297698"></script>
					<script type="text/javascript" charset="utf-8"
						src="http://c.cnzz.com/cnzz_core.php?web_id=1297698&amp;l=none"></script><a
					title="站长统计" target="_blank"
					href="http://www.cnzz.com/stat/website.php?web_id=1297698">站长统计</a>
					<script src="/ads/foot.js" language="javascript"></script>
					<script type="text/javascript">
						/*bbgyy.com_悬浮_120*270，创建于2011-7-2*/
						var cpro_id = 'u527109';
					</script>
					<script type="text/javascript"
						src="http://cpro.baidu.com/cpro/ui/f.js"></script>
					<script
						src="http://pos.baidu.com/ecom?di=u527109&amp;tm=BAIDU_CPRO_SETJSONADSLOT&amp;fn=BAIDU_CPRO_SETJSONADSLOTFLOAT&amp;baidu_id="
						charset="utf-8" type="text/javascript"></script> <script
						data="type=slide&amp;img=2&amp;pos=right&amp;uid=11219"
						id="bdshare_js" type="text/javascript"
						src="http://bdimg.share.baidu.com/static/js/bds_s_v2.js?cdnversion=375680"></script>
					<script id="bdshell_js" type="text/javascript"
						src="http://bdimg.share.baidu.com/static/js/shell_v2.js?t=15"></script>
					<script type="text/javascript">
						var bds_config = {
							"bdTop" : 320
						};
						document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?t="
								+ new Date().getHours();
					</script></li>
			</ul>
		</div>
	</div>
	<!--设定导航
<script type="text/javascript">setnav(0);</script>-->

	<iframe
		style="position: absolute; width: 100px; height: 1px; z-index: -10240; visibility: hidden;"
		src="http://cpro.baidu.com/cpro/ui/e.html"></iframe>
</body>
</html>
