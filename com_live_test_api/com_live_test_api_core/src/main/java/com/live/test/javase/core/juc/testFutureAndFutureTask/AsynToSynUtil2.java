package com.live.test.javase.core.juc.testFutureAndFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class AsynToSynUtil2 {
	private static final int TIMEOUT_DEFAULT = 5000;

	public static Object asynToSyn(Callable<Object> callable, int timeout) {
		if(timeout <0) {
			timeout = TIMEOUT_DEFAULT;
		}
		FutureTask<Object> ft = new FutureTask<Object>(callable);
		try {
			//ft.run();
			ft.run();
			Object r = ft.get(timeout, TimeUnit.MILLISECONDS); // 3)阻塞等待结果，可以指定最长等待时长
			System.out.println(r);
			return r;
		} catch (Exception egnore) {
		}
		return null;
	}
	
	private static void asynToSyn_bak() {
		Callable<String> r = new Callable<String>() {// 1）准备 callable
			@Override
			public String call() throws Exception {
				System.out.println("run...");
				Thread.sleep(2000);
				System.out.println("runEnd...");
				return "ok";
			}
		};

		FutureTask<String> f = new FutureTask<String>(r);
		try {
			f.run();
			String result = f.get();// 3)阻塞等待结果
			System.out.println(result);
		} catch (Exception egnore) {
		}

		FutureTask<String> f2 = new FutureTask<String>(r);
		try {
			String result2 = f2.get(TIMEOUT_DEFAULT, TimeUnit.MILLISECONDS); // 3)阻塞等待结果，可以指定最长等待时长
			System.out.println(result2);
		} catch (Exception egnore) {
		}
	}

	public static void main(String[] args) {
		
		Callable<Object> callable = new Callable<Object>() {// 1）准备 callable
			@Override
			public String call() throws Exception {
				
				System.out.println("run...");
				for (int i = 0; i < 10; i++) {
					 System.out.println("轮询获取结果");
					 Thread.sleep(500);
					 if(false) {
						 return "ok";
					 }
				}
				//System.out.println("runEnd...");
				return "ok";
			}
		};
		
		long start = System.currentTimeMillis();
		System.out.println("start：" + start);
		Object r = asynToSyn(callable,2);
		System.out.println(r);
		long end = System.currentTimeMillis();
		System.out.println("end：" +end);
		
		System.out.println("耗时：" + (end -start));
	}
}
