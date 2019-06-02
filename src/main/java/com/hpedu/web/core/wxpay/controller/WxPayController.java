package com.hpedu.web.core.wxpay.controller;

import com.hpedu.web.core.wxpay.service.WxPayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
@Controller
@RequestMapping("/")
public class WxPayController {

	@Resource
	WxPayService WxPayService;
	
	/*
	* 统一下单
	* */
	@RequestMapping(value = "/WxPayUnifiedorder", method = RequestMethod.GET)  
	@ResponseBody  
	public Object WxPayUnifiedorder(String out_trade_no) throws Exception {
	    HashMap<String,Object> map = new HashMap<String,Object>();  
	    String codeUrl = WxPayService.weixin_pay(out_trade_no);  
	    map.put("codeUrl",codeUrl);       
	    return map;  
	}  
	
	/** 
	 * 生成二维码图片并直接以流的形式输出到页面 
	 * @param code_url 
	 * @param response 
	 */  
	@RequestMapping("qr_codeImg")  
	@ResponseBody  
	public void getQRCode(String code_url,HttpServletResponse response){
		WxPayService.encodeQrcode(code_url, response);  
	}  
	
	/*统一下单回调地址*/
	@RequestMapping(value = "/weixinNotify", method = RequestMethod.POST)  
	@ResponseBody  
	public void weixinNotify(HttpServletRequest request,HttpServletResponse response) {
	    WxPayService.weixin_notify(request, response);  
	}  
}
