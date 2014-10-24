package controller.admin;

import bean.TbsHttpRequestBean;
import bean.model.TbsHttpRequestModel;
import org.apache.http.client.fluent.Content;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.TbsHttpRequestService;
import util.browser.HCPool;
import util.browser.HtmlJsoup;
import util.core.MethodUtil;
import util.json.JSONUtil;
import util.spring.MyTimestampPropertyEdit;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
@Controller
@RequestMapping("/admin/TbsHttpRequest")
public class TbsHttpRequestControllerAdmin extends BaseController{	private final static Logger log= Logger.getLogger(TbsHttpRequestControllerAdmin.class);
	private static  MethodUtil util = new MethodUtil();
	// 服务类
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private TbsHttpRequestService<TbsHttpRequestBean> tbsHttpRequestService; 
	
	//参数类
	private List<TbsHttpRequestBean> listTbsHttpRequest = null;
	private TbsHttpRequestBean tbsHttpRequestBean = new TbsHttpRequestBean();  //JavaBean
	//private TbsHttpRequestModel tbsHttpRequestModel=new TbsHttpRequestModel(); //分页模型
	
		@InitBinder//必须有一个参数WebDataBinder 日期类型装换
	public void initBinder(WebDataBinder binder) {
		    binder.registerCustomEditor(Timestamp.class,new MyTimestampPropertyEdit()); //使用自定义属性编辑器
		//  binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}	/**
	 * 
	 * <br>
	 * <b>功能：</b>主页<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-25 <br>
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView list(/*TbsHttpRequestModel tbsHttpRequestModel*/){
		return new ModelAndView("admin/TbsHttpRequest/TbsHttpRequestIndex");
	}	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>查询方法<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-12 <br>
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param tbsMenuChildModel
	 * @param searchAnds
	 * @param searchColumnNames
	 * @param searchConditions
	 * @param searchVals
	 * @return
	 */
	@RequestMapping("/listData.html")
	@ResponseBody
	public String listData(String page,String rows,String sort,String order,TbsHttpRequestModel tbsHttpRequestModel,String searchAnds,String searchColumnNames,String searchConditions,String searchVals){
		String result = "[]";
		System.out.println("page:"+page+"|rows:"+rows+"|sort:"+sort+"|order:"+order+"TbsHttpRequestModel:"+tbsHttpRequestModel+"|searchAnds:"+searchAnds+"|searchColumnNames:"+searchColumnNames);
		if(null!=searchColumnNames && searchColumnNames.trim().length()>0){
			StringBuffer sb=new StringBuffer();
			String[] searchColumnNameArray=searchColumnNames.split("\\,");
			String[] searchAndsArray=searchAnds.split("\\,");
			String[] searchConditionsArray=searchConditions.split("\\,");
			String[] searchValsArray=searchVals.split("\\,");
			System.out.println(Arrays.toString(searchColumnNameArray));
			for (int i = 0; i < searchColumnNameArray.length; i++) {
				if (searchColumnNameArray[i].trim().length() > 0 && searchConditionsArray[i].trim().length()>0) {
					if (i == 0) {
						sb.append(searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " + searchValsArray[i].trim());
					} else {
						sb.append(" " + searchAndsArray[i].trim() + " " + searchColumnNameArray[i].trim() + " " + searchConditionsArray[i].trim() + " " + searchValsArray[i].trim());
					}
				}
			}
			if(sb.length()>0){
				System.out.println("sb:"+sb.toString());
				Map<String,Object> map=new HashMap<String, Object>();
				util.core.PageUtil pageUtil=new util.core.PageUtil();
				pageUtil.setAndCondition(sb.toString());
				pageUtil.setPageId(Integer.parseInt(page));
				pageUtil.setPageSize(Integer.parseInt(rows));
				map.put("pageUtil", pageUtil);
				try {
					listTbsHttpRequest = tbsHttpRequestService.selectByMapPaging(map);
					result="{\"total\":\""+pageUtil.getRowCount()+"\",\"rows\":"+com.alibaba.fastjson.JSON.toJSONString(listTbsHttpRequest)+"}";
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			}
		}
		try{
			tbsHttpRequestModel.getPageUtil().setPageId(Integer.parseInt(page)); //当前页
			tbsHttpRequestModel.getPageUtil().setPageSize(Integer.parseInt(rows));//显示X条
			//tbsHttpRequestModel.getPageUtil().setOrderField(sort);      //排序字段名称
			//tbsHttpRequestModel.getPageUtil().setOrderDirection(true);  //true false 表示 正序与倒序
			listTbsHttpRequest = tbsHttpRequestService.selectByModel(tbsHttpRequestModel);
			result="{\"total\":\""+tbsHttpRequestModel.getPageUtil().getRowCount()+"\",\"rows\":"+com.alibaba.fastjson.JSON.toJSONString(listTbsHttpRequest)+"}";
		}catch(Exception e){
			log.error(e);
		}
		return result;
	}
    /**
     * 
     * <br>
     * <b>功能：</b>转向<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2013-1-22 <br>
     * @param ids
     * @param map
     * @return
     */
	@RequestMapping("/edit.html") 
	public String edit(){
		return "admin/TbsHttpRequest/TbsHttpRequestEdit";
	}
	    /**
     * 
     * <br>
     * <b>功能：</b>增加 TbsHttpRequestBean<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2012-10-11 <br>
     * @return
     */
	@RequestMapping("/add.html")
	public void add(TbsHttpRequestBean tbsHttpRequestBean,HttpServletResponse response){
		tbsHttpRequestBean.setId(util.getUid().toString());//设置主键
		System.out.println("tbsHttpRequestBean:"+tbsHttpRequestBean.toString());
	    try {
			if(tbsHttpRequestService.insert(tbsHttpRequestBean)>0){
				util.toJsonMsg(response, 0, null);
				return;
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	    util.toJsonMsg(response, 1, null);
	}    /**
     * 
     * <br>
     * <b>功能：</b>保存 TbsHttpRequestBean<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2012-10-11 <br>
     * @return
     */
	@RequestMapping("/save.html") 
	public void save(TbsHttpRequestBean tbsHttpRequestBean,HttpServletResponse response){
		try{
		 	if(tbsHttpRequestService.updateByPrimaryKey(tbsHttpRequestBean)>0){
			    util.toJsonMsg(response, 0, null);
			    return;
			 }
		}catch(Exception e){
			util.toJsonMsg(response, 1, null);
			e.printStackTrace();
		}
	}
	    /**
     * 
     * <br>
     * <b>功能：</b>删除 TbsHttpRequestBean<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-25 <br>
     * @param ids
     * @param response
     */
	@RequestMapping("/del.html") 
	public void del(String[] ids,HttpServletResponse response){
		System.out.println("del-ids:"+Arrays.toString(ids));
		try{
			 if(null!=ids && ids.length>0){
				 if(tbsHttpRequestService.deleteByPrimaryKeys(ids)>0){
					 util.toJsonMsg(response, 0, null);
					 return;
				 }
			 }
		}catch(Exception e){
			util.toJsonMsg(response, 1, null);
			log.error(e);
		}
	}
	
    /////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////在下面添加自定义的方法///////////////////////////////////
    private SqlSessionFactory sqlSessionFactory=null; 
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) { 
         this.sqlSessionFactory = sqlSessionFactory; 
    } 
	@RequestMapping(value = "/httpIndex.html")
	public String http(/*String id,ModelMap modelMap*/) {
		/*try {
			System.out.println("id:"+id);
			modelMap.addAttribute("tbsHttpRequestBean", tbsHttpRequestService.selectById(Long.parseLong(id)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return "admin/TbsHttpRequest/TbsHttpRequestHttpIndex";
	}
	@RequestMapping(value = "/httpDialog.html")
	public String httpDialog() {
		return "admin/TbsHttpRequest/TbsHttpRequestHttpDialog";
	}
	@RequestMapping(value = "/http.html")
	@ResponseBody
	public String hc(String type, TbsHttpRequestBean tbsHttpRequestBean, HttpServletResponse response) throws JSONException {
		System.out.println("type:" + type + "|" + JSONUtil.toJSONString(tbsHttpRequestBean) + "|" + tbsHttpRequestBean.getName());
		if (null == type)
			return "类型错误";
		try {
			tbsHttpRequestService.updateByPrimaryKey(tbsHttpRequestBean);
		} catch (Exception e) {
			e.printStackTrace();
			return "保存规则失败";
		}
		String encoding=tbsHttpRequestBean.getEncoding();
		if(type.equals("15")){
			return this.getThreadPool(tbsHttpRequestBean);
		}
		if (type.equals("1")) {
			return new HtmlJsoup().getPage(tbsHttpRequestBean.getFirstPage(), null, null, null, encoding, encoding).html();
		}
		String[] rs = tbsHttpRequestBean.getRegexs().split("\\|");
		String[] as = tbsHttpRequestBean.getArrtSplit().split("\\|");
		List<String[][]> list = this.getList(tbsHttpRequestBean.getFirstPage(), null, null, null, encoding, encoding, rs, as);
		if (type.equals("2")) {
			return this.getPrintList(list, "|");
		}
		if (type.equals("3") || type.equals("4")) {
			String[] inserts = tbsHttpRequestBean.getInserts().split("\\|");
			if (type.equals("3")) {
				return this.getInserts(inserts, list, false);
			}else{
				return this.getInserts(inserts, list, true);
			}
		}
		String listUrlRegex = tbsHttpRequestBean.getListUrlRegex();
		if (type.equals("5")) {
			return this.getPrintListData(this.getListData(this.getGroupArray(listUrlRegex), list));
		}
		String result = null;
		List<String[]> data = this.getListData(this.getGroupArray(listUrlRegex), list);
		Integer pageId = tbsHttpRequestBean.getListPageId();
		String splitUrlChar = tbsHttpRequestBean.getListSplitUrlChar();
		String splitUrl = tbsHttpRequestBean.getListSplitUrl();
		Integer testLinne = tbsHttpRequestBean.getListUrlTest();
		System.out.println("pageId:" + pageId + "|splitUrlChar:" + splitUrlChar + "|splitUrl:" + splitUrl + "|testLinne:" + testLinne);
		String testUrl = data.get(0)[testLinne]; // 取数据
		System.out.println("testUrl:" + testUrl);
		try {
			result = this.getUrlSplist(testUrl, splitUrlChar, splitUrl, pageId);
		} catch (Exception e) {
			e.printStackTrace();
			return "参数设置有误" + e.toString();
		}
		if (type.equals("6")) {
			return result;
		}
		if (type.equals("7")) {
			System.out.println("sendUrl:" + result);
			return new HtmlJsoup().getPage(result, null, null, null, encoding, encoding).html();
		}
		rs = tbsHttpRequestBean.getListRegexs().split("\\|");
		as = tbsHttpRequestBean.getListAttrSplit().split("\\|");
		list = this.getList(result, null, null, null, encoding, encoding, rs, as);
		if (type.equals("8")) {
			return this.getPrintList(list, "|");
		}
		if (type.equals("9") || type.equals("18")) {
			String[] inserts = tbsHttpRequestBean.getListInserts().split("\\|");
			if (type.equals("9")) {
				return this.getInserts(inserts, list, false);
			} else {
				return this.getInserts(inserts, list, true);
			}
		}
		String contUrlRegx=tbsHttpRequestBean.getContUrlRegex();
		data=this.getListData(this.getGroupArray(contUrlRegx),list);
		if(type.equals("10")){
			return this.getPrintListData(data);
		}
		Integer contUrlTest=tbsHttpRequestBean.getContUrlTest(); 
		testUrl=data.get(0)[contUrlTest];
		if(type.equals("11")){
			System.out.println("sendUrl:" + testUrl);
			return new HtmlJsoup().getPage(testUrl, null, null, null, encoding, encoding).html();
		}
		rs = tbsHttpRequestBean.getContRegexs().split("\\|");
		as = tbsHttpRequestBean.getContAttrSplit().split("\\|");
		list = this.getList(testUrl, null, null, null, encoding, encoding, rs, as);
		if(type.equals("12")){
			return this.getPrintList(list, "|");
		}
		if(type.equals("13") || type.equals("14")){
			String[] inserts = tbsHttpRequestBean.getContInserts().split("\\|");
			if(type.equals("13")){
			    return this.getInserts(inserts, list, false);
			}else{
				return this.getInserts(inserts, list, true);
			}
		}
		return null;
	}

   private String getThreadPool(final TbsHttpRequestBean tbsHttpRequestBean){
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		if(threadPool.isTerminated()){
			return "线程正在运行!";
		}
		threadPool.execute(new Runnable() {
			public void run() {
				HCPool hcp = new HCPool();
				String encoding = tbsHttpRequestBean.getEncoding();
				System.out.println("encoding:"+encoding);
				List<String[][]> list = getList(tbsHttpRequestBean.getFirstPage(), null, null, null, encoding, encoding, tbsHttpRequestBean.getRegexs().split("\\|"), tbsHttpRequestBean.getArrtSplit().split("\\|"));
				String[] urls=getListData(getGroupArray(tbsHttpRequestBean.getListUrlRegex()), list).get(0);
				//List<Future<String>> listFuture = hcp.httpPool(urls, null, null, null, encoding, 0, 0);
				Integer pageId=tbsHttpRequestBean.getListPageId();
				String[] regexs=tbsHttpRequestBean.getListRegexs().split("\\|");
				String[] attrs=tbsHttpRequestBean.getListAttrSplit().split("\\|");
				String[] conRegexs=tbsHttpRequestBean.getContRegexs().split("\\|");
				String[] conAttrs=tbsHttpRequestBean.getContAttrSplit().split("\\|");
				String[] conInserts=tbsHttpRequestBean.getContInserts().split("\\|");
				for (int i = 0; i < urls.length; i++) {
					System.out.println("urls[i]:" + urls[i]);
					Integer listPageId=pageId;
					while (true) {
						/*if (i < 2)
							break;*/
						System.out.println("-------------listPageId:" + listPageId);
						String pageUrl = getUrlSplist(urls[i], tbsHttpRequestBean.getListSplitUrlChar(), tbsHttpRequestBean.getListSplitUrl(), listPageId);
						System.out.println("-------------pageUrl:" + pageUrl);
						list = getList(pageUrl, null, null, null, null, null, regexs, attrs);
						String[] contentUrls = getListData(getGroupArray(tbsHttpRequestBean.getContUrlRegex()), list).get(0);
						if (contentUrls.length < 1) {
							System.out.println("最后一页:listPageId:" + (listPageId - 1)+"|urls[i]:"+urls[i]);
							break;
						}
						Queue<Future<Content>> qfs = hcp.httpQueue(contentUrls, null, null, null, encoding, 0, 0);
						for (int j = 0; j < qfs.size(); j++) {
							String html = null;
							try {
								Future<Content> fc;
								while((fc=qfs.poll()) != null){
									//System.out.println(fc.get().asString());
									//html = IOUtils.toString(fc.get().asString().getBytes(""), encoding);
									html = fc.get().asString();
									if (html != null) {
										List<String[][]> ls = getList(html, conRegexs, conAttrs);
										getInserts(conInserts, ls, true);
									}
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (ExecutionException e) {
								e.printStackTrace();
							}
						}
						listPageId++;
						//listPageId = listPageId + 5;
					}
				}
			}
		});
		threadPool.shutdown();
		return "启动线程成功请查看数据库!";
   }
   public void getLooped(String url,Integer pageId,String listSplitUrlChar,String listSplitUrl){
	   /* list = getList(url, null, null, null, null, null, regexs, attrs);
		List<String[]> listContent = getListData(getGroupArray(tbsHttpRequestBean.getContUrlRegex()), list);
		String[][] listContentSort = getListDateSort(listContent);
		for (int a = 0; a < listContentSort.length; a++) {
			for (int b = 0; b < listContentSort[a].length; b++) {
				System.out.println("pageId:" + listContentSort[a][b]);
			}
		}*/
   }
	   
    
	private String getUrlSplist(String testUrl, String listSplitUrlChar, String listSplitUrl, Integer pageId) {
		StringBuffer sb = new StringBuffer();
		String[] splitStr = testUrl.split("\\" + listSplitUrlChar);
		String[] splitStr1 = listSplitUrl.split("\\+");
		for (int i = 0; i < splitStr1.length; i++) {
			if (splitStr1[i].equals("/")) {
				sb.append("/");
			} else if (splitStr1[i].equals("(*)")) {
				sb.append(pageId);
			} else if (splitStr1[i].equals("//")) {
				sb.append("//");
			} else {
				sb.append(splitStr[Integer.parseInt(splitStr1[i])]);
			}
		}
		return sb.toString();
	}

	private String getPrintListData(List<String[]> data) {
		StringBuffer sb = new StringBuffer();
		sb.append("\r");
		for (int i = 0; i < data.size(); i++) {
			String[] array = data.get(i);
			for (int j = 0; j < array.length; j++) {
				sb.append(array[j]);
				sb.append("\r");
			}
		}
		return sb.toString();
	}

	private String getPrintList(List<String[][]> list, String splitChar) {
		StringBuffer sb = new StringBuffer();
		sb.append("\r");
		for (int i = 0; i < list.size(); i++) {
			String[][] array = list.get(i);
			for (int j = 0; j < array.length; j++) {
				for (int x = 0; x < array[j].length; x++) {
					sb.append(array[j][x] + splitChar);
				}
				sb = sb.delete(sb.length() - 1, sb.length());
				sb.append("\r");
			}
		}
		return sb.toString();
	}
	
	private List<String[][]> getList(String html,String[] regexs, String[] attrs){
		Elements es=Jsoup.parse(html).getAllElements();
		List<String[][]> list = new ArrayList<String[][]>();
		for (int i = 0; i < regexs.length; i++) {
			System.out.println("regex:" + regexs[i]);
			list.add(HtmlJsoup.getArrtArray(HtmlJsoup.getPage(es, regexs[i]), attrs[i].split("\\,")));
		}
		return list;
	}

	private List<String[][]> getList(String url, String method, Map<?, ?> headerMap, Map<?, ?> paramsMap, String requestEncoding, String resultEncoding, String[] regexs, String[] attrs) {
		Elements es = new HtmlJsoup().getPageAllElements(url, method, headerMap, paramsMap, requestEncoding, resultEncoding);
		List<String[][]> list = new ArrayList<String[][]>();
		for (int i = 0; i < regexs.length; i++) {
			System.out.println("regex:" + regexs[i]);
			list.add(HtmlJsoup.getArrtArray(HtmlJsoup.getPage(es, regexs[i]), attrs[i].split("\\,")));
		}
		return list;
	}
	private List<String[]> getListData(List<String[]> groupArr, List<String[][]> list) {
		List<String[]> listData = new ArrayList<String[]>();
		for (int a = 0; a < groupArr.size(); a++) {
			String[] groupData = groupArr.get(a);
			String[][] array = list.get(Integer.parseInt(groupData[0]));
			System.out.println("column:" + Integer.parseInt(groupData[1]) + "|line:" + Integer.parseInt(groupData[0]) + "|list.size:" + list.size()+"|array.length:" + array.length);
			String[] data = new String[array.length];
			for (int j = 0; j < array.length; j++) {
				data[j] = array[j][Integer.parseInt(groupData[1])];
			}
			listData.add(data);
		}
		return listData;
	}
	private String[][] getListDateSort(List<String[]> listData) {
		String[][] reDate = null;
		int listSize = listData.size();
		for (int b = 0; b < listSize; b++) {
			String[] data = listData.get(b);
			if (b < 1)reDate = new String[data.length][listSize];
			for (int c = 0; c < data.length; c++) {
				reDate[c][b] = data[c];
			}
		}
		return reDate;
	}
	private List<String[]> getGroupArray(String customRegex) {
		List<String[]> listArray = new ArrayList<String[]>();
		if (null != customRegex)customRegex = customRegex.trim();
		String centerStrRep = customRegex.replaceAll("\\{|\\}", "");
		String[] centerStrRepSplit = centerStrRep.split("\\,");
		for (int j = 0; j < centerStrRepSplit.length; j++) {
			String[] sp = centerStrRepSplit[j].split("\\-");
			listArray.add(sp);
		}
		return listArray;
	}
	public String getInserts(String[] inserts, List<String[][]> list, boolean flag) {
		StringBuffer sb = new StringBuffer();
		sb.append("\r");
		for (int i = 0; i < inserts.length; i++) {
			int start = inserts[i].indexOf('{');
			int end = inserts[i].lastIndexOf('}') + 1;
			String startStr = inserts[i].substring(0, start); // 开始字符串
			String endStr = inserts[i].substring(end, inserts[i].length());// 结束字符串
			String regexStr = inserts[i].substring(start, end);// 表达式
			System.out.println("startStr:" + startStr + "|endStr:" + endStr + "regexStr" + regexStr);
			List<String[]> groupArr = this.getGroupArray(regexStr); // 分割组
			List<String[]> listData = this.getListData(groupArr, list); // 获取数据
			String[][] reDate = this.getListDateSort(listData); // 排序
			for (int j = 0; j < reDate.length; j++) {
				String sql = "";
				if (startStr.indexOf("[id]") != -1) {
					sql = startStr.replaceAll("\\[id\\]", Long.toString(util.getUid()));
				} else {
					sql = startStr;
				}
				for (int x = 0; x < reDate[j].length; x++) {
					sql += "'" + reDate[j][x] + "',";
				}
				sql = sql.substring(0, sql.length() - 1);
				sql += endStr;
				if (flag) {
					//DBFactory.getDBDaoEx().getInsert(this.sqlSessionFactory.openSession().getConnection(), sql);
					System.out.println("sql:"+sql);
					//DBFactory.getDBDaoEx().getInsert(this.sqlSessionFactory.openSession().getConnection(), sql);
					try {
						tbsHttpRequestService.insertBySql(sql);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 入库操作
				}else{
				  sb.append(sql);
				  sb.append("\r");
				}
			}
		}
		return sb.toString();
	}

	public void test() {
		String url = "http://www.yy606.com/about/siteMap.shtml";
		String encodeing = "UTF-8";
		String[] rs = new String[] { "html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a", "html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a" };
		String[] as = new String[] { "target,href", "text" };
		List<String[][]> list = this.getList(url, null, null, null, encodeing, encodeing, rs, as);
		System.out.println("list.size:" + list.size());
		// String[] is=tbsHttpRequestBean.getInserts().split("\\|");
		String inserts = "INSERT INTO TbcTemp(id,href,text) VALUES('[id]',{1-0},{0-1})|INSERT INTO TbcTemp(id,href,text) VALUES({0-0})";
		this.getInserts(inserts.split("\\|"), list, false);
	}
   
	public void test1(){
		TbsHttpRequestControllerAdmin admin = new TbsHttpRequestControllerAdmin();
		//admin.test();
		String[] rs="html>body>div[class=container]>div[class=index_left04 fl]>div:eq(1)>div:eq(0)>h2>strong".split("\\|");
		String[] as="text".split("\\|");
		String encoding="UTF-8";
		String url="http://www.yy606.com/view/2013031419174481704.shtml";
		List<String[][]> list = admin.getList(url, null, null, null, encoding, encoding, rs, as);
		System.out.println(admin.getPrintList(list, "|"));
	}
	
	public void test2() throws InterruptedException, ExecutionException{
		TbsHttpRequestControllerAdmin admin = new TbsHttpRequestControllerAdmin();
		String url = "http://www.yy606.com/about/siteMap.shtml";
		String encodeing = "UTF-8";
		String[] rs = new String[] { "html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a", "html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a" };
		String[] as = new String[] { "href", "text" };
		List<String[][]> list = admin.getList(url, null, null, null, encodeing, encodeing, rs, as);
		List<String[]> data = admin.getListData(admin.getGroupArray("{0-0}"), list);
		System.out.println(admin.getPrintListData(data));
		HCPool hcPool=new HCPool();
		for(int i=0;i<data.size();i++){
			/*List<Future<String>> callable= hcPool.httpGetPool(data.get(i), null, null, 0, 0);
			for(int j=0;j<callable.size();j++){
				System.out.println(callable.get(j).get());
				System.out.println("j:"+j);
			}*/
			Queue<Future<Content>> queue=hcPool.httpQueue(data.get(i), null, null, null, null, 0, 0);
			for(int j=0;j<queue.size();j++){
				Future<Content> str;
				while((str=queue.poll())!=null){
					System.out.println("result:"+str.get().asString());
				}
			}
		}
		System.out.println("list.size:" + data.size());
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		TbsHttpRequestControllerAdmin admin = new TbsHttpRequestControllerAdmin();
		TbsHttpRequestBean tbsHttpRequestBean=new TbsHttpRequestBean();
		tbsHttpRequestBean.setFirstPage("http://www.yy606.com/about/siteMap.shtml");
		tbsHttpRequestBean.setEncoding("utf-8");
		tbsHttpRequestBean.setRegexs("html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a|html>body>div[class=container]>div[class=border]>div>ul>li:eq(0)>dl>dd>a");
		tbsHttpRequestBean.setArrtSplit("href,text|target");
		tbsHttpRequestBean.setInserts("INSERT INTO TbcTemp(id,href,text) VALUES([id],{0-0},{0-1});|INSERT INTO TbcTemp(id,href,text) VALUES([id],{0-0},{1-0});");
		
		tbsHttpRequestBean.setListUrlRegex("{0-0}");
		tbsHttpRequestBean.setListPageId(1);
		tbsHttpRequestBean.setListUrlTest(0);
		tbsHttpRequestBean.setListSplitUrlChar("/");
		tbsHttpRequestBean.setListSplitUrl("0+/+/+1+2+/+3+/+4+/+(*)");
		tbsHttpRequestBean.setListRegexs("html>body>div[class=container]>div[class=index_left04 fl]>div[class=list_ulli border]>div[class=list_l_01]>div[class=list_l_04]>div[class=fl]>a|html>body>div[class=container]>div[class=index_left04 fl]>div[class=list_ulli border]>div[class=list_l_01]>div[class=list_l_04]>div[class=fl]>a>span");
		tbsHttpRequestBean.setListAttrSplit("href,text|text");
		tbsHttpRequestBean.setListInserts("INSERT INTO TbcListTemp(id,href,text) VALUES([id],{0-0},{0-1});|INSERT INTO TbcListTemp(id,text) VALUES([id],{1-0});");
		
		tbsHttpRequestBean.setContUrlRegex("{0-0}");
		tbsHttpRequestBean.setContUrlTest(0);
		tbsHttpRequestBean.setContRegexs("html>body>div.container>div.index_left04>div.index_left07a>span.fl>a:eq(2)|html>body>div.container>div.index_left04>div.border>div.news_dt06>h2>strong|html>body>div.container>div.index_left04>div.border>div.news_dt06>p:eq(4)|html>body>div.container>div.index_left04>div.border>div.news_dt06>center>div>img");
		tbsHttpRequestBean.setContAttrSplit("text|text|text|src");
		tbsHttpRequestBean.setContInserts("INSERT INTO TbcConTemp(id,TYPE,title,image,TEXT) VALUES([id],{0-0},{1-0},{2-0},{3-0});");
		
		System.out.println("getThreadPool:"+admin.getThreadPool(tbsHttpRequestBean));
	}
}