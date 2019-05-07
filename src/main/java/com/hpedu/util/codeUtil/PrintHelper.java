package com.hpedu.util.codeUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
/**
 * 专门处理--返回给前台控件格式化的数据，多以json为主
 * @author yhr
 *
 */
/**
 * @author 赖佳
 * @date 2016-09-22
 */
public class PrintHelper {
	Log log = LogFactory.getLog(PrintHelper.class);
	
	//将map转成json串
		public static String  changeMapToJson(Map<String,Object> map,int type) throws IOException {
			//1.转成JSONArray类型 
			//[{"users":[{"password":"1234","username":"cxl"},{"password":"1234","username":"lhl"}],"u":{"password":"1234","username":"lhl"}}] 
			//js中取数据：alert(data[0].users[0].username); 
			String json = JSONArray.toJSON(map).toString() ;
			
			//2.转成JSONObject类型 
			//{"user":[{"password":"1234","username":"cxl"},{"password":"1234","username":"lhl"}],"u":{"password":"1234","username":"lhl"}} 
			//js中取数据：alert(data.user[0].username); 
			String json2 = JSONObject.toJSON(map).toString();
			return type==0?json2:json;
		} 
		//将list转成json串
		public static String  changeListToJson(List<Object> list) throws IOException {
			  return  JSONArray.toJSON(list).toString();
		}
			//将obj转成json串
		public static String  changeObjectToJson(Object obj) throws IOException {
			  return  JSONObject.toJSON(obj).toString();
		}
		//将json转成list
		public static List<Map<String,Object>> changeJsonToList(String jsonStr) throws IOException {
	        List<Map<String,Object>> listFromJson = (List)JSONArray.parse(jsonStr) ;
			return listFromJson; 
		}
		//将json转成Map
		public static Map<String,Object> changeJsonToMap(String jsonStr) throws IOException {
		        Map<String,Object> mapFromJson = (Map)JSONObject.parse(jsonStr);
				return mapFromJson; 
		}
	/**
	 * @return object json:{}
	 * @param response
	 * @param olist
	 * @param total
	 * @throws IOException
	 */
	public static <T> void sendJsonMap(HttpServletResponse response,T olist,int total) throws IOException {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows",olist);
		String  jsonstr= JSONObject.toJSON(map).toString();
		System.out.println("(***)"+jsonstr);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonstr);
		out.close();
		out.flush();
		
	}
	
	/**
	 * @return object json:{}
	 * @param response
	 * @param map
	 * @throws IOException
	 */
	public static <T> void sendJsonObject(HttpServletResponse response,T o) throws IOException {
	
		String  jsonstr= JSONObject.toJSON(o).toString();
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonstr);
		out.close();
		out.flush();
		
	}
	/**
	 * @return array json:[]
	 * @param response
	 * @param t
	 * @throws IOException
	 */
	public static    void sendJsonArray(HttpServletResponse response,List  t) throws IOException{
		/*Map<String,Object> map=new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows",olist);*/
		String  jsonstr=JSONArray.toJSON(t).toString();
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonstr);
		out.close();
		out.flush();
	}
	public static <T> void sendJsonString(HttpServletResponse response,T  str) throws IOException{
		//response.setContentType("text/html;charset=gbk");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(str);
		out.close();
		out.flush();
	}

	public static <T> void sendJsonHtm(HttpServletResponse response,T  htm) throws IOException{
		//response.setContentType("text/html;charset=gbk");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(htm);
		out.close();
		out.flush();
	}
	public static void downloadFile4AnyFile(HttpServletResponse response,File file,String customFileName) throws UnsupportedEncodingException{
		if(!file.exists()){
			return;
		}
		if(StringUtils.isEmpty(customFileName)){
			customFileName = file.getName();
		}
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");  
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
        response.setHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(customFileName,"UTF-8"));  
        ServletOutputStream out = null;  
        FileInputStream inputStream = null;
        try {  
            inputStream = new FileInputStream(file);  
  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
  
            int b = 0;  
            byte[] buffer = new byte[1024];  
            while (b != -1){  
                b = inputStream.read(buffer);  
                //4.写到输出流(out)中  
                if(b!=-1){
                	out.write(buffer,0,b);  
                }
                
            }  
            inputStream.close();  
            out.flush();  
            out.close();  
          
  
        } catch (IOException e) { 
        	try {
	        	if(inputStream!=null){
					inputStream.close();
	        	}
	        	if(out!=null){
	        		out.flush();  
	                out.close();  
	        	}
        	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
            e.printStackTrace();  
        }  
	}
	public static void downloadFile4Excel(HttpServletResponse response,File file,String customFileName) throws UnsupportedEncodingException{
		if(!file.exists()){
			return;
		}
		if(StringUtils.isEmpty(customFileName)){
			customFileName = file.getName();
		}
		if(!customFileName.endsWith(".xlsx")&&!customFileName.endsWith(".xls")){
			customFileName +=".xlsx";
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/vnd.ms-excel");
        //response.setContentType("multipart/form-data");
		 /**解决下载的中文名无法显示的问题
         * response.setHeader("Content-Disposition", "attachment; filename=" +  URLEncoder.encode(filename, "UTF-8")); 
         * 
         * fileName = fileName.Replace("+", " ");
         */
        response.setHeader("Content-Disposition", "attachment;fileName="
                + URLEncoder.encode(customFileName,"UTF-8").replace("+", " "));
       
        InputStream inputStream = null;
        OutputStream os = null;
        try {
          
            inputStream = new FileInputStream(file);
 
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
 
             // 这里主要关闭。
            os.close();
            inputStream.close();
        
        } catch (Exception e) {
        	 try {
        		  if(os!=null){
        				os.close();
        		  }
        		  if(inputStream!=null){
        			  inputStream.close();
        		  }
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
             
            e.printStackTrace();
        }
            //  返回值要注意，要不然就出现下面这句错误！
            //java+getOutputStream() has already been called for this response

	}
}
