package com.zqsign.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.zqsign.client.util.HttpClientUtil;
import com.zqsign.common.utils.HashStrEncryptUtil;
import com.zqsign.common.utils.rsa.RsaSign;

public class Testtest {
	private static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIOTTQXXd7mLFyFeqeFI4vepbgY36lpB9eDb+lDOCeRSPJUgndjTmCox+zx7KbkwaeYtDNi3rfOiBtNeL97Raoonhtzy+VYHc5Dy/a5tk/1vvpYGaZYyNEWWDHxYK4931TDthNMCDy20hi+bEceBsxIDV3FWO7lZE2Z63Ij069tTAgMBAAECgYAhDdA7t3WMVzToXS+wOO9L6HSLe2CUbB2o75pi0mAJy98MV7VP01hWwVesSgShUVhJSuf/SaWZnwq37uF3yIRK9Ebs1c9PyNzTt1yw4E6Z5HRdKtbr5t3YAhHiCbIYleP7OwWjWd40IKmfyHW22DqAYUxtbZWU+vPeACT15rq/AQJBAMYtjGFCE0rT2jw8rTST9iSuszjaWNJeD2C5GGPDLXYlhNWNXLz5zwTu8LKOuYyhIf5AFIDZgKGl2X9tdyodwjkCQQCp9wb6w106z4ifnE4k20xociGCXOFbtGT7NHoqVHHN/gWr+vhkZ/9rNbcKAzCSCFmR9A4PzSjJM/EuI9ekuxnrAkBMZ7Uurh3oBIOoI7CXi9AjVmNssOn7uR3uqY4YsGAvo8lx1/2AMkO5YDHWdaZd7KXBc400HCc2q02OjnKZ/z0pAkEAoXleOSkYTsJSg4klOKDcA7LCoS6Kr+w/r+UYV1To1hKK4uxJxmKDaCCdKDcFkwe5yQ87uMlqAVWGwhYtToSvRwJBALSLfcrEtd2LXES7xbGxvY14bkTJQ7IFGVCxjFvX9QpfNPMZqcgaLoWmSih9hJA5PoM3xsmS69tqXh2v+X3OwP0=";
	private static String zqid = "ZQEA3526D3C34A46D3B18C94560F225741";
	private static String url = "http://117.78.50.198:8080/zqsign-web-saas-service/personReg";
	
	public static void main(String[] args) throws IOException {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("zqid", zqid);
		paramsMap.put("user_code", "my123");
		paramsMap.put("name", "张三");
		paramsMap.put("id_card_no", "410188198804085011");
		paramsMap.put("mobile", "13676943455");
		
		String content = RsaSign.createLinkString(paramsMap);
		String sign = RsaSign.sign(content, private_key);
		paramsMap.put("sign_val", sign);

		System.out.println(sign);
		String result = HttpClientUtil.sendPost(url, paramsMap);
		System.out.println(result);
		
//uVUARFMJFmC0SkSNuqJAPMT73bvNJW912G/3cCSqN2ZVsr+UdmkHOkhMJiIAmyzOEXg8Cu0x1RgtsCwYO+uFabimUbPcuEGkqQ5TNvJAtUvGksmI7MpkoYvcHVVF53Yd1UbduSQUeKnU8tllSe1zU0VVh40md6RJtt4KHOf7+Uk=		
	
	}
	@Test
	public void testName() throws Exception {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("zqid", zqid);
		paramsMap.put("user_code", "my123");
		paramsMap.put("name", "张三");
		paramsMap.put("id_card_no", "410188198804085011");
		paramsMap.put("mobile", "13676943455");
		
		String content = RsaSign.createLinkString(paramsMap);
		String sign = RsaSign.sign(content, private_key);
		paramsMap.put("sign_val", sign);

		System.out.println(sign);
		String result = HttpClientUtil.sendPost(url, paramsMap);
		System.out.println(result);
		
	}
	
	@Test
	public void testHash() throws Exception {
//		String encryptStr = HashStrEncryptUtil.encryptStr("ZQF0AF6632C2F34EBCB2B367F5EAB4D255", "ZQF0AF6632C2F34EBCB2B367F5EAB4D255");
		String encryptStr = HashStrEncryptUtil.encryptStr("ZQ71E02064AAB64CFAB8FDE4E98CFFF355", "ZQ71E02064AAB64CFAB8FDE4E98CFFF355");
		System.out.println(encryptStr);
	}
	@Test
	public void testFloat() throws Exception {
		float f1 = 112.12f;
		int f2 = 2;
		System.out.println(f1-f2+1);
	}
}
