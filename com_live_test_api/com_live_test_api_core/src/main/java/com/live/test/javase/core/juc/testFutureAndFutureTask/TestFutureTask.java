package com.live.test.javase.core.juc.testFutureAndFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class TestFutureTask {

	/**
	 * future demo
	 */
	private static void testFutureTask() {
		Callable<String> r = new Callable<String>() {// 1）准备 callable
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				System.out.println("run...");
				return "ok";
			}
		};

		FutureTask<String> f = new FutureTask<String>(r);
		try {
			String result = f.get();// 3)阻塞等待结果
			System.out.println(result);
		} catch (Exception egnore) {
		}

		FutureTask<String> f2 = new FutureTask<String>(r);
		try {
			String result2 = f2.get(1, TimeUnit.SECONDS); // 3)阻塞等待结果，可以指定最长等待时长
			System.out.println(result2);
		} catch (Exception egnore) {
		}
	}

	public static void main(String[] args) {
		testFutureTask();
	}
}
