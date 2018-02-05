package com.zqsign.common.utils.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.zqsign.common.utils.file.FileItem;
import com.zqsign.common.utils.httpclient.HttpClientUtil.DefaultHttpResponseCallBack;

/**
 * HTTP 请求的包装类
 * @author zzk
 * 2017年7月26日下午7:03:48
 */
public class HttpClientUtil {
	private static final Log log = LogFactory.getLog(HttpClientUtil.class);
//	private static final HttpClient CLIENT;
//	private static SSLConnectionSocketFactory FACTORY;
	
	private static final String DEFAULT_ENCODING="UTF-8"; 
	
//	private static final CloseableHttpClient CLIENT = HttpClients.createDefault();
	
//	private static boolean idleConnectManager =true;

//	static {
//		try {
//			FACTORY = getSSLSocketFactory();
//		} catch (Exception e) {
//			FACTORY = null;
//		}
//		
//        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connFactory = new ManagedHttpClientConnectionFactory();
//
//		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
//	            .register("http", PlainConnectionSocketFactory.INSTANCE)
//	            .register("https", FACTORY)
//	            .build();
		
		// Create a connection manager with custom configuration.
//        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connFactory);
//		manager.setMaxTotal(20000);
//		manager.setDefaultMaxPerRoute(2000);
		
//		RequestConfig config = RequestConfig.custom().setConnectTimeout(20000).setSocketTimeout(20000).build();

//		CLIENT = HttpClients.custom().setConnectionManager(manager).setDefaultRequestConfig(config).build();
		
//		if (idleConnectManager) {
//			IdleConnectionMonitorThread thread = new IdleConnectionMonitorThread(manager);
//			thread.setDaemon(true);
//			thread.start();
//		}
//	}
	
	/**
	 * 发送post请求<br/>
	 * 方法同doPost
	 * @param url
	 * @param params
	 * @return
	 * @auther zzk
	 * 2017年7月27日上午10:55:35
	 */
	public static String sendPost(String url, Map<String, ? extends Object> params) {
		return doPost(url, params, null, new DefaultHttpResponseCallBack());
	}
	/**
	 * 发送post请求
	 * @param url
	 * @param params
	 * @return
	 * @auther zzk
	 * 2017年7月26日下午6:19:12
	 */
	public static String doPost(String url, Map<String, ? extends Object> params) {
		return doPost(url, params, null, new DefaultHttpResponseCallBack());
	}
	
	/**
	 * 发送post请求
	 * @param url
	 * @param params
	 * @param httpResponseCallBack
	 * @return
	 * @auther zzk
	 * 2017年7月26日下午4:35:47
	 */
	public static <T> T doPost(String url, Map<String, ? extends Object> params, HttpResponseCallBack<T> httpResponseCallBack) {
		return doPost(url, params, null, httpResponseCallBack);
	}

	/**
	 * 发送带有头部的post请求
	 * @param url
	 * @param params
	 * @param headers
	 * @param httpResponseCallBack
	 * @return
	 * @auther zzk
	 * 2017年7月26日下午4:36:45
	 */
	public static <T> T doPost(String url, Map<String, ? extends Object> params, Map<String, ? extends Object> headers,HttpResponseCallBack<T> httpResponseCallBack) {
		return doPost(url, params, headers, null, httpResponseCallBack);
	}

	/**
	 * 发送带有文件上传的post
	 * @param url
	 * @param params
	 * @param headers
	 * @param inputStream
	 * @param httpResponseCallBack
	 * @return
	 * @auther zzk
	 * 2017年7月26日下午4:37:17
	 */
	public static <T> T doPost(String url, Map<String, ? extends Object> params, Map<String, ? extends Object> headers,
			FileItem fiItem, HttpResponseCallBack<T> httpResponseCallBack) {
		CloseableHttpClient CLIENT = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		if (headers != null) {
			for (Map.Entry<String, ? extends Object> entry : headers.entrySet()) {
				post.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
		if (fiItem != null) {
	        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//设置浏览器兼容模式，解决文件名乱码问题  
	        try {
				multipartEntityBuilder.addBinaryBody(fiItem.getFieldName(), fiItem.getContent(), fiItem.getMimeType(), fiItem.getFileName());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	        if (params != null) {
	        	for (Map.Entry<String, ? extends Object> entry : params.entrySet()) {
	        		StringBody param = new StringBody(String.valueOf(entry.getValue()), ContentType.create("text/plain", Consts.UTF_8));
	        		multipartEntityBuilder.addPart(entry.getKey(), param);  
	        	}
	        }
	        HttpEntity reqEntity = multipartEntityBuilder.setCharset(Charset.forName(DEFAULT_ENCODING)).build(); 
	        post.setEntity(reqEntity);
		} else if (params != null) {
			UrlEncodedFormEntity entity;
			ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, ? extends Object> entry : params.entrySet()) {
				BasicNameValuePair pair = new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue());
				list.add(pair);
			}
			entity = new UrlEncodedFormEntity(list, Charset.forName(DEFAULT_ENCODING));
			post.setEntity(entity);
		}
		HttpResponse httpResponse = null;
		try {
			httpResponse = CLIENT.execute(post);
			if (httpResponse != null) {
				return httpResponseCallBack.doResponse(httpResponse);
			}
		} catch (IOException e) {
			log.error("http post error", e);
			post.releaseConnection();
			throw new RuntimeException(e);
		} finally {
			if (httpResponse != null) {
				EntityUtils.consumeQuietly(httpResponse.getEntity());
			}
		}
		return null;
	}

	/**
	 * 发送GET请求
	 * @param url
	 * @return
	 * @auther zzk
	 * 2017年7月26日下午6:21:53
	 */
	public static String doGet(String url) {
		return doGet(url, null, null, new DefaultHttpResponseCallBack());
	}
	
	/**
	 * 发送GET请求
	 * @param url
	 * @param httpResponseCallBack
	 * @return
	 * @auther zzk
	 * 2017年7月26日下午6:22:09
	 */
	public static <T> T doGet(String url, HttpResponseCallBack<T> httpResponseCallBack) {
		return doGet(url, null, null, httpResponseCallBack);
	}

	public static <T> T doGet(String url, Map<String, ? extends Object> params, Map<String, ? extends Object> headers,
			HttpResponseCallBack<T> httpResponseCallBack) {
		CloseableHttpClient CLIENT = HttpClients.createDefault();
		HttpGet get = null;
		HttpResponse response = null;
		try {
			URIBuilder builder = new URIBuilder(url);
			if (params != null) {
				for (Map.Entry<String, ? extends Object> entry : params.entrySet()) {
					builder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
				}
			}
			URI uri = builder.build();
			get = new HttpGet(uri);
			if (headers != null) {
				for (Map.Entry<String, ? extends Object> entry : headers.entrySet()) {
					get.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
				}
			}
			response = CLIENT.execute(get);
			if (response != null) {
				return httpResponseCallBack.doResponse(response);
			}
		} catch (URISyntaxException e) {
			log.error("http get url error", e);
		} catch (IOException e) {
			log.error("http get error", e);
			get.releaseConnection();
			throw new RuntimeException(e);
		} finally {
			if (response != null) {
				EntityUtils.consumeQuietly(response.getEntity());
			}
		}
		return null;
	}

//	public static boolean isIdleConnectManager() {
//		return idleConnectManager;
//	}
//
//	public static void setIdleConnectManager(boolean idleConnectManager) {
//		HttpClientUtil.idleConnectManager = idleConnectManager;
//	}
	
//	public static class IdleConnectionMonitorThread extends Thread {
//		private final PoolingHttpClientConnectionManager connMgr;
//		private volatile boolean shutdown;
//
//		public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager connMgr) {
//			this.connMgr = connMgr;
//		}
//
//		public void run() {
//			try {
//				while (!this.shutdown) {
//					synchronized (this) {
//						wait(5000L);
//
//						this.connMgr.closeExpiredConnections();
//
//						this.connMgr.closeIdleConnections(30L, TimeUnit.SECONDS);
//					}
//				}
//			} catch (InterruptedException ex) {
//			}
//		}
//
//		public void shutdown() {
//			this.shutdown = true;
//			synchronized (this) {
//				notifyAll();
//			}
//		}
//	}

//	private static SSLConnectionSocketFactory getSSLSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
//		SSLContext sslContext = SSLContexts.createSystemDefault();
//
//		return new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,
//                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
//	}
	
	//默认的响应结果处理
	static class DefaultHttpResponseCallBack implements HttpResponseCallBack<String>{

		public String doResponse(HttpResponse response) throws IOException{
			if(response.getStatusLine().getStatusCode() == 200){
        		return EntityUtils.toString(response.getEntity(),DEFAULT_ENCODING);
        	}
			return null;
		}
		
	}
	
	
}
