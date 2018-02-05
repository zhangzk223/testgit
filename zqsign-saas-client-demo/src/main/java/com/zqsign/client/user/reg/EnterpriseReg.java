package com.zqsign.client.user.reg;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;
import com.zqsign.model.req.SignEnterpriseRegRequest;

/**
 * 
 * @ClassName: RegEnterprise
 * @Description: 模拟企业用户颁发数字证书
 * @date: 2017年6月7日 上午11:07:09
 *
 */
public class EnterpriseReg {

	public static void main(String[] args) throws IOException {
		String user_code = UUID.randomUUID().toString();

		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "entpReg";
		String zqid = ZqsignManage.ZQID;
		// 封装请求参数
		SignEnterpriseRegRequest enterpriseRegRequest = new SignEnterpriseRegRequest();
		enterpriseRegRequest.setZqid(zqid);// 商户的zqid,该值需要与private_key对应
		enterpriseRegRequest.setUser_code(user_code);// 用户唯一标示，该值不能重复
		enterpriseRegRequest.setName("北京众签科技有限公司");// 企业名称
		enterpriseRegRequest.setCertificate("888888");// 营业执照号或社会统一代码
		enterpriseRegRequest.setAddress("北京");// 企业注册地址
		enterpriseRegRequest.setContact("佘宏利");// 联系人
		enterpriseRegRequest.setMobile("15201257519");// 联系电话（手机号码）
		// 签名
		Map<String, Object> map = enterpriseRegRequest.toMap();
		String content = enterpriseRegRequest.createLinkString(map);
		String sign_val = RsaSign.sign(content, private_key);

		map.put("sign_val", sign_val); // 请求参数的签名值
		String response_str = HttpClientUtil.sendPost(request_url, map);// 向服务端发送请求，并接收请求结果
		System.out.println("usercode:" + user_code + "==请求结果：" + response_str);// 输出服务器响应结果
	}
}