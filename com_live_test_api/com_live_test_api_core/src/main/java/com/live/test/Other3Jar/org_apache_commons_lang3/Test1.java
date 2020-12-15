package com.live.test.Other3Jar.org_apache_commons_lang3;

import org.apache.commons.lang.StringUtils;

public class Test1 {

	public static void main(String[] args) {
		String s = "abc123";
		System.out.println("原始string："+s);
		
		String s1 = StringUtils.capitalize(s);
		System.out.println("capitalize，首字母大写："+s1);
		
		String s2 = StringUtils.uncapitalize(s1);
		System.out.println("uncapitalize，首字母小写："+s2);
		
	}
	
}
