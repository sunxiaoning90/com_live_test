package com.live.test.api.core.date.ju;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil_tmp2 {
	//年月日中的日
	private static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd");
	//年月日中的月
	private static SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyyMM");
	//年月日中的年
	private static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	
	/**
	 * 获取夜间执行的时间戳
	 * @return Long
	 */
	public static Long getDelayTime(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
	    c.set(Calendar.HOUR_OF_DAY, 1);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    System.out.println(c.getTime());
	    return c.getTimeInMillis()/1000;
	}
	private static Calendar getCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar;
	}
	/**
	 * 获取昨天日期
	 * @author Cat
	 * @DateTime Nov 25, 2019
	 */
	private static Date getYesterdayDateTime() {
		Calendar calendar = getCalendar();
		calendar.add(Calendar.DATE,-1);
		return calendar.getTime();
	}
	/**
	 * 获取昨天的日期
	 * @author Cat
	 * @DateTime Nov 22, 2019
	 */
	public static String getYesterdayDate() {
        return sdfDay.format(getYesterdayDateTime());
	}
	/**
	 * 获取昨天的月份
	 * @author Cat
	 * @DateTime Nov 22, 2019
	 */
	public static String getYesterdayMonth() {
        return sdfMonth.format(getYesterdayDateTime());
	}
	/**
	 * 获取当天日期
	 * @author Cat
	 * @DateTime Nov 22, 2019
	 */
	private static Date getTodayDateTime() {
		Calendar calendar = getCalendar();
		calendar.add(Calendar.DATE,0);
        return calendar.getTime();
	}
	/**
	 * 获取当天日期
	 * @author Cat
	 * @DateTime Nov 22, 2019
	 */
	public static String getTodayDate() {
        return sdfDay.format(getTodayDateTime());
	}

	/**
	 * 获取昨天的所属月份
	 * @author Cat
	 * @DateTime Nov 25, 2019
	 */
	public static String getEndTheMonth() {
		return sdfMonth.format(getYesterdayDateTime());
	}
	/**
	 * 获取昨天的所属年份
	 * @author Cat
	 * @DateTime Nov 25, 2019
	 */
	public static String getEndTheYear() {
		return sdfYear.format(getYesterdayDateTime());
	}
	/**
	 * 获取四天前的日期
	 * @author Cat
	 * @DateTime Nov 26, 2019
	 */
	public static String isThreeDaysBefore() {
	    Calendar calendar1 = Calendar.getInstance();
	    calendar1.add(Calendar.DATE, -4);
	    String three_days_ago = sdfDay.format(calendar1.getTime());
	    return three_days_ago;
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(getDelayTime());
	}
}
