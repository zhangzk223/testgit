package com.zqsign.test.addition;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.zqsign.client.util.AuthenResult;
import com.zqsign.client.util.ZqSubmit;
import com.zqsign.common.file.FileItem;
import com.zqsign.common.io.IOUtils;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.httpclient.HttpResponseCallBack;
import com.zqsign.common.utils.rsa.RsaSign;

public class TestPersonAuth {
	
	String zqid="ZQ26AE180566FF4CF3BD1D2FBC3C61B592";
	String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKud4/ndRM4dJs5ycvTCcTDs9EA0TrBN/pYm4NSq0BCMzThwAHn/DuDMecwExp8/KcaKjJY9VYwGiAxauc1Fp55emwlLH42NxZjVu45ix7woDIO/p4s0zJEEVVJBl0CdGedZ6BFrL+ZI67todfia7khpke0OOcPvrbeLhignMjTXAgMBAAECgYA79Wj+FjmAzZyOSlwxeXG0h0T63YuUe1t0OFca1iblcVwN2MvB08Pt9GKlThHa92VwEV3266zkxC9qKZR6zFg4INg3ZyoUXDQ7/0m5CyACqG2grg5rcQtMVXYb8r72XGzxSB/IUYiDEmW/9cRXmrD66jwZ+lQlTQ6xUrtw8alcGQJBAN6vqm+7YFIgSB5em4+LHLKBq4VwBmOMHOOhMqoToXZKLgCkoVPxbBoDBxidPHOwjwffUwy0iv/C7UlaEsZn1VMCQQDFSmQQFz+7ykDg+uRNqahXvj1+JgQJACw0zRt06Vc5HEqYB7IKgugIXy4eIn0VbI1wmeENoKQiMimC+h8XwY3tAkEAn/7Y1yRqpTcaee+Bl1XdDYhLIzmE7Zsq2oXkL/ygwquzd7TRUM2h3OKWA6JdX8XYTlCvi+5f/DkiYdR8rt7FjwJAYHI7dHpJF96J1PIRxb2YpDxGB3NCKbxB4BYcxAyGLU8dUDEC4oyYLe+JtgWaLG0Cv/cZw/WyB12KWZM4u/5NCQJBAMrid+bzm6toseBy9zCjoDb9qSLyZSyTWcXLOqt6jg8XpsxSQyeTUfxiIR0x99RqXE1nqLIgRv/8Ve03Q1YT2wc=";
	String url="http://192.168.1.21:8080/zqsign-web-identify";

	@Test
	public void a2e() throws IOException{
		String order_no = UUID.randomUUID().toString();
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("name", "王文婷");
		hashMap.put("idcard", "410185198504095018");
		hashMap.put("order_no", order_no);
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/a2e", hashMap);
		System.out.println("order_no:"+order_no+"==result:"+s);
	}
	@Test
	public void fca() throws IOException{
		String order_no = UUID.randomUUID().toString();
		String motions = "YAW NOD"; //BLINK MOUTH NOD YAW
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("name", "张宗奎");
		hashMap.put("idcard", "410185198504095018");
		hashMap.put("order_no", order_no);
		hashMap.put("motions", motions);
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/fca", hashMap);
		System.out.println("order_no:"+order_no+"==result:"+s);
		//获取token  498bad753e494757b3e02a7efdb14d41
	}
	@Test
	public void fcaUpload() throws Exception{
		HashMap<String, String> hashMap = new HashMap<>();
		String token = "498bad753e494757b3e02a7efdb14d41";
		hashMap.put("token", token); //video
//		byte[] contents = IOUtils.toByteArray(new File("C:/Users/admin/Desktop/1111.mp4"));
//		FileItem fileItem = new FileItem("video",contents);
//		AuthenResult result = com.zqsign.client.util.HttpClientUtil.doPost(url+"/fcaUpload", hashMap, null, fileItem, new HttpResponseCallBack<AuthenResult>() {
//
//			@Override
//			public AuthenResult doResponse(HttpResponse response) throws IOException {
//				int code = response.getStatusLine().getStatusCode();
//				if(200 == code){
//					return JSONObject.parseObject(response.getEntity().getContent(), AuthenResult.class);
//				}
//				return null;
//			}
//		});
		String result = com.zqsign.client.util.HttpClientUtil.uploadFileImpl(url+"/fcaUpload", "C:/Users/admin/Desktop/1111.mp4", "video", hashMap);
		System.out.println(result);
	}
	
//	--------------------------------------------------------------------------------------------------------------------------------------
	
	@Test
	public void testH5fca() throws Exception {
		String order_no = UUID.randomUUID().toString();
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("order_no", order_no);
		hashMap.put("return_url", "http://127.0.0.1:8080/");
		hashMap.put("notify_url", "http://127.0.0.1:8080/");
		String string = ZqSubmit.buildRequest(hashMap, url+"/h5fca", privateKey);
		System.out.println(string);
	}
	
	
	
	
}
