package com.zqsign.client.contract.bykeyword;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;

/**
 * 
 * @ClassName: SignAuto
 * @Description: 签章验证关键字签署
 * @date: 2017年3月28日 下午2:32:06
 * 
 */
public class SignByKeywordNIV {

	public static void main(String[] agrs) throws Exception {

		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "signByKeywordNIV";
		String zqid = ZqsignManage.ZQID;

		Map<String, String> map = new HashMap<String, String>();

		map.put("zqid", zqid);// ---------需要用户修改
		map.put("no", "1b428785-613b-4608-b15a-1e2b5c201c12");// ---------需要用户修改
		map.put("keyword", "为什么");// ---------签署关键字
		map.put("user_code", "ec0797c9-5488-4265-965a-a69534eda483");// ---------需要用户修改
		map.put("sms_id", "1874fec373a043469b3170f82032eb15");// ---------验证码id
		map.put("sms_code", "123456");// ---------用户输入的验证码

		//签名
        String content = RsaSign.createLinkString(map);
        String sign_val = RsaSign.sign(content,private_key);
        
		System.out.println(sign_val);
		map.put("sign_val", sign_val); // 请求参数的签名值
		String response_str = HttpClientUtil.sendPost(request_url, map);// 向服务端发送请求，并接收请求结果
		System.out.println("请求结果：" + response_str);// 输出服务器响应结果

	}
	
	/**
	 * 
	 * @param no-------合同编号
	 * @param keyword----关键字
	 * @param userCode----用户id
	 * @param sms_id-------验证码id
	 * @param sms_code------用户输入的验证码
	 * @return
	 * @throws Exception
	 */
	public static String signByKeywordNIV(String no,String keyword,String userCode,String sms_id,String sms_code) throws Exception {

		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "signByKeywordNIV";
		String zqid = ZqsignManage.ZQID;

		Map<String, String> map = new HashMap<String, String>();

		map.put("zqid", zqid);// ---------需要用户修改
		map.put("no", no);// ---------需要用户修改
		map.put("keyword", keyword);// ---------签署关键字
		map.put("user_code",userCode );// ---------需要用户修改
		map.put("sms_id", sms_id);// ---------验证码id
		map.put("sms_code", sms_code);// ---------用户输入的验证码
		
		//签名
        String content = RsaSign.createLinkString(map);
        String sign_val = RsaSign.sign(content,private_key);
        
		map.put("sign_val", sign_val); // 请求参数的签名值
		String response_str = HttpClientUtil.sendPost(request_url, map);// 向服务端发送请求，并接收请求结果
		System.out.println("请求结果：" + response_str);// 输出服务器响应结果
		JSONObject ob = JSONObject.parseObject(response_str);
		String s = ob.getString("msg");
		return s;
	}
}
