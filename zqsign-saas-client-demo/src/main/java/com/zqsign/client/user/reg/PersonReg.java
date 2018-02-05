package com.zqsign.client.user.reg;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;
import com.zqsign.model.req.SignPersonRegRequest;

/**
 * 
 * @ClassName: RegPerson
 * @Description: 模拟个人用户颁发数字证书
 * @date: 2017年6月7日 上午11:07:21
 *
 */
public class PersonReg {

	public static void main(String[] args) throws IOException {
		String user_code = UUID.randomUUID().toString();

		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "personReg";
		String zqid = ZqsignManage.ZQID;
		// 封装请求参数
		SignPersonRegRequest personRegRequest = new SignPersonRegRequest();
		personRegRequest.setZqid(zqid);// 商户的zqid,该值需要与private_key对应
		personRegRequest.setUser_code(user_code);// 用户唯一标示，该值不能重复
		personRegRequest.setName("张宗奎");// 平台方用户姓名
		personRegRequest.setId_card_no("410188198804095078");// 身份证号
		personRegRequest.setMobile("15313927710");// 联系电话（手机号码）

		// 签名
		Map<String, Object> map = personRegRequest.toMap();
		String content = personRegRequest.createLinkString(map);
		String sign_val = RsaSign.sign(content, private_key);

		map.put("sign_val", sign_val); // 请求参数的签名值
		String response_str = HttpClientUtil.sendPost(request_url, map);// 向服务端发送请求，并接收请求结果
		System.out.println("usercode:" + user_code + "==请求结果：" + response_str);// 输出服务器响应结果
	}
}