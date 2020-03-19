package com.live.test.api.core.date.ju;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类 基于 java.util.Calendar
 * 
 * @author live
 */
public class DateUtil {

	// 毫秒数
	/**
	 * 1秒 的毫秒数
	 */
	public static final long MILLISECOND_OF_SECOND = 1000;
	/**
	 * 1分钟 的毫秒数
	 */
	public static final long MILLISECOND_OF_MINUTE = MILLISECOND_OF_SECOND * 60;
	/**
	 * 1小时 的毫秒数
	 */
	public static final long MILLISECOND_OF_HOUR = MILLISECOND_OF_MINUTE * 60;
	/**
	 * 1天 的毫秒数
	 */
	public static final long MILLISECOND_OF_DAY = MILLISECOND_OF_HOUR * 24;

	// format
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy_MM_dd
	 */
	public static final String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

	/**
	 * yyyy-MM
	 */
	public static final String FORMAT_yyyy_MM = "yyyy-MM";

	// 日期比较
	/**
	 * 判断两个日期是否同日
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqualDay(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		int i = calendar1.get(Calendar.DAY_OF_YEAR);
		int i2 = calendar2.get(Calendar.DAY_OF_YEAR);
		return i == i2;
	}

	/**
	 * 判断两个日期是否同周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqualWeek(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		int week1 = calendar1.get(Calendar.WEEK_OF_MONTH);
		int week2 = calendar2.get(Calendar.WEEK_OF_MONTH);
		return isEqualMonth(date1, date2) && week1 == week2;
	}

	/**
	 * 判断两个日期是否同月
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqualMonth(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
				&& calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
	}

	// 获取指定日期
	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getToday() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * 获取今天 00:00:00
	 * 
	 * @return
	 */
	public static Date getToday0() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);// 24小时制
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取今天23:59:59
	 */
	public static Date getToday24() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getToday0());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}

	/**
	 * 获取 次日0点0分0秒
	 * 
	 * @return Date
	 */
	public static Date getNextDay0() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);// 加1表示明天的0点
		calendar.set(Calendar.HOUR_OF_DAY, 0);// 24小时制
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取几天前
	 * 
	 * @return
	 */
	public static Date getBeforeDay(int n) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -n);
		return c.getTime();
	}

	/**
	 * 获取几天后
	 * 
	 * @return
	 */
	public static Date getAfterDay(int n) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, n);
		return c.getTime();
	}

	/**
	 * 获取指定年月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		int firstDay = cal.getMinimum(Calendar.DATE);
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_yyyy_MM_dd_HH_mm_ss);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获取指定年月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_yyyy_MM_dd_HH_mm_ss);
		return sdf.format(cal.getTime());
	}

	// Date 转 String
	/**
	 * Date 转 String(默认格式:yyyy_MM_dd_HH_mm_ss)
	 * 
	 * @param date
	 * @return
	 */
	public static String coverToString(Date date) {
		return new SimpleDateFormat(DateUtil.FORMAT_yyyy_MM_dd_HH_mm_ss).format(date);
	}

	/**
	 * Date 转 String
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String coverToString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * Date 转 String (年月日)
	 * 
	 * @param date
	 * @return
	 */
	public static String coverToDay(Date date) {
		return coverToString(date, FORMAT_yyyy_MM_dd);
	}

	/**
	 * Date 转 String (年月)
	 * 
	 * @param date
	 * @return
	 */
	public static String coverToMonth(Date date) {
		return coverToString(date, FORMAT_yyyy_MM);
	}

	/**
	 * 获取当前时刻 的时间戳
	 * 
	 * @return 毫秒
	 */
	public static long getNowMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取当前时刻 距离 次日0点0分0秒 的毫秒数
	 * 
	 * @return 毫秒
	 */
	public static long getNowToZeroMillis() {
		return getZeroMillis() - getNowMillis();
	}

	/**
	 * 获取 Date 的时间戳
	 * 
	 * @return 毫秒
	 */
	public static long getDateMillis(Date date) {
		return date.getTime();
	}

	/**
	 * 获取 次日0点0分0秒 的时间戳
	 * 
	 * @return 毫秒
	 */
	public static long getZeroMillis() {
		return getDateMillis(getNextDay0());
	}

	/**
	 * 获取指定日期是周几
	 * 
	 * @param date
	 * @return String
	 */
	public static String getWeekDate(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

}
