package com.live.test.javaweb.core.util;

import org.slf4j.Logger;

public class LogUtil {

	public static void println(Logger log) {
		log.info(getln());
	}
	
	/**
	 * 获取一个空行
	 * @return String
	 */
	public static String getln(){
		return System.getProperty("line.separator") + " ";
	}
}
