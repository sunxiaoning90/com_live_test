package com.live.test.javase.core.thread;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadUtil {

	/**
	 * 最优的线程数算法之一 ：cpu核心数 * 2
	 * @return
	 */
	public static int getOpimalByAvailableProcessors() {
		return Runtime.getRuntime().availableProcessors() * 2;
	}

	/**
	 * 异常时，阻止打印堆栈
	 */
	public static void forbidSystemOutErrorException() {

		UncaughtExceptionHandler eh = new UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("阻止了打印堆栈：线程异常：t:" + t.getName() + ",e:" + e.getMessage());
			}
		};

		Thread.setDefaultUncaughtExceptionHandler(eh);
	}

	/**
	 * 恢复
	 */
	public static void allowSystemOutErrorException() {
		// Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(null);
	}

	/**
	 * 测试异常时，阻止打印堆栈 与 允许打印堆栈
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		forbidSystemOutErrorException();

		Thread t = new Thread() {
			@Override
			public void run() {
				super.run();
				System.out.println("run...");
				// throw new RuntimeException("t线程异常");
				try {
					throw new Exception("t线程异常");
				} catch (Exception e) {
				}
			}
		};

		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		allowSystemOutErrorException();

		Thread t2 = new Thread() {
			@Override
			public void run() {
				super.run();
				System.out.println("run2...");
				throw new RuntimeException("t2线程异常");
			}
		};

		t2.start();
	}
}
