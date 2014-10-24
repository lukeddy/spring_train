
package util.browser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.cyberneko.html.parsers.DOMParser;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Document;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import com.sun.org.apache.xpath.internal.XPathAPI;

/**
 * <br>
 * <b>功能：</b>类功能描述<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-1-24 <br>
 * <b>版权所有：<b>版权所有(C) 2013 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class HokeHtml {
	public String getPage(String url, String encoding) throws SAXException, IOException {
        DOMParser parse=new DOMParser();
        parse.setProperty("http://cyberneko.org/html/properties/default-encoding", encoding);
        parse.setFeature("http://xml.org/sax/features/namespaces", false);
        parse.parse(url);
        org.w3c.dom.Document document=parse.getDocument();
        DOMReader domReasder=new DOMReader();
        org.dom4j.Document documnet=domReasder.read(document);
        System.out.println(documnet.getStringValue());
		return documnet.asXML();
	}
	public org.dom4j.Document getPage(String url, String method, Map<?, ?> headerMap,Map<?, ?> paramsMap,String requestEncoding,String resultEncoding) throws SAXException, IOException{
		if(null==resultEncoding)resultEncoding="UTF-8";
		String result=new HC().doGet(url, method, headerMap, paramsMap, requestEncoding, resultEncoding);
		if(null==result) return null;
		DOMParser parse=new DOMParser();
		InputStream in=new ByteArrayInputStream(result.getBytes(resultEncoding)); //string to inputstream
		parse.setProperty("http://cyberneko.org/html/properties/default-encoding", resultEncoding);
        parse.setFeature("http://xml.org/sax/features/namespaces", false);
		parse.parse(new InputSource(in));
		in.close();
		org.w3c.dom.Document doc=parse.getDocument();
		
	    DOMReader domReasder=new DOMReader();
	    //org.dom4j.Document document=domReasder.read(doc);
	    return domReasder.read(doc);
	}
	
	public static void main(String[] args) throws SAXException, IOException, DocumentException {
		HokeHtml hh=new HokeHtml();
		try {
			hh.test();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//org.dom4j.Document doc=hh.getPage("http://www.baidu.com", null, null, null, "UTF-8","UTF-8");
	}
	
	public void test() throws SAXException, IOException, TransformerException{
		    String url="http://www.yy606.com";
		    DOMParser parse=new DOMParser();
	        parse.setProperty("http://cyberneko.org/html/properties/default-encoding", "utf-8");
	        parse.setFeature("http://xml.org/sax/features/namespaces", false);
	        parse.parse(url);
	        org.w3c.dom.Document doc=parse.getDocument();
	        System.out.println(doc.getNodeValue());
	        org.w3c.dom.NodeList nodeList=XPathAPI.selectNodeList(doc, "/HTML/DOBY/*");
	        System.out.println(nodeList.getLength());
	}
	
}

