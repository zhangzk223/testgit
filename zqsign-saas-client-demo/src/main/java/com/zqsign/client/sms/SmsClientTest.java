package com.zqsign.client.sms;

import java.io.IOException;
import java.util.HashMap;

import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;

public class SmsClientTest {

	public static void main(String[] args) throws IOException {
		String contract_no ="1b428785-613b-4608-b15a-1e2b5c201c12";
		String mobile = "15313927710";
		String signer = "ec0797c9-5488-4265-965a-a69534eda483";
		smsSend(contract_no,signer,mobile);
	}
	
	
	//合同自动签署
	public static void smsSend(String contractNo,String signer,String mobile) throws IOException{
		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "v2/5/sms";
		String zqid = ZqsignManage.ZQID;

		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("zqid", zqid);
		hashMap.put("contract_no", "1b428785-613b-4608-b15a-1e2b5c201c12");
		hashMap.put("mobile", "15313927710");
		hashMap.put("signer", "ec0797c9-5488-4265-965a-a69534eda483");
		String content = RsaSign.createLinkString(hashMap);
		String sign = RsaSign.sign(content, private_key);
		hashMap.put("sign_val", sign);
		String s = HttpClientUtil.sendPost(request_url, hashMap);
		System.out.println(s);
	}
	
}
