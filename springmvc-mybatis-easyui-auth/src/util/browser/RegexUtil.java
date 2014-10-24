package util.browser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-1-25 <br>
 * <b>版权所有：<b>版权所有(C) 2011，QQ 405645010<br>
 */
public class RegexUtil {
    /**
     * 
     * <br>
     * <b>功能：</b>方法功能描述<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2013-1-25 <br>
     * @param str
     * @param regex 
     * @return
     */
	public static List<String> getRegexList(String str,String regex){
  	      Pattern pattern = Pattern.compile(regex);
		  Matcher matcher = pattern.matcher(str);
		  List<String> list=new ArrayList<String>();
		  String location=null;
		  while(matcher.find()) {
			 location = matcher.group(0);
			 list.add(location);
		  }
		  return list;
    }
	  /**
	   * 字符串切割
	   * @param startOffset 
	   * @param endOffset 
	   * @param offset 默认为 null    1为从后面开始索引
	   * @return
	  */
	public  static String subString(String startOffset,String endOffset,String string,Integer offset){
		  String result=null;
		  String tmp=null;
		  try{
			  int start=startOffset.length();
			  int len=string.length();
			  if(null!=offset && 0!=offset){
				  result=string.substring(string.indexOf(startOffset)+start,len);
				  result=result.substring(0, result.indexOf(endOffset));
				  return result;
			  }
			  result=string.substring(string.indexOf(startOffset)+start,len);
			  tmp=result;
			  result=tmp.substring(0, tmp.indexOf(endOffset));
		  }catch(Exception e){
			  e.printStackTrace();
			  return null;
		  }
		  return result;
	  }
}
