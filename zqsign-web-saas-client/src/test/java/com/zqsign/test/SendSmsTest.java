package com.zqsign.test;

import java.io.File;
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
import com.zqsign.client.util.ZqSubmit;
import com.zqsign.common.base64.Base64;
import com.zqsign.common.io.IOUtils;
import com.zqsign.common.utils.rsa.RsaSign;

/**
 * 合同签署的controller测试
 * @author zzk
 * 2017年4月15日下午4:21:24
 */
public class SendSmsTest {
	
//	private String zqid="ZQ72A595AA33064825892EF35FD2090B16";
//	private String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJcd/pyfdbC1Kkg2m9h6REEPHRvR4lyxPe7395oKq5j1AndZcxhVDTYRUq37anv7k8U5uWyQWzF8/Nxyv98fadQiOPYzg8J/aS08d3j1VJ3y0c06Aino7S9sp/G4m29BXNkmBKFmQ9xmWUKuIXqqklCOxpAn5ihCOxe6Dr6VL1PDAgMBAAECgYBUpjmktHIxnRnlP6nG9p5bL0Mn/Ubmk2ZVAVQSVt3OnZhXEKU3QRVYbxW4XbhFYSRiezZSCP78Vr4yIpk2PBuEGjz/dFhg/Uya8s5uT4U7pN0M0lWVuTCNVN16envYLA6KwrLI/xsm67sdCkldCoXnX7V0RGALZjJLUrolx3oZOQJBANvEYBxIqQNVgfJh3/1pwU6CSQamMxd1nceTfghIjvIOGr3CKxz2+Zqu+oiWr1sVGnesBUWQvhkMGApBS5YyTH0CQQCwCCNpN9c0cvOkcyidEkKno5jDMIFuCBiAEQTDa+1Q25DMtRtOKOVlv7ZvWH4r8/8mTo6jEaRUyIkBqrRKXVU/AkB1vEDZJjwVoyuY1MKRGqULEFVbEJLykHpUvzmX6ndaSiAdZPZ/7/hM3E0v+7o6C5ZAYpH4cNDGzmnapJx//LZpAkEAgPcVamq8wfJNyfeMk7bjGopiyu3ohUJtILZXNQ0HKqwR48fzn0ma9vaDpAPDPFRgZCI9of601GCu74eu2qSSZQJBAIBPdAqh9kwDQNE6G4LpM9c4RyVSeo9rHffNxFj82I+XZOJzx/aif/NLUGeSgXvDqAXlxZkI6mtpDQKR2OJTsPY=";
//	private String url="http://localhost:8080/zqsign-web-saas-wszqc";
	String zqid="ZQ26AE180566FF4CF3BD1D2FBC3C61B592";
	String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKud4/ndRM4dJs5ycvTCcTDs9EA0TrBN/pYm4NSq0BCMzThwAHn/DuDMecwExp8/KcaKjJY9VYwGiAxauc1Fp55emwlLH42NxZjVu45ix7woDIO/p4s0zJEEVVJBl0CdGedZ6BFrL+ZI67todfia7khpke0OOcPvrbeLhignMjTXAgMBAAECgYA79Wj+FjmAzZyOSlwxeXG0h0T63YuUe1t0OFca1iblcVwN2MvB08Pt9GKlThHa92VwEV3266zkxC9qKZR6zFg4INg3ZyoUXDQ7/0m5CyACqG2grg5rcQtMVXYb8r72XGzxSB/IUYiDEmW/9cRXmrD66jwZ+lQlTQ6xUrtw8alcGQJBAN6vqm+7YFIgSB5em4+LHLKBq4VwBmOMHOOhMqoToXZKLgCkoVPxbBoDBxidPHOwjwffUwy0iv/C7UlaEsZn1VMCQQDFSmQQFz+7ykDg+uRNqahXvj1+JgQJACw0zRt06Vc5HEqYB7IKgugIXy4eIn0VbI1wmeENoKQiMimC+h8XwY3tAkEAn/7Y1yRqpTcaee+Bl1XdDYhLIzmE7Zsq2oXkL/ygwquzd7TRUM2h3OKWA6JdX8XYTlCvi+5f/DkiYdR8rt7FjwJAYHI7dHpJF96J1PIRxb2YpDxGB3NCKbxB4BYcxAyGLU8dUDEC4oyYLe+JtgWaLG0Cv/cZw/WyB12KWZM4u/5NCQJBAMrid+bzm6toseBy9zCjoDb9qSLyZSyTWcXLOqt6jg8XpsxSQyeTUfxiIR0x99RqXE1nqLIgRv/8Ve03Q1YT2wc=";
	String url="http://localhost:8080/zqsign-web-saas-service";
	@Test
	public void sendSms() throws IOException{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("contract_no", "5125123");
		hashMap.put("mobile", "13676943455");
		hashMap.put("signer", "402885f6569bfda701569c02f97c0002");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/sendSms", hashMap);
		System.out.println(s);
	}
	@Test
	public void verifySms() throws IOException{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("contract_no", "5125123");
		hashMap.put("mobile", "13676943455");
		hashMap.put("signer", "402885f6569bfda701569c02f97c0002");
		hashMap.put("sms_id", "df641d6bf2ee430897bab2454f0b6559");
		hashMap.put("sms_code", "798101");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/verifySms", hashMap);
		System.out.println(s);
	}
	
}
