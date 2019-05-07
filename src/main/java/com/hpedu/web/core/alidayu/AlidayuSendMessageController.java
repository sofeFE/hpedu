package com.hpedu.web.core.alidayu;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Controller
@RequestMapping("/message")
public class AlidayuSendMessageController {

	@RequestMapping(value="/reg/ycode",method=RequestMethod.POST)
	@ResponseBody
	public int sendMessage(HttpServletRequest reqt,String tel,HttpSession session)
			 {
		// 官网的URL
		String url = "http://gw.api.taobao.com/router/rest";
		// 成为开发者，创建应用后系统自动生成
		String appkey = "23633603";
		String secret = "1a52fb90dd0c8c1341835f828ddae713";
		Random random = new Random();
		int x = random.nextInt(999999);
		session.removeAttribute("ycodes");
		session.setAttribute("ycodes", x);
		// 短信模板的内容
		StringBuilder jsons = new StringBuilder();
		jsons.append("{");
		jsons.append("\"code\":" + "\"" + x + "\",");
		jsons.append("\"product\":" + "\"" +"厚朴教育"+ "\"");
		jsons.append("}");
		String jsonstr = jsons.toString();
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//req.setExtend("厚朴教育");
		req.setSmsType("normal");
		req.setSmsFreeSignName("厚朴教育");
		req.setSmsParamString(jsonstr);
		req.setRecNum(tel);
		req.setSmsTemplateCode("SMS_53015044");
		try {
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			System.out.println(rsp.getBody());
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}

}
