package com.hpedu.web.core.wxpay.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.hpedu.util.codeUtil.BaseUtil;

/**
 * 
 * @author louiseliu
 *
 */
public class Signature {

	public static String generateSign(Map<String, String> map){
    	Map<String, String> orderMap = MapUtil.order(map);
    	  
    	String result = MapUtil.mapJoin(orderMap,true,false);
//        result += "&key=AQuickBrownFoxJumpsOverALazyDog1"; //+ Configure.getKey();
    	result += "&key=abd5bd7027c0cce28db49698f94c4ee0";
        System.out.println("原串"+result);
        result = MD5.MD5Encode(result).toUpperCase();
       
        return result;
    }
	public static Map<String,String> get_Signature(String url_path) throws IOException{
		 Map<String,String> map=new HashMap<String, String>();
		 //String timestamp=BaseUtil.get_timestamp();
		 String newst = System.currentTimeMillis()+"";
		 String timestamp=newst.substring(0, 10);//时间戳
		 String nonceStr="Wm3WZYTPz0wzccnW";//随机数
		 java.util.Properties pro=BaseUtil.readPorpertites("application.yml");
		 String APPID=pro.getProperty("wechat_appid");
		 String APPSECRET=pro.getProperty("wechat_secret");
		 //获取access_token（时限：7200s）
		 String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
		 Map<String,Object> mymap1=JSONObject.parseObject( HttpClientUtil.httpGet(url), Map.class);
		 String access_token=(String) mymap1.get("access_token");
		
		//获取临时票据
		url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		mymap1=JSONObject.parseObject( HttpClientUtil.httpGet(url), Map.class);
		String jsapi_ticket=(String) mymap1.get("ticket");
		
	    map.put("jsapi_ticket", jsapi_ticket);//临时票据
	    map.put("noncestr",  nonceStr);
	    String http_URL=pro.getProperty("http_URL");
	    //url+="/carRental"+orderId;//当前调用js接口的网页的完整url
	    url=http_URL+"/carRental"+url_path;
	    map.put("timestamp", timestamp);
	    map.put("url", url);
		//生成签名
    	Map<String, String> orderMap = MapUtil.order(map);
    	String signature = MapUtil.mapJoin(orderMap,true,false);
     	signature =BaseUtil.SHA1(signature);
        map.put("signature", signature);
        map.put("wechat_appid", APPID);
        map.put("http_URL", http_URL+"/carRental");
        System.out.println("ccccc================"+JSONObject.toJSON(map).toString());
        return map;
    }
	
	public static String generateSign1(Map<String, String> map){
    	Map<String, String> orderMap = MapUtil.order(map);
    	  
    	String result = MapUtil.mapJoin(orderMap,true,false);
//        result += "&key=AQuickBrownFoxJumpsOverALazyDog1"; //+ Configure.getKey();
//    	result += "&key=abd5bd7027c0cce28db49698f94c4ee0";
//        System.out.println("原串"+result);
        result = MD5.MD5Encode(result).toUpperCase();
       
        return result;
    }
}

