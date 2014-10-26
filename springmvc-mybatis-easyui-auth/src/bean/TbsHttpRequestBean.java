package bean;
public class TbsHttpRequestBean extends BaseBean {

	private String id; //序列号
	private String name; //名称
	private String header; //请求头信息(JSON格式)
	private String firstPage; //所有分类地址
	private String encoding; //编码
	private String listName; //列表过滤
	private String regexs; //jquery表达式
	private String arrtSplit; //属性
	private String inserts; //入库语句
	private String listUrlRegex; //请求属性
	private Integer listUrlTest; //测试行数
	private Integer listPageId; //开始页
	private String listSplitUrlChar; //分割符
	private String listSplitUrl; //分页表达式
	private String listRegexs; //jquery表达式
	private String listAttrSplit; //属性
	private String listInserts; //入库语句
	private String contUrlRegex; //URL表达式
	private Integer contUrlTest; //测试Url
	private String contRegexs; //选择器
	private String contAttrSplit; //熟悉
	private String contInserts; //入库表达式
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>序列号<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return id
	 */
	public String getId(){
	   return id;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>序列号<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param id
	 */
	public void setId(String id){
	   this.id=id;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>名称<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return name
	 */
	public String getName(){
	   return name;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>名称<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param name
	 */
	public void setName(String name){
	   this.name=name;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>请求头信息(JSON格式)<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return header
	 */
	public String getHeader(){
	   return header;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>请求头信息(JSON格式)<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param header
	 */
	public void setHeader(String header){
	   this.header=header;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>所有分类地址<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return firstPage
	 */
	public String getFirstPage(){
	   return firstPage;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>所有分类地址<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param firstPage
	 */
	public void setFirstPage(String firstPage){
	   this.firstPage=firstPage;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>编码<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return encoding
	 */
	public String getEncoding(){
	   return encoding;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>编码<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param encoding
	 */
	public void setEncoding(String encoding){
	   this.encoding=encoding;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>列表过滤<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return listName
	 */
	public String getListName(){
	   return listName;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>列表过滤<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param listName
	 */
	public void setListName(String listName){
	   this.listName=listName;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>jquery表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return regexs
	 */
	public String getRegexs(){
	   return regexs;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>jquery表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param regexs
	 */
	public void setRegexs(String regexs){
	   this.regexs=regexs;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>属性<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return arrtSplit
	 */
	public String getArrtSplit(){
	   return arrtSplit;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>属性<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param arrtSplit
	 */
	public void setArrtSplit(String arrtSplit){
	   this.arrtSplit=arrtSplit;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>入库语句<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return inserts
	 */
	public String getInserts(){
	   return inserts;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>入库语句<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param inserts
	 */
	public void setInserts(String inserts){
	   this.inserts=inserts;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>请求属性<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return listUrlRegex
	 */
	public String getListUrlRegex(){
	   return listUrlRegex;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>请求属性<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param listUrlRegex
	 */
	public void setListUrlRegex(String listUrlRegex){
	   this.listUrlRegex=listUrlRegex;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>测试行数<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return listUrlTest
	 */
	public Integer getListUrlTest(){
	   return listUrlTest;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>测试行数<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param listUrlTest
	 */
	public void setListUrlTest(Integer listUrlTest){
	   this.listUrlTest=listUrlTest;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>开始页<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return listPageId
	 */
	public Integer getListPageId(){
	   return listPageId;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>开始页<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param listPageId
	 */
	public void setListPageId(Integer listPageId){
	   this.listPageId=listPageId;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>分割符<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return listSplitUrlChar
	 */
	public String getListSplitUrlChar(){
	   return listSplitUrlChar;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>分割符<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param listSplitUrlChar
	 */
	public void setListSplitUrlChar(String listSplitUrlChar){
	   this.listSplitUrlChar=listSplitUrlChar;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>分页表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return listSplitUrl
	 */
	public String getListSplitUrl(){
	   return listSplitUrl;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>分页表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param listSplitUrl
	 */
	public void setListSplitUrl(String listSplitUrl){
	   this.listSplitUrl=listSplitUrl;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>jquery表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return listRegexs
	 */
	public String getListRegexs(){
	   return listRegexs;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>jquery表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param listRegexs
	 */
	public void setListRegexs(String listRegexs){
	   this.listRegexs=listRegexs;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>属性<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return listAttrSplit
	 */
	public String getListAttrSplit(){
	   return listAttrSplit;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>属性<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param listAttrSplit
	 */
	public void setListAttrSplit(String listAttrSplit){
	   this.listAttrSplit=listAttrSplit;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>入库语句<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return listInserts
	 */
	public String getListInserts(){
	   return listInserts;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>入库语句<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param listInserts
	 */
	public void setListInserts(String listInserts){
	   this.listInserts=listInserts;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>URL表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return contUrlRegex
	 */
	public String getContUrlRegex(){
	   return contUrlRegex;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>URL表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param contUrlRegex
	 */
	public void setContUrlRegex(String contUrlRegex){
	   this.contUrlRegex=contUrlRegex;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>测试Url<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return contUrlTest
	 */
	public Integer getContUrlTest(){
	   return contUrlTest;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>测试Url<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param contUrlTest
	 */
	public void setContUrlTest(Integer contUrlTest){
	   this.contUrlTest=contUrlTest;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>选择器<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return contRegexs
	 */
	public String getContRegexs(){
	   return contRegexs;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>选择器<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param contRegexs
	 */
	public void setContRegexs(String contRegexs){
	   this.contRegexs=contRegexs;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>熟悉<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return contAttrSplit
	 */
	public String getContAttrSplit(){
	   return contAttrSplit;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>熟悉<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param contAttrSplit
	 */
	public void setContAttrSplit(String contAttrSplit){
	   this.contAttrSplit=contAttrSplit;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>入库表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return contInserts
	 */
	public String getContInserts(){
	   return contInserts;
	}
	/**
	 * 
	 * <br>
	 * <b>功能：</b>入库表达式<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @param contInserts
	 */
	public void setContInserts(String contInserts){
	   this.contInserts=contInserts;
	}
    
	/**
	 * 
	 * <br>
	 * <b>功能：</b>重写<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-26 <br>
	 * @return
	 */
    public String toString(){
	   return "id:"+id+"|name:"+name+"|header:"+header+"|firstPage:"+firstPage+"|encoding:"+encoding+"|listName:"+listName+"|regexs:"+regexs+"|arrtSplit:"+arrtSplit+"|inserts:"+inserts+"|listUrlRegex:"+listUrlRegex+"|listUrlTest:"+listUrlTest+"|listPageId:"+listPageId+"|listSplitUrlChar:"+listSplitUrlChar+"|listSplitUrl:"+listSplitUrl+"|listRegexs:"+listRegexs+"|listAttrSplit:"+listAttrSplit+"|listInserts:"+listInserts+"|contUrlRegex:"+contUrlRegex+"|contUrlTest:"+contUrlTest+"|contRegexs:"+contRegexs+"|contAttrSplit:"+contAttrSplit+"|contInserts:"+contInserts;
    }

    ///////////////////////////增加/////////////////////////

}
