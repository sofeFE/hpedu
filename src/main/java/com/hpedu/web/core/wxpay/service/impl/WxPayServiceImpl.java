package com.hpedu.web.core.wxpay.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.hpedu.config.property.PayConfigUtil;
import com.hpedu.web.core.wxpay.service.WxPayService;
import com.hpedu.web.core.wxpay.util.HttpClientResult;
import com.hpedu.web.core.wxpay.util.HttpClientUtils;
import com.hpedu.web.core.wxpay.util.PayCommonUtil;
import com.hpedu.web.core.wxpay.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WxPayServiceImpl implements WxPayService {
@Autowired
    PayConfigUtil payConfigUtil ;
    
    @Override
    public String weixin_pay(String out_trade_no) throws Exception {
        // 账号信息  
        String appid = payConfigUtil.getAppID();  // appid  
        // 商业号  
        String mch_id = payConfigUtil.getMchID();  //商户id
        // key  
        String key = payConfigUtil.getKey();

        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        //随机字符串  
        String nonce_str = strTime + strRandom;

        //查询订单数据表获取订单信息  
        // 获取发起电脑 ip  
        String spbill_create_ip = payConfigUtil.getIp();
        // 回调接口   
        String notify_url = payConfigUtil.NOTIFY_URL;
        String trade_type = "NATIVE";
        String time_start = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, 1);
        String time_expire = new SimpleDateFormat("yyyyMMddHHmmss").format(ca.getTime());

        /*参数封装*/
        SortedMap<String, String> packageParams = new TreeMap<>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        packageParams.put("time_start", time_start);
        packageParams.put("time_expire", time_expire);

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
        packageParams.put("sign", sign);

//        String requestXML = WechatPayUtil.switchMap2Xml(packageParams);
//        String resXml = HttpUtil.postData(payConfigUtil.unifiedorder_api, requestXML);
        HttpClientResult httpClientResult = HttpClientUtils.doPost(payConfigUtil.unifiedorder_api, packageParams);

        Map map = XMLUtil.doXMLParse(httpClientResult.getContent());
        return (String) map.get("code_url");
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void encodeQrcode(String content, HttpServletResponse response) {
        if (content == null || "".equals(content))
            return;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); //设置字符集编码类型  
        BitMatrix bitMatrix = null;

        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 300, 300, hints);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            //输出二维码图片流  
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e) ;
        } 
    }

    @SuppressWarnings({"unchecked", "rawtypes", "unused"})
    @Override
    public String weixin_notify(HttpServletRequest request, HttpServletResponse response) {

        StringBuilder sb = getParamFromReq(request);

        //解析xml成map
        Map<String, String> map = XMLUtil.doXMLParse(sb.toString());

        //过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = handleMap(map);

        // 账号信息
        String key = payConfigUtil.getKey(); // key
        String out_trade_no = (String) packageParams.get("out_trade_no");
        //判断签名是否正确
        String resXml = "";
        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
            if ("SUCCESS".equals((String) packageParams.get("result_code"))) {// 这里是支付成功
               /* String mch_id = (String) packageParams.get("mch_id");
                String openid = (String) packageParams.get("openid");
                String is_subscribe = (String) packageParams.get("is_subscribe");

                String bank_type = (String) packageParams.get("bank_type");
                String total_fee = (String) packageParams.get("total_fee");
                String transaction_id = (String) packageParams.get("transaction_id");*/

                //TODO 成功回调后需要处理预生成订单的状态和一些支付信息
            } else {

                String err_code = (String) packageParams.get("err_code");

                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";

            }
        }
        return resXml;
    }

    private SortedMap<Object, Object> handleMap(Map<String, String> map) {
        //过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = map.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
        return packageParams;
    }

    private StringBuilder getParamFromReq(HttpServletRequest request) {

        //读取参数
        try (InputStream inputStream = request.getInputStream();
             BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));) {
            String s;
            StringBuilder sb = new StringBuilder();
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }

            return sb;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
