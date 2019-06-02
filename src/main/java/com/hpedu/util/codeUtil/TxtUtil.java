package com.hpedu.util.codeUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//txt文件操作
public class TxtUtil {
	//-----------------------------txt导出
	 //第一种：
	//流的方式直接输出文件（数据量小的情况）  
    public static void writeToTxt(HttpServletResponse response,List<String[]> list,String[] titles,String filename) {  
  
        response.setContentType("text/plain");// 一下两行关键的设置  
        response.addHeader("Content-Disposition",  
                "attachment;filename="+filename+".txt");// 导出txt文件名称
        BufferedOutputStream buff = null;  
        StringBuffer write = new StringBuffer();  
        String tab = "  ";  
        String enter = "\r\n";  
        ServletOutputStream outSTr = null;  
        try {  
            outSTr = response.getOutputStream();// 建立  
            buff = new BufferedOutputStream(outSTr);  
             //标题读取
            for(String str:titles){
              write.append(str + tab);  
            }
            write.append( enter); //换行
             //数据读取
            for (int i = 0; i < list.size(); i++) {  
            	String[] row=list.get(i);
            	for(String str:row){
            		 write.append(str + tab);  
            	}
                write.append( enter); //换行
            }  
            buff.write(write.toString().getBytes("UTF-8"));  
            buff.flush();  
            buff.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                buff.close();  
                outSTr.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    //第二种方式-----------先在服务器生成文件，在下载（数据量大的情况）
    // 输出TXT  
        public static void writeToTxt_load(HttpServletRequest request,List<String[]> list,String[] titles) {  
            FileOutputStream outSTr = null;  
            BufferedOutputStream Buff = null;  
            String path = request.getSession().getServletContext().getRealPath("file/downLoad.txt");  //项目根目录的file文件夹下 
            String tab = "  ";  
            String enter = "\r\n";  
            StringBuffer write = new StringBuffer();  
            try {  
            	
                outSTr = new FileOutputStream(new File(path));  
                Buff = new BufferedOutputStream(outSTr); 
                //标题读取
                for(String str:titles){
                  write.append(str + tab);  
                }
                write.append( enter); //换行
                //数据读取
                for (int i = 0; i < list.size(); i++) {  
                	String[] row=list.get(i);
                	for(String str:row){
                		 write.append(str + tab);  
                	}
                    write.append( enter); //换行
                }  
                Buff.write(write.toString().getBytes("UTF-8"));   
                Buff.flush();  
                Buff.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    Buff.close();  
                    outSTr.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  

        //下载  
        public static void downFile(HttpServletRequest request, HttpServletResponse response,String url,String uploadAbsolutePath)  
                throws Exception {  
        	InputStream fis = null ;
       	 OutputStream toClient = null ;
           try {  
//           	String relativePath = new String(url.getBytes("ISO8859-1"),"UTF-8");// 未在tomcat配置文件server.xml中 设置 URIEncoding="utf-8" 情况下.
           		String relativePath = url ;
               File file = new File(uploadAbsolutePath + relativePath);  
               String filename = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
               response.reset();  
               response.addHeader("Content-Disposition", "attachment;filename="+filename.replace(" ", ""));  
               response.addHeader("Content-Length", "" + file.length()); // 设置返回的文件类型  
               toClient = new BufferedOutputStream(response.getOutputStream()); // 得到向客户端输出二进制数据的对象  
               // 根据扩展名声称客户端浏览器mime类型  
               response.setContentType("application/octet-stream"); // 设置返回的文件类型
               
               fis = new BufferedInputStream(new FileInputStream(uploadAbsolutePath + relativePath));  
               byte[] buffer = new byte[4096]; 
               while(fis.read(buffer)!=-1){
               	toClient.write(buffer); // 输出数据  
               	toClient.flush();  
               }
               
           } catch (IOException ex) {  
               ex.printStackTrace();  
           } finally{
           	try {
					if(fis!=null) fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}  
               try {
					if(toClient!=null ) toClient.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
           }
            
        }  
    //-----------------------------------------------txt内容追加    
    public static void TXT_Appendwrite(HttpServletRequest request,int type,String content,String dir,String filename,String uploadAbsolutePath) {
    	 String filePath = BaseUtil.getServerPath( dir, filename,uploadAbsolutePath);
    	if(type==1){
    		method1(filePath,content);
    	}else if(type==2){
    		method2(filePath,content);
    	}else{
    		method3(filePath,content);
    	}
    }
    
//方法一
	public static void method1(String filepath,String content) {
	   FileWriter fw = null;
		try {
		  //如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f=new File(filepath);
			fw = new FileWriter(f, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PrintWriter pw = new PrintWriter(fw);
			pw.println(content);//追加内容
			pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
		   e.printStackTrace();
		}
	}
//方法二
public static void method2(String filepath, String conent) {
	BufferedWriter out = null;
	try {
			out = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(filepath, true)));
			out.write(conent+"\r\n");
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		try {
		   out.close();
		} catch (IOException e) {
		   e.printStackTrace();
		}
	}
}
//方法三
public static void method3(String filepath, String content) {
   try {
		// 打开一个随机访问文件流，按读写方式
		RandomAccessFile randomFile = new RandomAccessFile(filepath, "rw");
		// 文件长度，字节数
		long fileLength = randomFile.length();
		// 将写文件指针移到文件尾。
		randomFile.seek(fileLength);
		randomFile.writeBytes(content+"\r\n");
		randomFile.close();
	} catch (IOException e) {
	   e.printStackTrace();
	}
 }
 //============================txt文件读取
/**
 * 功能：Java读取txt文件的内容
 * 步骤：1：先获得文件句柄
 * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
 * 3：读取到输入流后，需要读取生成字节流
 * 4：一行一行的输出。readline()。
 * 备注：需要考虑的是异常情况
 * @param filePath
 */
public static List<String> readTxtFile(HttpServletRequest request,String dir,String filename,String uploadAbsolutePath){
	List<String> list=new ArrayList<String>();
	 String filePath = BaseUtil.getServerPath( dir, filename,uploadAbsolutePath);
    try {
           // String encoding="GBK";
    	    String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                	list.add(lineTxt);
                }
                read.close();
    }else{
        System.out.println("找不到指定的文件");
    }
    } catch (Exception e) {
        System.out.println("读取文件内容出错");
        e.printStackTrace();
    }
  return list;
}
 
}
