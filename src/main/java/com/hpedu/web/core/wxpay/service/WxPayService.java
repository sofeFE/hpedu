package com.hpedu.web.core.wxpay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WxPayService  {

	  String weixin_pay(String out_trade_no) throws  Exception;
	  void encodeQrcode(String content,HttpServletResponse response) ;
	  String weixin_notify(HttpServletRequest request, HttpServletResponse response) ;
}
