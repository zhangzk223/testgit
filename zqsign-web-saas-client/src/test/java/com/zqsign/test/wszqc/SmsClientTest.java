package com.zqsign.test.wszqc;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

import com.zqsign.client.util.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;

public class SmsClientTest {

	String zqid="ZQ9B008946DCEB4A088DC491AFF2CF2D2C";
	String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAITXQVyl0O8MiQBmcXOQOanNA9+ylKJVxS3SyN1bkU5lXJ5Avkb2lUoDrfllKh6mcjQ3XYyUolYEwHQB8+iYltCcU50IX8f08zmCgk+L4ZbYXb5Uw01Hj8qwjEH44lxlo48nk34MKAlGjQ8BcQt91tNkUvwYhOtH6gqfDCyUS6+9AgMBAAECgYA5K57Arcg+qSlpF62p6581ebs6EdQwDTPAJeA2jAr8L1lt7jU1uzfC09SS6bv31vB9knpbXziCRqdBBfof11PIMVaMCpulHlQATYAG4ePACgn+5vCgDqUDwvFB2a5b3nvwRgZS8jGGUuhEUcpU81qyQqfltXNQ5a1GB1NE0k4coQJBANgEZpot/Z39A+9zreZ5qGuwK/eNp/PY5/op0iETCd8md5k33/1F2dZzzctqowHPNQnERPDnvSBfeqgfwM58UbUCQQCdbbAP23Cg9enBMtfCqZ4w5obWcDTwxZhCUwk+b1NHtXoSXBSgqhtIyt5sRM+PGjzetWCr7fmsvGdQocsfy0rpAkAQwr6nRdxAzunoQWSD1nqv6Fpw+cfHTtQo/+ey80Q30UdEVyaFA090qUWVe++r60TlPhpPStvM0Du3IceW/eRlAkB65TrxVhQ5g1+k86TdkP4uHHgchOG/J/GxrRLlS5DOxQvzszBjMAg2rjAugxUpGIl2Lk+2SAOF5loc5wEj49apAkEArS1GDBHnR+0ry1rfUD6UUj/rdVZmNOnWpDkHWFViZkyZK2pElxvjQROFQ1sCly5mo+IidEOJc2uvNeAIonS6rA==";
	String url="http://192.168.1.134:8080/zqsign-web-saas-wszqc/";
	
	//合同自动签署
	@Test
	public void smsSend() throws IOException{
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
//		hashMap.put("no", "59761fc6-d05c-4692-b524-5d53c4addbd8");
		//hashMap.put("signers", "a5848ace-4965-4a54-aacf-335c33c1e0af");
		hashMap.put("contract_no", "265499ee-33d8-4161-a63a-7f5d9c84db0d");
		hashMap.put("mobile", "13676943455");
		hashMap.put("signer", "3e0805f7190845af8f4338d5bb89a961");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, privateKey);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(url+"/v2/5/sms", hashMap);
		System.out.println(s);
	}
	
}
