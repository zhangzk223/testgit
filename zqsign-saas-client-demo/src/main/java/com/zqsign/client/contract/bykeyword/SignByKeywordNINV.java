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
 * @Description: 签章无验证关键字签署
 * @date: 2017年3月28日 下午2:32:06
 * 
 */
public class SignByKeywordNINV {

	public static void main(String[] agrs) throws Exception {

		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "signByKeywordNINV";
		String zqid = ZqsignManage.ZQID;

		Map<String, String> map = new HashMap<String, String>();

		map.put("zqid", zqid);// ---------需要用户修改
		map.put("no", "5deb6e7cb4f14f52a2e4bf2efda209d6");// ---------需要用户修改
		map.put("keyword", "dddd");// ---------签署关键字
		map.put("user_code", "999");// ---------需要用户修改

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
	 * @param no-----合同编号
	 * @param keyword-----关键字
	 * @param userCode----用户id
	 * @param sms_id------验证码id
	 * @param sms_code-----用户填写的验证码
	 * @return
	 * @throws Exception
	 */
	public static String signByKeywordNINV(String no,String keyword,String userCode) throws Exception {

		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "signByKeywordNINV";
		String zqid = ZqsignManage.ZQID;

		Map<String, String> map = new HashMap<String, String>();

		map.put("zqid", zqid);// ---------需要用户修改
		map.put("no", no);// ---------需要用户修改
		map.put("keyword", keyword);// ---------签署关键字
		map.put("user_code",userCode );// ---------需要用户修改
		
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
