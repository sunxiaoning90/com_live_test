package com.live.test.javase.core.juc.threadpool;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class ThreadPoolExecutorTest {

	/**
	 * 一、线程池简介
	 * 1、线程池是什么
	 * 2、线程池优点
	 * 3、线程池实现原理
	 * 4、java中有几种创建线程池的方式
	 * 
	 */
	
	/**
	 * 4、java中有几种创建线程池的方式<br>
	 * 方式1：通过 juc 的 ThreadPoolExecutor <br>
	 * 方式2：通过 juc 的 Executors : 1）Executors.newFixedThreadPool 2）Executors.newCachedThreadPool 3）Executors.newSingleThreadExecutor 4）Executors.newScheduledThreadPool<br>
	 */
	private void createThreadPool() {
//		方式1：通过 juc 的 ThreadPoolExecutor
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 9, 1, TimeUnit.MICROSECONDS,
				new LinkedBlockingQueue<Runnable>());

//		方式2：通过 juc 的 Executors
		ExecutorService fixedPool = Executors.newFixedThreadPool(2);
	}

	/**
	 * 二、ThreadPoolExecutor
	 */
	
	/**
	 * 1、ThreadPoolExecutor 简单使用
	 */
	private void testThreadPoolExecutor() {
		//1)指定：corePoolSize,maximumPoolSize, keepAliveTime, TimeUnit, BlockingQueue<Runnable>
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 18, 6, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>());
		
		ThreadFactory threadFactory = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				return t;
			}
		};
		// 2)指定：线程工厂：threadFactory
		ThreadPoolExecutor pool2 = new ThreadPoolExecutor(2, 18, 6, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
		
		RejectedExecutionHandler rejected =new AbortPolicy();
		// 3）指定拒绝策略：RejectedExecutionHandler
		ThreadPoolExecutor pool3 = new ThreadPoolExecutor(2, 18, 6, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(10), rejected);
		// 4)指定：线程工厂：threadFactory 和 拒绝策略：RejectedExecutionHandler
		ThreadPoolExecutor pool4 = new ThreadPoolExecutor(2, 18, 6, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(10), threadFactory,rejected);
	}
	
	/**
	 * 2、ThreadPoolExecutor 参数详解
	 * int corePoolSize, 核心线程数
	 * int maximumPoolSize, 最大线程数
	 * long keepAliveTime, TimeUnit unit, 存活时间
	 * BlockingQueue<Runnable> workQueue, 队列
	 * ThreadFactory threadFactory, 线程工厂
	 * RejectedExecutionHandler handler 拒绝策略
	 */
	
	/**
	 * 3、线程工厂
	 */
	private void testThreadFactory() {
		//准备 ThreadFactory
		ThreadFactory threadFactory = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				//1、生产一个线程对象
				//2、如果有必要，可以记录这个对象，方便统计等
				Thread t = new Thread(r);
				return t;
			}
		};
		
		// 指定：线程工厂：threadFactory
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 18, 6, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
	}
	
	/**
	 * 4、线程池拒绝策略
	 */
	private void testRejectedExecutionHandler() {
		RejectedExecutionHandler rejected1 = new ThreadPoolExecutor.AbortPolicy(); //中止
		RejectedExecutionHandler rejected2 = new ThreadPoolExecutor.DiscardPolicy(); //丢弃
		RejectedExecutionHandler rejected3 = new ThreadPoolExecutor.DiscardOldestPolicy(); //丢弃 Oledest
		RejectedExecutionHandler rejected4 = new ThreadPoolExecutor.CallerRunsPolicy(); // 直接运行run
	}
	
	/**
	 * 三、Executors
	 * 1、Executors 是什么
	 */
	
	/**
	 * Executors 是什么？
	 * 1、读JDK源码：
	 java.util.concurrent.Executors

	Factory and utility methods for Executor, ExecutorService, ScheduledExecutorService, ThreadFactory, and Callable classes defined in this package. 
	This class supports the following kinds of methods:
		Methods that create and return an ExecutorService set up with commonly useful configuration settings.
		Methods that create and return a ScheduledExecutorService set up with commonly useful configuration settings.
		Methods that create and return a "wrapped" ExecutorService, that disables reconfiguration by making implementation-specific methods inaccessible.
		Methods that create and return a ThreadFactory that sets newly created threads to a known state.
		Methods that create and return a Callable out of other closure-like forms, so they can be used in execution methods requiring Callable.
	 */
	
	/**
	 * 2、juc 的 Executors,提供了哪些线程池？
	 * 1）Executors.newFixedThreadPool
	 * 2）Executors.newCachedThreadPool
	 * 3）Executors.newSingleThreadExecutor
	 * 4）Executors.newScheduledThreadPool
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
		
		ThreadPoolExecutorTest test = new ThreadPoolExecutorTest();
		test.testRejectedExecutionHandler(fixedPool);
//		
	}
}
