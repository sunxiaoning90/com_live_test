package com.live.test.javase.core.juc.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 线程池遇到异常，会自动处理，如何捕获异常？
 * 可以重写 ThreadPoolExecutor的 afterExecute方法
 * @author live
 * @2020年5月20日 @下午4:39:53
 */
public class TestThreadPoolException {
	
	public static void main(String[] args) {
//		ThreadPoolExecutor poll = new ThreadPoolExecutor(2, 4, 7, TimeUnit.SECONDS, new ArrayBlockingQueue(2));
		ThreadPoolExecutor poll = new ThreadPoolExecutor(2, 4, 7, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(2)) {

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
				System.out.println("线程池某个线程异常:" + r + ",t:" + t);
			}
		};

		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("run...");
//				throw new RuntimeException("");
				int a = 1 / 0;
				System.out.println(a);
				System.out.println("end...");
			}
		};

		poll.execute(r1);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		poll.shutdown();
	}
	
}
