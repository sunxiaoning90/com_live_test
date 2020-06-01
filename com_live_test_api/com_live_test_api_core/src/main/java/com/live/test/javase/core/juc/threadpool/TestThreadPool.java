package com.live.test.javase.core.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class TestThreadPool {

	/**
	 * 1、线程池有几种创建方式？<br>
	 * 方式1：通过 juc 的 ThreadPoolExecutor <br>
	 * 方式2：通过 juc 的 Executors <br>
	 */
	private void createThreadPool() {
//		方式1：通过 juc 的 ThreadPoolExecutor
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 9, 1, TimeUnit.MICROSECONDS,
				new LinkedBlockingQueue<Runnable>());

//		方式2：通过 juc 的 Executors
		ExecutorService fixedPool = Executors.newFixedThreadPool(2);
	}

	/**
	 * 2、juc 的 Executors,提供了哪些线程池？<br>
	 * 1）newFixedThreadPool 2）newCachedThreadPool 3）newSingleThreadExecutor
	 * 4）newScheduledThreadPool
	 */
	private void testExecutorService() {
		// 1）newFixedThreadPool
		ExecutorService fixedPool = Executors.newFixedThreadPool(2);

		// 2）newCachedThreadPool
		ExecutorService cachedPool = Executors.newCachedThreadPool();

		// 3）newSingleThreadExecutor
		ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

		// 4）newScheduledThreadPool
		ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
	}

	/**
	 * 2、测试线程池拒绝策略
	 */
	private void testRejectedExecutionHandler(ExecutorService pool) {
		for (int i = 0;; i++) {
			System.out.println(i);
			pool.submit(() -> {
				System.out.println("run	" + Thread.currentThread().getId());
		});
		}
	}

	/**
	 * RejectedExecutionHandler
	 * 		 CallerRunsPolicy
	 * AbortPolicy
	 * DiscardOldestPolicy
	 * DiscardPolicy
	 */
	
	
	/**
	 * 1、AbortPolicy （默认策略）
	 * private static final RejectedExecutionHandler defaultHandler = new AbortPolicy();
	 */
	
	/**
	 * 1、  CallerRunsPolicy
	 */
	
	/**
	 * 1、DiscardOldestPolicy
	 */
	
	/**
	 * 1、DiscardPolicy
	 */
	
	
	public static void main(String[] args) {

		ExecutorService fixedPool = Executors.newFixedThreadPool(2); //AbortPolicy ，源码：private static final RejectedExecutionHandler defaultHandler = new AbortPolicy();
		ExecutorService cachedPool = Executors.newCachedThreadPool();
		ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
		ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);
		
		TestThreadPool test = new TestThreadPool();
		test.testRejectedExecutionHandler(fixedPool);
//		
	}
}
