package com.hpedu.web.core.wxpay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface WxPayService  {

	  String weixin_pay(String out_trade_no) throws IOException;
	  void encodeQrcode(String content,HttpServletResponse response) ;
	  void weixin_notify(HttpServletRequest request,HttpServletResponse response) ;
}
