package com.zqsign.test.wszqc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
	
//	String zqid="ZQEA3526D3C34A46D3B18C94560F225741";
//	String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIOTTQXXd7mLFyFeqeFI4vepbgY36lpB9eDb+lDOCeRSPJUgndjTmCox+zx7KbkwaeYtDNi3rfOiBtNeL97Raoonhtzy+VYHc5Dy/a5tk/1vvpYGaZYyNEWWDHxYK4931TDthNMCDy20hi+bEceBsxIDV3FWO7lZE2Z63Ij069tTAgMBAAECgYAhDdA7t3WMVzToXS+wOO9L6HSLe2CUbB2o75pi0mAJy98MV7VP01hWwVesSgShUVhJSuf/SaWZnwq37uF3yIRK9Ebs1c9PyNzTt1yw4E6Z5HRdKtbr5t3YAhHiCbIYleP7OwWjWd40IKmfyHW22DqAYUxtbZWU+vPeACT15rq/AQJBAMYtjGFCE0rT2jw8rTST9iSuszjaWNJeD2C5GGPDLXYlhNWNXLz5zwTu8LKOuYyhIf5AFIDZgKGl2X9tdyodwjkCQQCp9wb6w106z4ifnE4k20xociGCXOFbtGT7NHoqVHHN/gWr+vhkZ/9rNbcKAzCSCFmR9A4PzSjJM/EuI9ekuxnrAkBMZ7Uurh3oBIOoI7CXi9AjVmNssOn7uR3uqY4YsGAvo8lx1/2AMkO5YDHWdaZd7KXBc400HCc2q02OjnKZ/z0pAkEAoXleOSkYTsJSg4klOKDcA7LCoS6Kr+w/r+UYV1To1hKK4uxJxmKDaCCdKDcFkwe5yQ87uMlqAVWGwhYtToSvRwJBALSLfcrEtd2LXES7xbGxvY14bkTJQ7IFGVCxjFvX9QpfNPMZqcgaLoWmSih9hJA5PoM3xsmS69tqXh2v+X3OwP0=";
//	String url="http://192.168.1.221:8080/zqsign-web-saas-wszqc";
	String zqid="ZQ9B008946DCEB4A088DC491AFF2CF2D2C";
	String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAITXQVyl0O8MiQBmcXOQOanNA9+ylKJVxS3SyN1bkU5lXJ5Avkb2lUoDrfllKh6mcjQ3XYyUolYEwHQB8+iYltCcU50IX8f08zmCgk+L4ZbYXb5Uw01Hj8qwjEH44lxlo48nk34MKAlGjQ8BcQt91tNkUvwYhOtH6gqfDCyUS6+9AgMBAAECgYA5K57Arcg+qSlpF62p6581ebs6EdQwDTPAJeA2jAr8L1lt7jU1uzfC09SS6bv31vB9knpbXziCRqdBBfof11PIMVaMCpulHlQATYAG4ePACgn+5vCgDqUDwvFB2a5b3nvwRgZS8jGGUuhEUcpU81qyQqfltXNQ5a1GB1NE0k4coQJBANgEZpot/Z39A+9zreZ5qGuwK/eNp/PY5/op0iETCd8md5k33/1F2dZzzctqowHPNQnERPDnvSBfeqgfwM58UbUCQQCdbbAP23Cg9enBMtfCqZ4w5obWcDTwxZhCUwk+b1NHtXoSXBSgqhtIyt5sRM+PGjzetWCr7fmsvGdQocsfy0rpAkAQwr6nRdxAzunoQWSD1nqv6Fpw+cfHTtQo/+ey80Q30UdEVyaFA090qUWVe++r60TlPhpPStvM0Du3IceW/eRlAkB65TrxVhQ5g1+k86TdkP4uHHgchOG/J/GxrRLlS5DOxQvzszBjMAg2rjAugxUpGIl2Lk+2SAOF5loc5wEj49apAkEArS1GDBHnR+0ry1rfUD6UUj/rdVZmNOnWpDkHWFViZkyZK2pElxvjQROFQ1sCly5mo+IidEOJc2uvNeAIonS6rA==";
	String url="http://192.168.1.155:8080/zqsign-web-saas-wszqc/";
	
	/**
	 * 通过上传pdf创建合同
	 * @throws IOException 
	 */
	@Test
	public void createForPdf() throws Exception{
		String contract_no = UUID.randomUUID().toString();
		File file = new File("E:/test/mypdf_example.pdf");
		byte[] fileBytes = IOUtils.toByteArray(file);
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("name", "测试位置");
		hashMap.put("no", contract_no);
		hashMap.put("contract", Base64.encode(fileBytes));
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/uploadPdf", hashMap);
		System.out.println("contract_no:"+contract_no);//41e25a00-73fd-40ef-bb95-4e0746e6a746
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
