package com.live.test.api.core.date.ju;

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
