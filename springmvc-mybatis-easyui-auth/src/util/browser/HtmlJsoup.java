
package util.browser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <br>
 * <b>功能：</b>HTML解析类<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-1-25 <br>
 * <b>版权所有：<b>版权所有(C) 2013 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class HtmlJsoup {
	public Document getPage(String url, String method, Map<?, ?> headerMap,Map<?, ?> paramsMap,String requestEncoding,String resultEncoding){
		   String html=new HC().doGet(url, method, headerMap, paramsMap, requestEncoding, resultEncoding);
		   return Jsoup.parse(html);
	}
	public Elements getPageAllElements(String url, String method, Map<?, ?> headerMap,Map<?, ?> paramsMap,String requestEncoding,String resultEncoding){
		   String html=new HC().doGet(url, method, headerMap, paramsMap, requestEncoding, resultEncoding);
		   return Jsoup.parse(html).getAllElements();
	} 
	public Elements getPage(String select,String url, String method, Map<?, ?> headerMap,Map<?, ?> paramsMap,String requestEncoding,String resultEncoding){
		   String html=new HC().doGet(url, method, headerMap, paramsMap, requestEncoding, resultEncoding);
		   Document doc= Jsoup.parse(html);
		   return doc.select(select);
	}
    public static Elements getPage(Elements es,String select){
    	return es.select(select);
    }
	public static List<List<String>> getArrt(Elements elements,String ...attrNames){
		List<List<String>> list=new ArrayList<List<String>>();
		for(int i=0;i<attrNames.length;i++){
			List<String> li=new ArrayList<String>();
			for(Element element : elements){
				if("text".equals(attrNames[i])){
					li.add(element.text());
				}else{
					li.add(element.attr(attrNames[i]));
				}
			}
			list.add(li);
	    }
		return list;
	}
	
	public static String[][] getArrtArray(Elements elements,String ...attrNames){
		int line=elements.size();
		int column=attrNames.length;
		System.out.println("line:"+line+"|column:"+column);
		String[][] array=new String[line][column];
		int j=0;
		for(Element element : elements){
			for(int i=0;i<attrNames.length;i++){
				if("text".equals(attrNames[i])){
					array[j][i]=element.text();
					continue;
				}
				if("ownText".equals(attrNames[i])){
					array[j][i]=element.ownText();
					continue;
				}
				if("html".equals(attrNames[i])){
					array[j][i]=element.html();
					continue;
				}
				if("outerHtml".equals(attrNames[i])){
					array[j][i]=element.outerHtml();
					continue;
				}
				if("document".equals(attrNames[i])){
					array[j][i]=String.valueOf(element.ownerDocument());
					continue;
				}
				array[j][i]=element.attr(attrNames[i]);
			}
			j++;
	    }
		return array;
	}
	
	public void test(String url, String method, Map<?, ?> headerMap,Map<?, ?> paramsMap,String requestEncoding,String resultEncoding){
		   String html=new HC().doGet(url, method, headerMap, paramsMap, requestEncoding, resultEncoding);
		   Document doc= Jsoup.parse(html);
		   String regex="html>body>div.container>div.index_left04>div.border>div.news_dt06>p:eq(4)";
		   Elements es=doc.select(regex);
		   System.out.println("es.size:"+es.size()+"|es.html:"+es.html());
		   for(Element element : es){
	    	   String href=element.attr("href");
	    	   String text=element.text();
	    	   System.out.println("element.ownText():"+element.ownText());
	    	   System.out.println("element.html():"+element.html());
	    	   System.out.println("href:"+href+"|text:"+text);
	    	   System.out.println("element.ownerDocument():"+String.valueOf(element.ownerDocument()));
	       }
		   
	}
	public static void main(String[] args) {
		HtmlJsoup  hj=new HtmlJsoup();
		hj.test("http://www.yy606.com/view/2013031419174481704.shtml", null, null, null, "UTF-8", "UTF-8");
	}
}

