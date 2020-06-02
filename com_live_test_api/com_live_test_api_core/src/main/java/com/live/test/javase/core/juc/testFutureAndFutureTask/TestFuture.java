package com.live.test.javase.core.juc.testFutureAndFutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestFuture {

	/**
	 * future demo
	 */
	private static void testFuture() {
		ExecutorService pool = Executors.newSingleThreadExecutor();

		Callable<String> r = new Callable<String>() {// 1）准备 callable
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				System.out.println("run...");
				return "ok";
			}
		};

		Future<String> future = pool.submit(r);// 2）调用线程池的submit方法，获取future
		try {
			String result = future.get();// 3)阻塞等待结果
			System.out.println(result);
		} catch (Exception egnore) {
		}

		Future<String> future2 = pool.submit(r);
		try {
			String result2 = future2.get(1, TimeUnit.SECONDS); // 3)阻塞等待结果，可以指定最长等待时长
			System.out.println(result2);
		} catch (Exception egnore) {
		}
	}

	/**
	 * 如何利用 Future 优雅地取消一个任务的执行？
	 */
	public static void testClose() {
		ExecutorService pool = Executors.newSingleThreadExecutor();

		Callable<String> r = new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				System.out.println("run...");
				return "ok";
			}
		};

		Future<String> future = pool.submit(r);
		try {
			String result = future.get(3, TimeUnit.SECONDS);
			System.out.println(result);
		} catch (InterruptedException e) {
			// Thread 恢复中断状态
			Thread.currentThread().interrupt();
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		} catch (TimeoutException e) {
			// 执行超时，适当地关闭
			Thread.currentThread().interrupt(); // 设置中断状态
			future.cancel(true); // 尝试取消
		}
	}

	public static void main(String[] args) {
		testClose();
	}
}
