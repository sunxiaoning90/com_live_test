package com.live.test.javase.core.juc.threadpool.util;

public class ThreadPoolUtil {

	/**
	 * CPU 密集型
	 * @return
	 */
	public static int bestThreadCountForCPU() {
		return Runtime.getRuntime().availableProcessors() * 2;
	}

	/**
	 * IO 密集型
	 * @return
	 */
	public static int bestThreadCountForIO() {
		return Runtime.getRuntime().availableProcessors() + 1;
	}
}
