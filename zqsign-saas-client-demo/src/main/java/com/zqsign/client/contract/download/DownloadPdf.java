package com.zqsign.client.contract.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.zqsign.common.constants.ZqsignManage;
import com.zqsign.common.utils.httpclient.HttpClientUtil;
import com.zqsign.common.utils.httpclient.HttpResponseCallBack;
import com.zqsign.common.utils.rsa.RsaSign;
import com.zqsign.model.req.SignDownloadRequest;

/**
 * 
 * @ClassName: DownloadPdf   
 * @Description: 模拟PDF格式的合同下载，将请求结果放在.html文件中进行请求，就能拿到当前合同编号的.pdf格式文件。
 * @date: 2017年6月7日 上午11:05:59   
 *
 */
public class DownloadPdf {

	public static void main(String[] args) throws Exception {
		String private_key = ZqsignManage.PRIVATE_KEY;
		String request_url = ZqsignManage.REQUEST_URL + "getPdf";
		String zqid = ZqsignManage.ZQID;
		//封装参数
		SignDownloadRequest downloadRequest = new SignDownloadRequest();
		downloadRequest.setZqid(zqid);//商户的zqid,该值需要与private_key对应
		downloadRequest.setNo("1b428785-613b-4608-b15a-1e2b5c201c12");//已存在的合同编号
		//签名
		Map<String, Object> map = downloadRequest.toMap();
        String content = downloadRequest.createLinkString(map);// 对请求进行签名加密
        String sign_val = RsaSign.sign(content,private_key);
		
		map.put("sign_val", sign_val);//添加签名值
		//发送请求
		byte[] response_str = HttpClientUtil.doPost(request_url, map, new HttpResponseCallBack<byte[]>() {
			@Override
			public byte[] doResponse(HttpResponse response) throws IOException {
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					byte[] result = EntityUtils.toByteArray(entity);
					EntityUtils.consume(entity);
					return result;
				}
				return null;
			}
		});
		//把接收的文件存在磁盘上
		FileOutputStream fileOutputStream = new FileOutputStream("D:/test.pdf");
		IOUtils.write(response_str, fileOutputStream);
		System.out.println("当前文件流已转成PDF文件放在  D:/test.pdf");

	}

}
