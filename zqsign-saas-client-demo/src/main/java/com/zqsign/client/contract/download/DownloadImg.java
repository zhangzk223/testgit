package com.zqsign.client.contract.download;

import java.util.Map;

import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.rsa.RsaSign;
import com.zqsign.model.req.SignDownloadRequest;

/**
 * 
 * @ClassName: DownloadImg   
 * @Description: 返回合同文件的图片地址，用户拿该地址能够去浏览器获得合同图片，链接有效时间5min
 * @date: 2017年6月7日 上午11:05:51   
 *
 */
public class DownloadImg {

	public static void main(String[] args) throws Exception {

		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "getImg";
		String zqid = ZqsignManage.ZQID;
		//封装参数
		SignDownloadRequest downloadRequest = new SignDownloadRequest();
		downloadRequest.setZqid(zqid);//商户的zqid,该值需要与private_key对应
		downloadRequest.setNo("f3695f8f-0899-4a67-a364-ccc3a7c7cab7");//已存在的合同编号
		//签名
		Map<String, Object> map = downloadRequest.toMap();
        String content = downloadRequest.createLinkString(map);// 对请求进行签名加密
        String sign_val = RsaSign.sign(content,private_key);
		
		map.put("sign_val", sign_val);//添加签名值
		String response_str = HttpClientUtil.sendPost(request_url, map);//向服务端发送请求，并接收请求结果
		System.out.println("请求结果：" + response_str);//输出服务器响应结果

	}

}
