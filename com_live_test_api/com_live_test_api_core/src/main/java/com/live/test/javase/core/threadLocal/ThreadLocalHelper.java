package com.live.test.javase.core.threadLocal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadLocalHelper {

	private static final ThreadLocal<Object> local = new ThreadLocal<Object>();

	public static void remove() {
		local.remove();
	}

	public static void set(Object v) {
		local.set(v);
	}

	public static Object get() {
		return local.get();
	}

	public static void main(String[] args) {
//		ThreadLocalHelper.remove();
//		ThreadLocalHelper.set(1);
//		Object v = ThreadLocalHelper.get();
//		System.out.println(v);

		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(100);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 10, 10, TimeUnit.SECONDS, workQueue);

		for (int i = 0; i < 10; i++) {
			Runnable task = TaskFactory.createTask(i + "");
			pool.submit(task);
		}

		pool.shutdown();
	}

}
