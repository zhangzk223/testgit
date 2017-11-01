package com.zqsign.test.wszqc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

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
public class SignContractClientTest {
	
//	String zqid="ZQ26AE180566FF4CF3BD1D2FBC3C61B592";
//	String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKud4/ndRM4dJs5ycvTCcTDs9EA0TrBN/pYm4NSq0BCMzThwAHn/DuDMecwExp8/KcaKjJY9VYwGiAxauc1Fp55emwlLH42NxZjVu45ix7woDIO/p4s0zJEEVVJBl0CdGedZ6BFrL+ZI67todfia7khpke0OOcPvrbeLhignMjTXAgMBAAECgYA79Wj+FjmAzZyOSlwxeXG0h0T63YuUe1t0OFca1iblcVwN2MvB08Pt9GKlThHa92VwEV3266zkxC9qKZR6zFg4INg3ZyoUXDQ7/0m5CyACqG2grg5rcQtMVXYb8r72XGzxSB/IUYiDEmW/9cRXmrD66jwZ+lQlTQ6xUrtw8alcGQJBAN6vqm+7YFIgSB5em4+LHLKBq4VwBmOMHOOhMqoToXZKLgCkoVPxbBoDBxidPHOwjwffUwy0iv/C7UlaEsZn1VMCQQDFSmQQFz+7ykDg+uRNqahXvj1+JgQJACw0zRt06Vc5HEqYB7IKgugIXy4eIn0VbI1wmeENoKQiMimC+h8XwY3tAkEAn/7Y1yRqpTcaee+Bl1XdDYhLIzmE7Zsq2oXkL/ygwquzd7TRUM2h3OKWA6JdX8XYTlCvi+5f/DkiYdR8rt7FjwJAYHI7dHpJF96J1PIRxb2YpDxGB3NCKbxB4BYcxAyGLU8dUDEC4oyYLe+JtgWaLG0Cv/cZw/WyB12KWZM4u/5NCQJBAMrid+bzm6toseBy9zCjoDb9qSLyZSyTWcXLOqt6jg8XpsxSQyeTUfxiIR0x99RqXE1nqLIgRv/8Ve03Q1YT2wc=";
//	String url="http://192.168.1.151:8080/zqsign-web-saas-wszqc";
	String zqid="ZQ9B008946DCEB4A088DC491AFF2CF2D2C";
	String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAITXQVyl0O8MiQBmcXOQOanNA9+ylKJVxS3SyN1bkU5lXJ5Avkb2lUoDrfllKh6mcjQ3XYyUolYEwHQB8+iYltCcU50IX8f08zmCgk+L4ZbYXb5Uw01Hj8qwjEH44lxlo48nk34MKAlGjQ8BcQt91tNkUvwYhOtH6gqfDCyUS6+9AgMBAAECgYA5K57Arcg+qSlpF62p6581ebs6EdQwDTPAJeA2jAr8L1lt7jU1uzfC09SS6bv31vB9knpbXziCRqdBBfof11PIMVaMCpulHlQATYAG4ePACgn+5vCgDqUDwvFB2a5b3nvwRgZS8jGGUuhEUcpU81qyQqfltXNQ5a1GB1NE0k4coQJBANgEZpot/Z39A+9zreZ5qGuwK/eNp/PY5/op0iETCd8md5k33/1F2dZzzctqowHPNQnERPDnvSBfeqgfwM58UbUCQQCdbbAP23Cg9enBMtfCqZ4w5obWcDTwxZhCUwk+b1NHtXoSXBSgqhtIyt5sRM+PGjzetWCr7fmsvGdQocsfy0rpAkAQwr6nRdxAzunoQWSD1nqv6Fpw+cfHTtQo/+ey80Q30UdEVyaFA090qUWVe++r60TlPhpPStvM0Du3IceW/eRlAkB65TrxVhQ5g1+k86TdkP4uHHgchOG/J/GxrRLlS5DOxQvzszBjMAg2rjAugxUpGIl2Lk+2SAOF5loc5wEj49apAkEArS1GDBHnR+0ry1rfUD6UUj/rdVZmNOnWpDkHWFViZkyZK2pElxvjQROFQ1sCly5mo+IidEOJc2uvNeAIonS6rA==";
	String url="http://192.168.1.134:8080/zqsign-web-saas-wszqc/";
	
	//合同自动签署
	@Test
	public void signContract() throws IOException{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
//		hashMap.put("no", "59761fc6-d05c-4692-b524-5d53c4addbd8");
		//hashMap.put("signers", "a5848ace-4965-4a54-aacf-335c33c1e0af");
		hashMap.put("no", "265499ee-33d8-4161-a63a-7f5d9c84db0d");
		hashMap.put("signers", "3e0805f7190845af8f4338d5bb89a961");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/signAuto", hashMap);
		System.out.println(s);
	}
	
	@Test
	public void signByAxisIV() throws IOException{
		File f = new File("E:/test/456.png");
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("no", "265499ee-33d8-4161-a63a-7f5d9c84db0d");
		hashMap.put("user_code", "3e0805f7190845af8f4338d5bb89a961");
		hashMap.put("sms_id", "f8884607922a400b9a86024cf3a247e4");
		hashMap.put("sms_code", "938424");
		hashMap.put("sign_width", "80");
		hashMap.put("sign_height", "80");
		hashMap.put("signature", Base64.encode(IOUtils.toByteArray(f)));
		hashMap.put("pdf_width", "645");
		hashMap.put("pdf_height", "942");
		hashMap.put("leftzb", "180");
		hashMap.put("topzb", "180");
		hashMap.put("page_no", "1");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/signByAxisIV", hashMap);
		System.out.println(s);
	}
	//合同自动签署（无签章显示）
		@Test
		public void signContractNI() throws IOException{
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("zqid", zqid);
			hashMap.put("no", "612612a");
			hashMap.put("signers", "402885f6569bfda701569c02f97c0002");
			String content = RsaSign.createLinkString(hashMap);
			String sign = RsaSign.sign(content, privateKey);
			hashMap.put("sign_val", sign);
			String s = HttpClientUtil.sendPost(url+"/signAutoNI", hashMap);
			System.out.println(s);
		}
		//传位置签署
		@Test
		public void poSignContract() throws IOException{
			File f = new File("E:/test/456.png");
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("zqid", zqid);
			hashMap.put("no", "5125123");
			hashMap.put("user_code", "402885f6569bfda701569c02f97c0002");
			hashMap.put("sign_type", "WRITTEN");//--  //手写签署
			//hashMap.put("sign_type", "WRITTENCODE");//--  //手写验证签署
			//hashMap.put("sign_type", "SIGNATURE");//--  //签章签署
			//hashMap.put("sign_type", "SIGNATURECODE");//--  //签章签署
			hashMap.put("leftzb", "150");
			hashMap.put("topzb", "200");
			hashMap.put("page_no", "1");
			hashMap.put("sign_width", "120");
			hashMap.put("sign_height", "63");
			hashMap.put("pi_width", "654");
			hashMap.put("pi_height", "925");
			hashMap.put("signature", Base64.encode(IOUtils.toByteArray(f)));
			String content = RsaSign.createLinkString(hashMap);
			String sign = RsaSign.sign(content, privateKey);
			hashMap.put("sign_val", sign);
			String s = HttpClientUtil.sendPost(url+"/poSignAuto", hashMap);
			System.out.println(s);
		}
	/**
	 * 视频签署
	 * @throws Exception
	 * 2017年7月7日下午8:27:14
	 */
	@Test
	public void vedioSignView() throws Exception{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("no", "zzk77");
		hashMap.put("user_code", "zzk712");
		hashMap.put("sign_type", "VIDEOSIGNATURE");
//		hashMap.put("sign_type", "VIDEOWRITTEN");
		hashMap.put("notify_url", "http://192.168.1.222:8080/zqsign-web-saas-service");
		hashMap.put("return_url", "http://192.168.1.222:8080/zqsign-web-saas-service");
		String s = ZqSubmit.buildRequest(hashMap, url+"/videoSignView", privateKey);
		System.out.println(s);
	}
	@Test
	public void signView() throws Exception{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("no", "d8f7c0ab5b06436680c92e0505d80fcd");
//		hashMap.put("no", "75eb4980ec6d46b78f426e90abe1b304");
		hashMap.put("user_code", "user001");
//		hashMap.put("sign_type", "SIGNATURE");
		hashMap.put("sign_type", "WRITTEN");
		hashMap.put("notify_url", "http://192.168.1.222:8080/zqsign-web-saas-service");
		hashMap.put("return_url", "http://192.168.1.222:8080/zqsign-web-saas-service");
		String s = ZqSubmit.buildRequest(hashMap, url+"/mobileSignView", privateKey);
		System.out.println(s);
	}
	@Test
	public void pcSignView() throws Exception{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("no", "41e25a00-73fd-40ef-bb95-4e0746e6a746");
		//hashMap.put("no", "75eb4980ec6d46b78f426e90abe1b304");
		hashMap.put("user_code", "0867d94b-204c-4bfe-bbb6-be43d20ae959");
//		hashMap.put("sign_type", "WRITTEN");
		hashMap.put("sign_type", "SIGNATURECODE");
		hashMap.put("notify_url", "http://192.168.1.222:8080/zqsign-web-saas-service");
		hashMap.put("return_url", "http://192.168.1.222:8080/zqsign-web-saas-service");
		String s = ZqSubmit.buildRequest(hashMap, url+"/signView", privateKey);
		System.out.println(s);
	}
	
	
	/**
	 * 回去签章图片的链接地址
	 * @throws IOException
	 * 2017年6月12日上午10:12:01
	 */
	@Test
	public void viewContractImg() throws IOException{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("no", "zhang123456");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/getImg", hashMap);
		System.out.println(s);
	}
	/**
	 * 下载PDF
	 * @throws Exception
	 * 2017年6月12日上午10:12:33
	 */
	@Test
	public void downloadPDF() throws Exception{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("no", "279725f6-06f0-4c65-a8d5-498ef3fa4d95");
//		String content = RsaSign.createLinkString(hashMap);
//		String sign = RsaSign.sign(content, privateKey);
		String s = ZqSubmit.buildRequest(hashMap, url+"/getPdf", privateKey);
		System.out.println(s);
	}
	@Test
	public void downloadPDFUrl() throws Exception{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("no", "0f888d1e-9555-4ecd-8a89-517b8e8678be");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/getPdfUrl", hashMap);
		System.out.println(s);
	}
	/**
	 * 撤销
	 * @throws IOException
	 * 2017年4月25日上午11:25:04
	 */
	@Test
	public void updoSign() throws IOException {
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("no", "ht-zhxy-001");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/undoSign", hashMap);
		System.out.println(s);
	}
	
	
}
