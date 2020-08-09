package com.hpedu.test;

import java.lang.reflect.Field;

public class SonTest extends Test1 {
	
	/*public static void main(String[] args) {
		new SonTest().method();
	}*/
	void method(){
		Test1 test = new Test1() ;
		Class<?> clazz = test.getClass();
		try {
			/** getField("string") 获取公共属性
			 * getDeclaredField("String")  获取 保护/私有 属性
			 * */
			Field fieldNum = clazz.getDeclaredField("num") ;
			Field fieldNum2 = clazz.getDeclaredField("num2") ;
			fieldNum.setAccessible(true);
			Integer object = (Integer)fieldNum.get(test);
			System.out.println(object  + ", fieldNum2 = " + (Integer)fieldNum2.getInt(test));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
//		System.out.println(num);
//		System.out.println(num2);
	}
}
