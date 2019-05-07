package com.hpedu.web.core.wxpay.util;



/**
 * 固定参数[未完]
 * */
public class PayConfigUtil {
	//这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）  
    // 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证  
    // 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改  
	 // private static String key = "abd5bd7027c0cce28db49698f94c4ee0";  //测试使用(32位)
    private static String key = "13045680451sth55272134537148lrsq";  
    
    //微信分配的公众号ID（开通公众号之后可以获取到）  
    //private static String appID = "wx8e8da9656e2c5c0d";  //测试使用
    private static String appID = "wx02126f2ce37b2633"; 
  
    //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）  
    //private static String mchID = "1300674301";//测试使用
    private static String mchID = "1456255802";
    //机器IP  
    //private static String ip = "127.0.0.1";  //测试使用
    private static String ip = "123.57.224.170";  
   //private static String WEB_SERVICE_PATH = "http://157i04614c.imwork.net/edu";//测试使用
    private static String WEB_SERVICE_PATH = "http://123.57.224.170/edu";
    public static String getWEB_SERVICE_PATH() {
		return WEB_SERVICE_PATH;
	}

	public static void setWEB_SERVICE_PATH(String wEB_SERVICE_PATH) {
		WEB_SERVICE_PATH = wEB_SERVICE_PATH;
	}

	//随机串
    private static String nonce_str=WechatPayUtil.getRandomStr(10);
    //时间戳
    private static String time_stamp=WechatPayUtil.gettime_stamp();
    //以下是几个API的路径：  
    //1）被扫支付API  
    //public static String PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";  
    public static String PAY_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";  
    //2）被扫支付查询API  
    public static String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";  
  
    //3）退款API  
    public static String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";  
  
    //4）退款查询API  
    public static String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";  
  
    //5）撤销API  
    public static String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";  
  
    //6）下载对账单API  
    public static String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";  
  
    //7) 统计上报API  
    public static String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";  
      
    //回调地址  
    public static String NOTIFY_URL = ""; //测试  
  
  
    public static String HttpsRequestClassName = "com.entplus.wxpay.util.HttpsRequest";  
  
    public static void setKey(String key) {  
        PayConfigUtil.key = key;  
    }  
  
   
    public static void setMchID(String mchID) {  
        PayConfigUtil.mchID = mchID;  
    }  
  
    public static void setIp(String ip) {  
        PayConfigUtil.ip = ip;  
    }  
  
    public static String getKey(){  
        return key;  
    }  
      
    public static String getAppid(){  
        return appID;  
    } 
    public static void setAppID(String appID) {  
        PayConfigUtil.appID = appID;  
    }  
  
      
    public static String getMchid(){  
        return mchID;  
    }  
  
  
  
    public static String getIP(){  
        return ip;  
    }  
  
    public static void setHttpsRequestClassName(String name){  
        HttpsRequestClassName = name;  
    }

	public static String getNonce_str() {
		return nonce_str;
	}

	public static void setNonce_str(String nonce_str) {
		PayConfigUtil.nonce_str = nonce_str;
	}

	public static String getTime_stamp() {
		return time_stamp;
	}

	public static void setTime_stamp(String time_stamp) {
		PayConfigUtil.time_stamp = time_stamp;
	}

	
	
	
}
