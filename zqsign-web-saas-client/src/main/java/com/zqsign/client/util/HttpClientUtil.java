package com.zqsign.client.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.zqsign.common.file.FileItem;
import com.zqsign.common.utils.StringUtil;
import com.zqsign.common.utils.httpclient.HttpResponseCallBack;

public class HttpClientUtil {
	private static Log logger = LogFactory.getLog(HttpClientUtil.class);

	private static String encode = "UTF-8"; 
      
	
	/** 
     * 上传文件 
     *  
     * @param serverUrl 
     *            服务器地址 
     * @param localFilePath 
     *            本地文件路径 
     * @param serverFieldName 
     * @param params 
     * @return 
     * @throws Exception 
     */  
    public static String uploadFileImpl(String url, String filePath,  
            String fileFiledName, Map<String, String> params)  
            throws Exception {  
        String respStr = null;  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            HttpPost httppost = new HttpPost(url);  
            FileBody binFileBody = new FileBody(new File(filePath));  
  
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder  
                    .create();  
            // add the file params  
            multipartEntityBuilder.addPart(fileFiledName, binFileBody);  
            // 设置上传的其他参数  
            setUploadParams(multipartEntityBuilder, params);  
  
            HttpEntity reqEntity = multipartEntityBuilder.build();  
            httppost.setEntity(reqEntity);  
  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                System.out.println(response.getStatusLine());  
                HttpEntity resEntity = response.getEntity();  
                respStr = EntityUtils.toString(resEntity);  
                EntityUtils.consume(resEntity);  
            } finally {  
                response.close();  
            }  
        } finally {  
            httpclient.close();  
        }  
        System.out.println("resp=" + respStr);  
        return respStr;  
    }  
  
    /** 
     * 设置上传文件时所附带的其他参数 
     *  
     * @param multipartEntityBuilder 
     * @param params 
     */  
    private static void setUploadParams(MultipartEntityBuilder multipartEntityBuilder,  
            Map<String, String> params) {  
        if (params != null && params.size() > 0) {  
            Set<String> keys = params.keySet();  
            for (String key : keys) {  
                multipartEntityBuilder  
                        .addPart(key, new StringBody(params.get(key),  
                                ContentType.TEXT_PLAIN));  
            }  
        }  
    } 
	
	
	public static <T> T doPost(String url, Map<String, String> params, Map<String, String> headers,
			FileItem fiItem, HttpResponseCallBack<T> httpResponseCallBack) {
		CloseableHttpClient CLIENT = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				post.addHeader(entry.getKey(), entry.getValue());
			}
		}
		if (fiItem != null) {
	        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//设置浏览器兼容模式，解决文件名乱码问题  
	        try {
				multipartEntityBuilder.addBinaryBody(fiItem.getFileName(), fiItem.getContent());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	        if (params != null) {
	        	for (Map.Entry<String, String> entry : params.entrySet()) {
	        		StringBody param = new StringBody(entry.getValue(), ContentType.create("text/plain", Consts.UTF_8));
	        		multipartEntityBuilder.addPart(entry.getKey(), param);  
	        	}
	        }
	        HttpEntity reqEntity = multipartEntityBuilder.setCharset(Charset.forName("utf-8")).build(); 
	        post.setEntity(reqEntity);
		} else if (params != null) {
			UrlEncodedFormEntity entity;
			ArrayList<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				BasicNameValuePair pair = new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue());
				list.add(pair);
			}
			entity = new UrlEncodedFormEntity(list, Charset.forName("utf-8"));
			post.setEntity(entity);
		}
		HttpResponse httpResponse = null;
		try {
			httpResponse = CLIENT.execute(post);
			if (httpResponse != null) {
				return httpResponseCallBack.doResponse(httpResponse);
			}
		} catch (IOException e) {
			logger.error("http post error", e);
			post.releaseConnection();
			throw new RuntimeException(e);
		} finally {
			if (httpResponse != null) {
				EntityUtils.consumeQuietly(httpResponse.getEntity());
			}
			try {
				CLIENT.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 发送post请求
	 * 
	 * @param url
	 *            请求地址
	 * @param formparams
	 *            formparams.add(new BasicNameValuePair(key, value))
	 * @throws IOException
	 */
	public static String sendPost(String url, List<NameValuePair> formparams)
			throws IOException {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(30000).build();// .setSocketTimeout(120000)
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(new UrlEncodedFormEntity(formparams, encode));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				System.out.println(response.getStatusLine());
				int code = response.getStatusLine().getStatusCode();
				if (code == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						result = EntityUtils.toString(response.getEntity(),encode);
					}
					logger.info(result);
					// do something useful with the response body
					// and ensure it is fully consumed
					EntityUtils.consume(entity);
				} else {
					logger.error(code + ":访问" + url + "失败");
					throw new RuntimeException(code + ":访问" + url + "失败");
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return StringUtil.replaceBlank(result);
	}

	/**
	 * 发送post请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数Map<String, ? extends Object> params
	 *            Object可以为String或者File或者基本类型
	 * @throws IOException
	 */
	public static String sendPost(String url, Map<String, String> params)
			throws IOException {
		System.out.println(url);
		String result = null;
		logger.info("开始发送post请求");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(30000).build();// .setSocketTimeout(120000)
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (params != null && params.size() > 0) {
				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String key = entry.getKey();
					String value = params.get(key);
					formparams.add(new BasicNameValuePair(key, value));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(formparams, encode));
			}
			CloseableHttpResponse response = httpclient.execute(httpPost);
			logger.info("拿到post请求的响应");
			try {
				System.out.println(response.getStatusLine());
				int code = response.getStatusLine().getStatusCode();
				if (code == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						result = EntityUtils.toString(response.getEntity(),
								encode);
					}
					logger.info(result);
					// do something useful with the response body
					// and ensure it is fully consumed
					EntityUtils.consume(entity);
				} else {
					logger.error(code + ":访问" + url + "失败");
					throw new RuntimeException(code + ":访问" + url + "失败");
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return StringUtil.replaceBlank(result);
	}

	public static String sendPost(String url, String param) throws IOException {
		String result = null;
		logger.info("开始发送post请求");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(30000).build();// .setSocketTimeout(120000)
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			if (!StringUtils.isBlank(param)) {
				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				String[] pps = param.split("&");
				for (int i = 0; i < pps.length; i++) {
					int denghaoindex = pps[i].indexOf("=");
					formparams.add(new BasicNameValuePair(pps[i].substring(0,
							denghaoindex), pps[i].substring(denghaoindex + 1)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(formparams, encode));
			}
			CloseableHttpResponse response = httpclient.execute(httpPost);
			logger.info("拿到post请求的响应");
			try {
				System.out.println(response.getStatusLine());
				int code = response.getStatusLine().getStatusCode();
				if (code == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						result = EntityUtils.toString(response.getEntity(),
								encode);
					}
					logger.info(result);
					// do something useful with the response body
					// and ensure it is fully consumed
					EntityUtils.consume(entity);
				} else {
					logger.error(code + ":访问" + url + "失败");
					throw new RuntimeException(code + ":访问" + url + "失败");
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return StringUtil.replaceBlank(result);
	}
	/**
	 * 发送get请求
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public static String sendGet(String url) throws IOException {
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(30000).build();// .setSocketTimeout(120000)
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			try {
				System.out.println(response.getStatusLine());
				int code = response.getStatusLine().getStatusCode();
				if (code == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						result = EntityUtils.toString(response.getEntity(),encode);
					}
					// do something useful with the response body
					// and ensure it is fully consumed
					EntityUtils.consume(entity);
				} else {
					logger.error(code + ":访问" + url + "失败");
					throw new RuntimeException(code + ":访问" + url + "失败");
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return StringUtil.replaceBlank(result);
	}
	public static void main(String[] args) throws IOException {
		String s = HttpClientUtil.sendGet("http://210.51.191.58:8080/zqsign-tools/createAbstract?content=123456789");
		System.out.println(s);
	}
}