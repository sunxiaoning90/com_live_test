package com.live.test.javase.core.jtext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {
	public static void main(String[] args) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
//        format.setLenient(false);  
//        format.setLenient(true);  被解析的字符串是否是宽松的，如下33日被解析为次月2日
        Date date = null;
		try {
			date = format.parse("33/12/2011");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println(date);  
	}
}
