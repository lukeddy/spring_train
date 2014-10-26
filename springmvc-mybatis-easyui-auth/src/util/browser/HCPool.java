package util.browser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HCPool {

	private final static Logger log= Logger.getLogger(HCPool.class);
	/**
	 * 
	 * <br>
	 * <b>功能：</b>队列调用<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-4-1 <br>
	 * @param urls
	 * @param method
	 * @param headerMap
	 * @param paramsMap
	 * @param encoding
	 * @param conTimeOut
	 * @param soTimeOut
	 * @return
	 */
	public Queue<Future<Content>> httpQueue(String[] urls,String method, Map<?, ?> headerMap, Map<?, ?> paramsMap, String encoding, Integer conTimeOut, Integer soTimeOut){
		ExecutorService threadpool = Executors.newFixedThreadPool(1);
		if (null == encoding)
			encoding = "UTF-8";
		if (conTimeOut < 1)
			conTimeOut = 1000 * 20;
		if (soTimeOut < 1)
			soTimeOut = 1000 * 30;
		if (null == method || method.length() < 1)
			method = "get";
		int length=urls.length;
    	Async async = Async.newInstance().use(threadpool);
    	Request[] requests=new Request[length];
    	for(int i=0;i<urls.length;i++){
    		Request request = null;
    		if (method.equals("get")) {
    			if (null != paramsMap) {
    				StringBuffer sb = new StringBuffer();
    				String params;
    				if (urls[i].indexOf("?") == -1) { // 判断?号
    					sb.append("?");
    				} else {
    					sb.append("&");
    				}
    				for (Map.Entry<?, ?> entity : paramsMap.entrySet()) {
    					sb.append(entity.getKey()).append("=").append(entity.getValue()).append("&");
    				}
    				params = sb.substring(0, sb.length() - 1);
    				urls[i] = urls[i] + params;
    			}
    			requests[i]=Request.Get(urls[i]).connectTimeout(conTimeOut).socketTimeout(soTimeOut);
    		}
    		if (method.equals("post")) {
    			request = Request.Post(urls[i]).connectTimeout(conTimeOut).socketTimeout(soTimeOut);
    			if (null != paramsMap) {
    				for (Map.Entry<?, ?> entity : paramsMap.entrySet()) {
    					request.bodyForm(new BasicNameValuePair(entity.getKey().toString(), entity.getValue().toString()));
    				}
    			}
    		}
    		if (null != headerMap) {
    			for (Map.Entry<?, ?> entity : headerMap.entrySet()) {
    				request.addHeader(entity.getKey().toString(), entity.getValue().toString());
    			}
    		}
    	}
    	Queue<Future<Content>> queue = new LinkedList<Future<Content>>();
    	for (final Request request: requests) {
    	    Future<Content> future = async.execute(request, new FutureCallback<Content>() {
    	        public void failed(final Exception ex) {
    	            System.out.println("failed"+ex.getMessage() + ": " + request);
    	        }
    	        public void completed(final Content content) {
    	            System.out.println("Request completed: " + request);
    	        }
    	        public void cancelled() {
    	        	System.out.println("cancelled");
    	        }
    	    });
    	    queue.add(future);
    	}
    	threadpool.shutdown();
    	return queue;
	}
    /**
     * 
     * <br>
     * <b>功能：</b>多线程请求<br>
     * <b>作者：</b>肖财高<br>
     * <b>日期：</b> 2013-4-1 <br>
     * @param urls
     * @param headerMap
     * @param encoding
     * @param conTimeOut
     * @param soTimeOut
     * @return
     */
	public List<Future<String>> httpPool(String[] urls, String method, Map<?, ?> headerMap, Map<?, ?> paramsMap, String encoding, Integer conTimeOut, Integer soTimeOut) {
		ExecutorService threadPool = Executors.newFixedThreadPool(urls.length);
		if (null == encoding)encoding = "UTF-8";
		if (conTimeOut < 1) conTimeOut = 1000 * 20;
		if (soTimeOut < 1) soTimeOut = 1000 * 30;
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
		HttpHost localhost = new HttpHost("locahost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);
		HttpClient httpClient = new DefaultHttpClient(cm);
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, conTimeOut); 
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeOut); 
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		//List<Future<String>> list = new ArrayList<Future<String>>();
		List<Callable<String>> calls = new ArrayList<Callable<String>>();
		for (int i = 0; i < urls.length; i++) {
			HttpGet httpget = new HttpGet(urls[i]);
			if (null != headerMap) {
				for (Map.Entry<?, ?> entity : headerMap.entrySet()) {
					httpget.addHeader(entity.getKey().toString(), entity.getValue().toString());
				}
			}
			calls.add(new GetCall(httpClient, httpget, encoding));
			/*Future<String> fs = threadPool.submit(new GetCall(httpClient, httpget, encoding));
			list.add(fs);*/
		}
		List<Future<String>> list = null;
		try {
			list= threadPool.invokeAll(calls);
		} catch (InterruptedException e) {
			log.error("InterruptedException:"+e.toString());
			e.printStackTrace();
		}
		threadPool.shutdown();
		return list;
	}

	class GetCall implements Callable<String>{
		private final HttpClient httpClient;
		private final HttpContext context;
		private final HttpGet httpget;
		private final String encoding;

		public GetCall(HttpClient httpClient, HttpGet httpget, String encoding) {
			this.httpClient = httpClient;
			this.context = new BasicHttpContext();
			this.httpget = httpget;
			this.encoding = encoding;
		}

		public String call() throws Exception {
			 try {
		            HttpResponse response = this.httpClient.execute(this.httpget, this.context);
		            HttpEntity entity = response.getEntity();
		            String result=null;
		            if (entity != null) {
		            	result=EntityUtils.toString(entity,encoding);
		            }
		            EntityUtils.consume(entity);
		            return result;
		        } catch (Exception e) {
		        	log.error("Exception:"+e.toString());
		            this.httpget.abort();
		            e.printStackTrace();
		        }
			return null;
		}
	}
}
