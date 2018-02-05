package com.zqsign.common.utils.httpclient;

import java.util.Map;

import com.zqsign.common.utils.rsa.RsaSign;


public class ZqSubmit {
	
	/**
	 * 根据参数信息生成接口请求；
	 * @param strUserRegInfo 接口参数信息
	 * @return
	 * @throws Exception
	 * String strUserRegInfo
	 */
   public static String buildRequest(Map<String, ? extends Object>  map ,String netURL,String privateKey) throws Exception {
	   //待请求参数数组
	   //私钥
	   String wsFormInfo = RsaSign.createLinkString(map);
	   //生成签名结果
	   String wsSignVal=RsaSign.sign(wsFormInfo,privateKey,"utf8");
	   
	   StringBuffer sbHtml = new StringBuffer();
	   sbHtml.append("<form id=\"zqwssubmit\" name=\"zqwssubmit\" action=\"" + netURL + "\" method='post'>");
	   for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
		   sbHtml.append("<input type=\"hidden\" name='" + entry.getKey() + "' value=\"" + entry.getValue() + "\"/>");
	   }
	   sbHtml.append("<input type=\"hidden\" name='sign_val' value=\"" + wsSignVal + "\"/>");
	   //submit按钮控件请不要含有name属性
	   sbHtml.append("<input type=\"submit\" value='确认' style=\"display:none;\"></form>");
	   sbHtml.append("<script>document.forms['zqwssubmit'].submit();</script>");
	   
	   return sbHtml.toString();
   }
}	
