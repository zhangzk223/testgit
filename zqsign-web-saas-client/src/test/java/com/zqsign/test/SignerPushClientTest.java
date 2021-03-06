package com.zqsign.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zqsign.client.util.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;

/**
 * 签户推送/更新的controller
 * @author zzk
 * 2017年4月15日下午4:21:24
 */

public class SignerPushClientTest {
	
	String zqid="ZQ26AE180566FF4CF3BD1D2FBC3C61B592";
	String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKud4/ndRM4dJs5ycvTCcTDs9EA0TrBN/pYm4NSq0BCMzThwAHn/DuDMecwExp8/KcaKjJY9VYwGiAxauc1Fp55emwlLH42NxZjVu45ix7woDIO/p4s0zJEEVVJBl0CdGedZ6BFrL+ZI67todfia7khpke0OOcPvrbeLhignMjTXAgMBAAECgYA79Wj+FjmAzZyOSlwxeXG0h0T63YuUe1t0OFca1iblcVwN2MvB08Pt9GKlThHa92VwEV3266zkxC9qKZR6zFg4INg3ZyoUXDQ7/0m5CyACqG2grg5rcQtMVXYb8r72XGzxSB/IUYiDEmW/9cRXmrD66jwZ+lQlTQ6xUrtw8alcGQJBAN6vqm+7YFIgSB5em4+LHLKBq4VwBmOMHOOhMqoToXZKLgCkoVPxbBoDBxidPHOwjwffUwy0iv/C7UlaEsZn1VMCQQDFSmQQFz+7ykDg+uRNqahXvj1+JgQJACw0zRt06Vc5HEqYB7IKgugIXy4eIn0VbI1wmeENoKQiMimC+h8XwY3tAkEAn/7Y1yRqpTcaee+Bl1XdDYhLIzmE7Zsq2oXkL/ygwquzd7TRUM2h3OKWA6JdX8XYTlCvi+5f/DkiYdR8rt7FjwJAYHI7dHpJF96J1PIRxb2YpDxGB3NCKbxB4BYcxAyGLU8dUDEC4oyYLe+JtgWaLG0Cv/cZw/WyB12KWZM4u/5NCQJBAMrid+bzm6toseBy9zCjoDb9qSLyZSyTWcXLOqt6jg8XpsxSQyeTUfxiIR0x99RqXE1nqLIgRv/8Ve03Q1YT2wc=";
//	String url="http://localhost:8080/zqsign-web-saas-wszqc";
	String url="http://192.168.1.222:8080/zqsign-web-saas-service";
	/**
	 * 个人用户注册。
	 * @throws IOException 
	 */
	@Test
	public void userReg() throws IOException{
		String usercode = "zzk";   String idcard ="410185198504095018";    String mobile="13676943455";
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid); //众签唯一标识
		hashMap.put("name", "张宗奎"); //推送的用户的名称
		hashMap.put("mobile", mobile);  //用户手机号
		hashMap.put("user_code", usercode);//推送的用户的唯一标识
		hashMap.put("id_card_no", idcard);//用户的身份证号码
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);//接口签名值
		String s = HttpClientUtil.sendPost(url+"/personReg", hashMap);
		System.out.println(s);
	}
	
	
	@Test
	public void userChange() throws IOException{
		String usercode = "12345";String idcard ="410185198504095018";String mobile="13676943456";
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("name", "zhangsan");
		hashMap.put("mobile", mobile);
		hashMap.put("user_code", usercode);
		hashMap.put("id_card_no", idcard);
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/personUpdate", hashMap);
		System.out.println(s);
	}
	/**
	 * 企业用户注册。
	 * @throws IOException 
	 */
	@Test
	public void entpReg() throws IOException{
		String usercode = "456";String certificate ="410184095018";String mobile="13676943455";
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("name", "zhangsan");
		hashMap.put("contact", "张三");
		hashMap.put("address", "河南省郑州市");
		hashMap.put("mobile", mobile);
		hashMap.put("user_code", usercode);
		hashMap.put("certificate", certificate);
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/entpReg", hashMap);
		System.out.println(s);
	}
	@Test
	public void entpChange() throws IOException{
		String usercode = "456";String certificate ="410184095018";String mobile="13676943455";
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("name", "zhangsan");
		hashMap.put("contact", "张三");
		hashMap.put("address", "河南省郑州市");
		hashMap.put("mobile", mobile);
		hashMap.put("user_code", usercode);
		hashMap.put("certificate", certificate);
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/entpUpdate", hashMap);
		System.out.println(s);
	}
}
