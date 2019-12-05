package com.live.test.javase.core.date;

import java.util.Calendar;

/**
 * 日期工具类
 * 基于 java.util.Calendar
 * 
 * @author live
 * @2019年12月4日 @下午3:26:59
 */
public class DateUtil {

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
	 * 获取 次日0点0分0秒 的时间戳
	 * 
	 * @return 毫秒
	 */
	public static long getZeroMillis() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);// 加1表示明天的0点
		calendar.set(Calendar.HOUR_OF_DAY, 0);// 24小时制
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTimeInMillis();
	}

}
