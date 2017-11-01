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
public class CreateContractClientTest {
	
	private String zqid="ZQ26AE180566FF4CF3BD1D2FBC3C61B592";
	private String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKud4/ndRM4dJs5ycvTCcTDs9EA0TrBN/pYm4NSq0BCMzThwAHn/DuDMecwExp8/KcaKjJY9VYwGiAxauc1Fp55emwlLH42NxZjVu45ix7woDIO/p4s0zJEEVVJBl0CdGedZ6BFrL+ZI67todfia7khpke0OOcPvrbeLhignMjTXAgMBAAECgYA79Wj+FjmAzZyOSlwxeXG0h0T63YuUe1t0OFca1iblcVwN2MvB08Pt9GKlThHa92VwEV3266zkxC9qKZR6zFg4INg3ZyoUXDQ7/0m5CyACqG2grg5rcQtMVXYb8r72XGzxSB/IUYiDEmW/9cRXmrD66jwZ+lQlTQ6xUrtw8alcGQJBAN6vqm+7YFIgSB5em4+LHLKBq4VwBmOMHOOhMqoToXZKLgCkoVPxbBoDBxidPHOwjwffUwy0iv/C7UlaEsZn1VMCQQDFSmQQFz+7ykDg+uRNqahXvj1+JgQJACw0zRt06Vc5HEqYB7IKgugIXy4eIn0VbI1wmeENoKQiMimC+h8XwY3tAkEAn/7Y1yRqpTcaee+Bl1XdDYhLIzmE7Zsq2oXkL/ygwquzd7TRUM2h3OKWA6JdX8XYTlCvi+5f/DkiYdR8rt7FjwJAYHI7dHpJF96J1PIRxb2YpDxGB3NCKbxB4BYcxAyGLU8dUDEC4oyYLe+JtgWaLG0Cv/cZw/WyB12KWZM4u/5NCQJBAMrid+bzm6toseBy9zCjoDb9qSLyZSyTWcXLOqt6jg8XpsxSQyeTUfxiIR0x99RqXE1nqLIgRv/8Ve03Q1YT2wc=";
//    private String url="http://localhost:8080/zqsign-web-saas-wszqc";
//	private String url="http://localhost:8080/zqsign-web-saas-service";
	String url="http://192.168.1.222:8080/zqsign-web-saas-service";
	/**
	 * 通过上传pdf创建合同
	 * @throws IOException 
	 */
	@Test
	public void createForPdf() throws Exception{
		File file = new File("E:/test/ddddd.pdf");
		byte[] fileBytes = IOUtils.toByteArray(file);
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("name", "测试位置");
		hashMap.put("no", "zzk77");
		hashMap.put("contract", Base64.encode(fileBytes));
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/uploadPdf", hashMap);
//		String s = ZqSubmit.buildRequest(hashMap, url+"/uploadPdf", sign);
		System.out.println(s);
	}
	
	/**
	 * 通过模板创建合同
	 * @param contract_no
	 * @param t_id
	 * @throws IOException
	 * 2017年4月16日下午5:21:28
	 */
	@Test
	public void createContractByPdfForm() throws Exception{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("name", "zhangsan123");
		hashMap.put("contract_val", "{\"jsonVal\":[{\"jiafang\":\"\",\"gupiaocode\":\"\",\"jianame\":\"\",\"jiashouji\":\"\",\"jiayouxiang\":\"\",\"yiname\":\"\",\"yidianhau\":\"\",\"yiyouxiang\":\"\",\"yifang\":\"\",\"Signer1\":\"\",\"Signer2\":\"\",\"year1\":\"\",\"month1\":\"\",\"day1\":\"\",\"year2\":\"\",\"month2\":\"\",\"day2\":\"\"}]}");
		hashMap.put("t_no", "0be345335b554a078dcd71bf4d64859b");
		hashMap.put("no", "aa1233");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/pdfTemplate", hashMap);
		System.out.println(s);
	}
	
}
