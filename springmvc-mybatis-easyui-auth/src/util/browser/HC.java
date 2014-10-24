
package util.browser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * <br>
 * <b>功能：</b>模拟浏览器<br>
 * <b>作者：</b>Wolf<br>
 * <b>日期：</b> 2013-1-24 <br>
 * <b>版权所有：<b>版权所有(C) 2013 QQ 405645010<br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class HC {
	private Logger log = Logger.getLogger(HC.class);
	private HttpClient hc;
	private HttpResponse hr;
	private HttpPost hp;
	private HttpGet hg;
	/**
	 * 
	 * <br>
	 * <b>功能：</b>请求后关闭资源<br>
	 * <b>作者：</b>wolf<br>
	 * <b>日期：</b> 2013-1-24 <br>
	 * @param url
	 * @param method
	 * @param headerMap
	 * @param paramsMap
	 * @param resultEncoding
	 * @return
	 */
	public String doGet(String url, String method, Map<?, ?> headerMap,Map<?, ?> paramsMap,String requestEncoding,String resultEncoding){
		  hc=new DefaultHttpClient();
		  hc.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
		  hc.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,1000*20);  //连接时间  20秒
		  hc.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,1000*30);          //读取内容时间   30秒
		  hc.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);  //Cook 兼容模式
		  String result=null;
		  try {
				hr = this.doGetOrPost(url, method, headerMap, paramsMap,requestEncoding,resultEncoding);
				int statusCode = hr.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					HttpEntity he = hr.getEntity();
					if (null != he) {
						//he= new BufferedHttpEntity(he); 
						if (null != resultEncoding) {
							result = EntityUtils.toString(he,resultEncoding);
						} else {
							result = EntityUtils.toString(he);
						}
					}
					if (null != result) {
						result = result.trim();
					}
				} else {
					Header locationHeader = hr.getFirstHeader("Location");
					if (null != locationHeader) {
						String location = locationHeader.getValue();
						log.info("页面成功重定向地址locationHeader:" + locationHeader);
						this.doGetOrPost(location, method, headerMap,paramsMap,requestEncoding,resultEncoding);
					} else {
						log.error("错误的重定向地址locationHeader:" + locationHeader);
					}
				}
			} catch (Exception e) {
				log.info("请求异常:e:" + e.toString());
				e.printStackTrace();
			} finally {
				if (hp != null) {
					hp.abort();
				}
				if (hg != null) {
					hg.abort();
				}
				hc.getConnectionManager().shutdown();
			}
			return result;
	}
    /**
     * 
     * <br>
     * <b>功能：</b>提供给系统调用<br>
     * <b>作者：</b>wolf<br>
     * <b>日期：</b> 2013-1-24 <br>
     * @param url
     * @param method
     * @param headerMap
     * @param paramsMap
     * @param resultEncoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
	private HttpResponse doGetOrPost(String url, String method, Map<?, ?> headerMap,Map<?, ?> paramsMap,String requestEncoding, String resultEncoding) throws ClientProtocolException, IOException{
		if(null==resultEncoding) resultEncoding="UTF-8";
		if(null==requestEncoding) requestEncoding="UTF-8";
		if (null == url || url.trim().length() < 1) {
			log.info("httpClient|url有误:" + url);
			return null;
		}
		/**********判断Get或Post方法执行  默认执行Get方法***********/
		/****执行POST请求***/
		if (null != method && method.toLowerCase().equals("post")) { 
			hp = new HttpPost(url);
			if (null != headerMap) {
				for (Map.Entry<?, ?> entity : headerMap.entrySet()) {
					hp.addHeader(entity.getKey().toString(), entity.getValue().toString());
				}
			}
			if (null != paramsMap) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (Map.Entry<?, ?> entity : paramsMap.entrySet()) {
					params.add(new BasicNameValuePair(entity.getKey().toString(), entity.getValue().toString()));
				}
				UrlEncodedFormEntity from = new UrlEncodedFormEntity(params,requestEncoding);
				hp.setEntity(from);
			}
			return hc.execute(hp);
		} else { 
			/****执行Get请求***/
			if (null != paramsMap) {
				StringBuffer sb = new StringBuffer();
				String params;
				if (url.indexOf("?") == -1) { // 判断?号
					sb.append("?");
				} else {
					sb.append("&");
				}
				for (Map.Entry<?, ?> entity : paramsMap.entrySet()) {
					sb.append(entity.getKey()).append("=").append(entity.getValue()).append("&");
				}
				params = sb.substring(0, sb.length() - 1);
				url = url + params;
			}
			hg = new HttpGet(url);
			if (null != headerMap) {
				for (Map.Entry<?, ?> entity : headerMap.entrySet()) {
					hg.addHeader(entity.getKey().toString(), entity.getValue().toString());
				}
			}
			return hc.execute(hg);
		}
	}
}

