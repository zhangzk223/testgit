package com.zqsign.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * pojo 的基类
 * @author zzk
 * 2017年4月14日上午11:30:20
 */
public abstract class BaseEntity implements Serializable{

	private static final long serialVersionUID = 2798627954144807426L;
	/**全局的map集合**/
	private Map<String, Object> context = new HashMap<>();
	
	/**签名值**/
	protected String sign_val;
	/**接口平台用户唯一标示**/
	protected String zqid;  //接口平台用户唯一标示
	
	public String getSign_val() {
		return sign_val;
	}
	//签名值设置
	public void setSign_val(String sign_val) {
		this.sign_val = sign_val;
	}
	public String getZqid() {
		return zqid;
	}
	//众签id设置
	public void setZqid(String zqid) {
		this.zqid = zqid;
		put("zqid", zqid);
	}
	/**
	 * 把当前实体转换为map
	 * @return
	 * 2017年4月14日上午11:00:24
	 */
	public abstract Map<String, Object> toMap();
	
	/**
	 * 把map转换为按一定顺序输出的请求字符串
	 * @param params
	 * @return
	 * 2017年4月14日上午11:10:22
	 */
	public String createLinkString(Map<String, ? extends Object> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = String.valueOf(params.get(key));
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }
	/**
	 * 把属性名和属性值放入contextMap
	 * @param key
	 * @param value
	 * 2017年4月16日上午10:44:14
	 */
	protected void put(String key, String value){
		this.context.put(key, value);
	}
	/**
	 * 把当前的全局属性的Map返回
	 * @return
	 * 2017年4月16日上午10:49:56
	 */
	protected Map<String, Object> get(){
		return this.context;
	}
}
