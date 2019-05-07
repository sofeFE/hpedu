package com.hpedu.web.core.wxpay.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpedu.web.core.wxpay.util.*;
import org.jdom.JDOMException;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.hpedu.web.core.wxpay.service.WxPayService;

@Service
public class WxPayServiceImpl implements WxPayService {

	@Override
	public String weixin_pay(String out_trade_no)  throws IOException {
		// 账号信息  
        String appid = PayConfigUtil.getAppid();  // appid  
        // 商业号  
        String mch_id = PayConfigUtil.getMchid();  //商户id
        // key  
        String key = PayConfigUtil.getKey();   
  
        String currTime = PayCommonUtil.getCurrTime();  
        String strTime = currTime.substring(8, currTime.length());  
        String strRandom = PayCommonUtil.buildRandom(4) + "";  
        //随机字符串  
        String nonce_str = strTime + strRandom;  
        // 价格   注意：价格的单位是分  
        //String order_price = "1";   
        // 商品名称  
        //String body = "企嘉科技商品";     
  
        //查询订单数据表获取订单信息  
        /***
         * 下订单开始，需要正式带入
         * */
       // PayOrder payOrder = payOrderDao.get(PayOrder.class,out_trade_no);  
        // 获取发起电脑 ip  
        String spbill_create_ip = PayConfigUtil.getIP();  
        // 回调接口   
        String notify_url = PayConfigUtil.NOTIFY_URL;  
        String trade_type = "NATIVE";  
        String time_start =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  
        Calendar ca = Calendar.getInstance();  
        ca.setTime(new Date());  
        ca.add(Calendar.DATE, 1);           
        String time_expire =  new SimpleDateFormat("yyyyMMddHHmmss").format(ca.getTime());  
        SortedMap<String,String> packageParams = new TreeMap<>();  
        packageParams.put("appid", appid);  
        packageParams.put("mch_id", mch_id);  
        packageParams.put("nonce_str", nonce_str);  
    //    packageParams.put("body",payOrder.getBody());  
        packageParams.put("out_trade_no", out_trade_no);  
        //packageParams.put("total_fee", "1");  
     //   packageParams.put("total_fee", payOrder.getTotalFee());  
        packageParams.put("spbill_create_ip", spbill_create_ip);  
        packageParams.put("notify_url", notify_url);  
        packageParams.put("trade_type", trade_type);  
        packageParams.put("time_start", time_start);  
        packageParams.put("time_expire", time_expire);          
        String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);  
        packageParams.put("sign", sign);  
         
        String requestXML = WechatPayUtil.switchMap2Xml(packageParams);
        String resXml = HttpUtil.postData(PayConfigUtil.PAY_API, requestXML);  

		Map map = null;
		try {
			map = XMLUtil.doXMLParse(resXml);
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}
		//String return_code = (String) map.get("return_code");
        //String prepay_id = (String) map.get("prepay_id");  
        String urlCode = (String) map.get("code_url");   
        System.out.println("打印调用统一下单接口生成二维码url:::::"+urlCode);  
        return urlCode;  
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void encodeQrcode(String content, HttpServletResponse response)
			 {
		 if(content==null || "".equals(content))  
		        return;  
		   MultiFormatWriter multiFormatWriter = new MultiFormatWriter();  
		   Map hints = new HashMap();  
		   hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型  
		   BitMatrix bitMatrix = null;  
		   try {  
		       bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300,hints);  
		       BufferedImage image =  MatrixToImageWriter.toBufferedImage(bitMatrix);  
		       //输出二维码图片流  
		       try {  
		           ImageIO.write(image, "png", response.getOutputStream());  
		       } catch (IOException e) {  
		           e.printStackTrace();  
		       }  
		   } catch (WriterException e1) {  
		       e1.printStackTrace();  
		   }           
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@Override
	public void weixin_notify(HttpServletRequest request,
			HttpServletResponse response)  {

		try {
			//读取参数
			InputStream inputStream ;
			StringBuffer sb = new StringBuffer();
			inputStream = request.getInputStream();

			String s ;
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			while ((s = in.readLine()) != null){
				sb.append(s);
			}
			in.close();
			inputStream.close();

			//解析xml成map
			Map<String, String> m = new HashMap<String, String>();
			m = XMLUtil.doXMLParse(sb.toString());

			//过滤空 设置 TreeMap
			SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
			Iterator it = m.keySet().iterator();
			while (it.hasNext()) {
				String parameter = (String) it.next();
				String parameterValue = m.get(parameter);

				String v = "";
				if(null != parameterValue) {
					v = parameterValue.trim();
				}
				packageParams.put(parameter, v);
			}

			// 账号信息
			String key = PayConfigUtil.getKey(); // key
			String out_trade_no = (String)packageParams.get("out_trade_no");
			//logger.info(packageParams);
			//判断签名是否正确
			if(PayCommonUtil.isTenpaySign("UTF-8", packageParams,key)) {
				//------------------------------
				//处理业务开始
				//------------------------------
				String resXml = "";
				if("SUCCESS".equals((String)packageParams.get("result_code"))){
					// 这里是支付成功
					//////////执行自己的业务逻辑////////////////
					String mch_id = (String)packageParams.get("mch_id");
					String openid = (String)packageParams.get("openid");
					String is_subscribe = (String)packageParams.get("is_subscribe");

					String bank_type = (String)packageParams.get("bank_type");
					String total_fee = (String)packageParams.get("total_fee");
					String transaction_id = (String)packageParams.get("transaction_id");

					System.out.println("mch_id:"+mch_id);
					System.out.println("openid:"+openid);
					System.out.println("is_subscribe:"+is_subscribe);
					System.out.println("out_trade_no:"+out_trade_no);
					System.out.println("total_fee:"+total_fee);
					System.out.println("bank_type:"+bank_type);
					System.out.println("transaction_id:"+transaction_id);

					//成功回调后需要处理预生成订单的状态和一些支付信息
				} else {
					System.out.println("支付失败,错误信息：" + packageParams.get("err_code")+
							"-----订单号：：："+out_trade_no+"*******支付失败时间：：：："
							+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

					String err_code = (String)packageParams.get("err_code");

					resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
							+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";

				}
				//------------------------------
				//处理业务完毕
				//------------------------------
				BufferedOutputStream out = new BufferedOutputStream(
						response.getOutputStream());
				out.write(resXml.getBytes());
				out.flush();
				out.close();
			} else{
				System.out.println("通知签名验证失败---时间::::"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
		} catch (Exception e) {
			throw new RuntimeException(e) ;
		}


	}

}
