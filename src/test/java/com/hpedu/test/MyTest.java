package com.hpedu.test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MyTest {


	public static void main(String[] args) {
		System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
		System.out.println("now= " + LocalDateTime.ofEpochSecond(1558011443,0,ZoneOffset.UTC));
	}
	
	
}
