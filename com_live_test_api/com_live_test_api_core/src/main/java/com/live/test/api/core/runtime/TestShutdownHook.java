package com.live.test.api.core.runtime;

/**
 * JVM关闭前如何做一些事？
 * Shutdown钩子，JVM关闭前触发。
 * @author live
 * @2020年5月19日 @下午3:21:47
 */
public class TestShutdownHook {

	public static void main(String[] args) {
		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("Shutdown钩子，JVM关闭前触发。");
			}
		};

		//添加钩子
		Runtime.getRuntime().addShutdownHook(t);
	}
}
