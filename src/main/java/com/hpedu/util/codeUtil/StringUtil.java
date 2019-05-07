package com.hpedu.util.codeUtil;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * 字符工具
 * 
 * 
 */
public final class StringUtil {

	private StringUtil() {// prevent instantiation
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 获得指定格式的当前时间
	 * 
	 * @author Administrator
	 * @type:yyyy年份，MM月份，dd日期，hh12小时制，HH24小时制，mm分，ss秒 TODO 要更改此生成的类型注释的模板，请转至 窗口
	 *                                                － 首选项 － Java － 代码样式 － 代码模板
	 */
	public static String getCurrentTime(String type) {
		String time;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(type);
		time = formatter.format(cal.getTime());
		return time;
	}

	/**
	 * 返回str的前maxLength个字符的String， 如果str的长度大于maxLength，返回值将以"..."代替多余的部分。
	 * 如果参数maxLength小于0，将抛出BadInputException 如果参数str为null将返回空字符串.
	 */
	public static String getShorterString(String str, int maxLength) {
		if (maxLength < 0)
			return "";
		if (str == null)
			return "";
		if (str.length() <= maxLength)
			return str;
		return str.substring(0, maxLength) + "...";
	}

	// 过滤变量为空(null)的情况,如果不为空要过滤关键字符(以_分隔)如,_|
	public static String filteStr(Object str, String c) {
		if (str == null)
			return "";
		String strTmp = "";
		try {
			strTmp = (String) str;
			if (!c.equals("")) {
				String t[] = c.split("_");// 没有这个分隔符,就拆分出一个值
				for (int i = 0; i < t.length; i++) {
					// System.out.println("****************:" + t[i]);
					strTmp = strTmp.replaceAll(t[i], "");
				}
			}
		} catch (Exception e) {
			return "";
		}
		return strTmp;
	}

	public static String filteSQLchar(String src) {
		if (src == null || src.equals(""))
			return "";
		String strTmp = src;
		strTmp = strTmp
				.replaceAll(
						"\\s+(and|AND|exec|EXEC|insert|INSERT|select|SELECT|delete|DELETE|update|UPDATE|count|COUNT|or|OR|chr|mid|master|truncate|char|declare|DECLARE)\\s+",
						"");
		return strTmp;
	}

	public static String clearNull(Object data) {
		if (data == null)
			return "";
		if (!(data instanceof String))
			return "";
		return (String) data;
	}

	public static String filteJavaScriptChar(String src) {
		if (src == null || src.equals(""))
			return "";
		String strTmp = src;
		strTmp = strTmp.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		return strTmp;
	}

	public static String urlEncode(String str, String character) {
		try {
			str = java.net.URLEncoder.encode(str, character);
		} catch (Exception n) {
			System.out.println("[Encode] " + n);
		}
		return str;
	}

	public static String urlDecode(String str, String character) {
		try {
			str = java.net.URLDecoder.decode(str, character);
		} catch (Exception n) {
			System.out.println("[decode] " + n);
		}
		return str;
	}

	// 数组转字符串
	public static String ArrayToString(String[] arr) {
		StringBuffer bf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			bf.append(arr[i]);
		}
		return bf.toString();
	}

	// sha1加密
	public static String SHA1Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buf.toString().toUpperCase();
	}

	/**
	 * 将字节数组转换成16进制字符串
	 * 
	 * */
	public static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为16进制字符串
	 * 
	 * */
	public static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

	/**
	 * 把数据库中blob类型转换成String类型
	 * @param blob
	 * @return
	 */
	public static String convertBlobToString(Blob blob) {

		String result = "";
		try {
			ByteArrayInputStream msgContent = (ByteArrayInputStream) blob
					.getBinaryStream();
			byte[] byte_data = new byte[msgContent.available()];
			msgContent.read(byte_data, 0, byte_data.length);
			result = new String(byte_data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
