package com.hpedu.util.codeUtil;


import java.util.UUID;

/**
 * 通用唯一识别码 
 *
 */
public class UUIDUtil {
	
	/**
	 * 获取java生成
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static void main(String[] args) {
		
		System.out.println(UUIDUtil.getUUID());
	}
	
}