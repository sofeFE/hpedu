package com.hpedu.util.codeUtil;

import com.alibaba.fastjson.JSONObject;
import com.hpedu.web.core.wxpay.util.HttpClientUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/*微信图片上传（第二种方法）：
http://ask.csdn.net/questions/176216
http://blog.csdn.net/zj8692286/article/details/40891335
*/public class WeChatApiUtil2 {
	 // token 接口(GET)
    private static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    // 素材上传(POST)
    private static final String UPLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload";//支持各种类型文件上传
    private static final String UPLOAD_MEDIA_Img = "http://file.api.weixin.qq.com/cgi-bin/media/uploadimg";//只支持图片(jpg/png)上传
    /**
     * 通用接口获取Token凭证
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getToken(String appId, String appSecret) {
        if(appId==null||appSecret==null){
            return null;
        }

        String token = null;
        String tockenUrl = WeChatApiUtil.getTokenUrl(appId, appSecret);
        String data= HttpClientUtil.get(tockenUrl);
        token=JSONObject.parseObject(data).getString("access_token");
        return token;
    }
    public static String getTokenUrl(String appId, String appSecret) {
        return String.format(ACCESS_TOKEN, appId, appSecret);
    }
	/**
	* 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	* 
	* @param url 请求地址 form表单url地址
	* @param filePath 文件在服务器保存路径
	* @return String url的响应信息返回值
	* @throws IOException
	*/
	public String send(String filePath,String token,String type,int uploadType) throws IOException {
	String result = null;
	File file = new File(filePath);
	if (!file.exists() || !file.isFile()) {
	throw new IOException("文件不存在");
	}
	/**
	* 第一部分
	*/
	 String url =uploadType==0?UPLOAD_MEDIA: UPLOAD_MEDIA_Img;
	 url=url+"?access_token="+token+"&type="+type;
	URL urlObj = new URL(url);
	// 连接
	HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();


	/**
	* 设置关键值
	*/
	//con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
	con.setRequestMethod("GET"); // 以get方式提交
	con.setDoInput(true);
	con.setDoOutput(true);
	con.setUseCaches(false); // post方式不能使用缓存


	// 设置请求头信息
	con.setRequestProperty("Connection", "Keep-Alive");
	con.setRequestProperty("Charset", "UTF-8");


	// 设置边界
	String BOUNDARY = "----------" + System.currentTimeMillis();
	con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);


	// 请求正文信息


	// 第一部分：
	StringBuilder sb = new StringBuilder();
	sb.append("--"); // 必须多两道线
	sb.append(BOUNDARY);
	sb.append("\r\n");
	sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
	+ file.getName() + "\"\r\n");
	//sb.append("Content-Type: "+type+" \r\n\r\n".getBytes());

	sb.append("Content-Type:application/octet-stream\r\n\r\n");

	byte[] head = sb.toString().getBytes("utf-8");


	// 获得输出流
	OutputStream out = new DataOutputStream(con.getOutputStream());
	// 输出表头
	out.write(head);
	
	// 文件正文部分
	// 把文件已流文件的方式 推入到url中
	DataInputStream in = new DataInputStream(new FileInputStream(file));
	int bytes = 0;
	byte[] bufferOut = new byte[1024];
	while ((bytes = in.read(bufferOut)) != -1) {
	out.write(bufferOut, 0, bytes);
	}
	in.close();


	// 结尾部分
	byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线


	out.write(foot);


	out.flush();
	out.close();


	StringBuffer buffer = new StringBuffer();
	BufferedReader reader = null;
	try {
	// 定义BufferedReader输入流来读取URL的响应
	reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String line = null;
	while ((line = reader.readLine()) != null) {
	//System.out.println(line);
	buffer.append(line);
	}
	if(result==null){
	result = buffer.toString();
	//result=result.replace("\\", "");
	}
	} catch (IOException e) {
	System.out.println("发送POST请求出现异常！" + e);
	e.printStackTrace();
	throw new IOException("数据读取异常");
	} finally {
	if(reader!=null){
	reader.close();
	}

	}


	return result;


	}


	public static void main(String[] args) throws IOException {
	String filePath = "E:/ss.png";
	String appId = "wx04a789167b966958";
    String appSecret = "2ff2f3323ddc8edf40130322d41d8312";
    String token = WeChatApiUtil.getToken(appId, appSecret);
    String type="image";//image/voice/video/thumb
	WeChatApiUtil2 fileUpload = new WeChatApiUtil2();
	String result =fileUpload.send(filePath, token, type,1);
	System.out.println(result);
	} 


}
