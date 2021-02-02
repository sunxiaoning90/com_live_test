package com.live.test.javaNewFeature.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * 
 * <pre>
格式化日期

Java8日期API（LocalDate、LocalTime、LocalDateTime、DateTimeFormatter）篇

1、在java8之前我们在处理时间的时候都是用的Date,但它其实有很明显的缺点。
1)我们也会对日期做一些操作，比如加几天，加几分，当月的最后一天等等，有些计算比较复杂。
2)也会用SimpleDataFormat来格式化日期。但是SimpleDateFormat是线程不安全的。
所以现在一般都推荐使用LocalDateTime它是线程安全的，并且性能更好，代码更简洁。

2、新时间日期API常用，重要对象主要有下面三个：
LocalDate：只含年月日的日期对象
LocalTime：只含时分秒的时间对象
LocalDateTime：同时含有年月日时分秒的日期对象

3、DateTimeFormatter 如何保证线程安全的
没有在任何共享变量，故线程安全
 * </pre>
 * 
 * @author live
 */
public class TestLocalDate {
	
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		System.out.println(date);

		LocalTime time = LocalTime.now();
		System.out.println(time);

		LocalDateTime datetime = LocalDateTime.now();
		System.out.println(datetime);
		System.out.println(datetime.toLocalDate());
		System.out.println(datetime.toLocalTime());

		// 解析字符串
		LocalTime t = LocalTime.parse("20:15:30");
		System.out.println("time: " + t);
		
		new TestLocalDate().testFormat();
	}

	@Test
	public void testFormat() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		// 一天开始时间
		LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		String format = todayStart.format(dateTimeFormatter);
		System.out.println("format = " + format);
		// 输出: format = 2020-07-07 00:00:00

		// 一天结束时间
		LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		String format1 = todayEnd.format(dateTimeFormatter);
		System.out.println("format1 = " + format1);
		// 输出: format1 = 2020-07-07 23:59:59

		// 一天中午时间
		LocalDateTime todayMid = LocalDateTime.of(LocalDate.now(), LocalTime.NOON);
		String format2 = todayMid.format(dateTimeFormatter);
		System.out.println("format2 = " + format2);
		// 输出: format2 = 2020-07-07 12:00:00
	}
}
