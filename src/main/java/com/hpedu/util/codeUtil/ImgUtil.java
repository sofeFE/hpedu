package com.hpedu.util.codeUtil;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
/**
 * 图像 处理
 *
 */
public class ImgUtil {
    /**
     * 把base64图片数据转为本地图片
     * @param base64ImgData
     * @param filePath
     * @throws IOException
     */
    public static String convertBase64DataToImage(String base64ImgData,String filePath){
        FileOutputStream os = null;
		try {
			BASE64Decoder d = new BASE64Decoder();
			byte[] bs = d.decodeBuffer(base64ImgData);
			os = new FileOutputStream(filePath);
			os.write(bs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        return filePath;
    }
    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imgUrl
     * @return
     */
    public static String GetImageStr(String imgUrl) {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgUrl);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
/**
 * 对字节数组字符串进行Base64解码并生成图片
 * @param imgStr
 * @param path
 * @return
 */
	public static boolean GenerateImage(String imgStr,String path) {
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			/*File file = new File(path);
			if(!file.exists()){  
				file.mkdirs();  
	        }*/
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
