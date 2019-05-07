package com.hpedu.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MyTest {
	private int num = 10 ;
	protected int num2 = 10 ;
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Map<String,Integer> map = new HashMap<>();
		boolean flag = false ;
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String,Integer> entry :entrySet) {
			
			for(Integer integer : list) {
				if(entry.getValue() == integer) {
					flag = true ;
					break ;
				}
			}
			
		}
		System.out.println("是否存在相同元素: " + flag);
	}
	
	
}
