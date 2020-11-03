package com.live.test.javase.core.juc.testFutureAndFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class AsynToSynUtil {
	private static final int WITETIME_MAX = 5000;

	/**
	 * future demo
	 */
	private static Object asynToSyn(Callable<Object> callable) {
		FutureTask<Object> ft = new FutureTask<Object>(callable);
		try {
			ft.run();
			Object r = ft.get(WITETIME_MAX, TimeUnit.MILLISECONDS); // 3)阻塞等待结果，可以指定最长等待时长
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
			String result2 = f2.get(WITETIME_MAX, TimeUnit.MILLISECONDS); // 3)阻塞等待结果，可以指定最长等待时长
			System.out.println(result2);
		} catch (Exception egnore) {
		}
	}

	public static void main(String[] args) {
		
		Callable<Object> callable = new Callable<Object>() {// 1）准备 callable
			@Override
			public String call() throws Exception {
				System.out.println("run...");
				Thread.sleep(2000);
				System.out.println("runEnd...");
				return "ok";
			}
		};
		
		System.out.println("start," + System.currentTimeMillis());
		Object r = asynToSyn(callable);
		System.out.println(r);
		System.out.println("end," + System.currentTimeMillis());
	}
}
