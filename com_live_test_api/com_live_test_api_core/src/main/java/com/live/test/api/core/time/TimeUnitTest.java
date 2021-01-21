package com.live.test.api.core.time;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest {

	public static void main(String[] args) {
		long convert = TimeUnit.DAYS.convert(23, TimeUnit.HOURS); //注意：无法将 小于24小时转换为 天（数据精度导致），结果为0
		System.out.println(convert);
		
		try {
			TimeUnit.MILLISECONDS.sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
