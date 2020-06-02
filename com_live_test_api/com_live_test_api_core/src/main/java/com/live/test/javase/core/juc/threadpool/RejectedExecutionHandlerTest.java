package com.live.test.javase.core.juc.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池拒绝策略
 * 
 * 1、什么情况下拒绝？
 * 当缓冲池满了。
 * 
 * 2、线程池4种拒绝策略  AbortPolicy、DiscardPolicy、DiscardOldestPolicy、CallerRunsPolicy
   RejectedExecutionHandler rejected1Abort = new ThreadPoolExecutor.AbortPolicy(); //中止 ，报异常
	RejectedExecutionHandler rejected2Discard = new ThreadPoolExecutor.DiscardPolicy(); //丢弃，不报异常
	RejectedExecutionHandler rejected3DiscardOldest = new ThreadPoolExecutor.DiscardOldestPolicy(); //丢弃 Oledest，不报异常
	RejectedExecutionHandler rejected4CallerRuns = new ThreadPoolExecutor.CallerRunsPolicy(); // 直接运行run，不报异常
 * 
 * @author live
 *
 */
public class RejectedExecutionHandlerTest {

//	static volatile int i = 0;
	static volatile AtomicInteger count = new AtomicInteger(0);
	
	public static void doTestRejectedExecutionHandler(ThreadPoolExecutor pool) {

		for (int i = 1; i <= 10; i++) {

			// 记录任务编号
			final int taskCode = i;

			Runnable r = new Runnable() {
				@Override
				public void run() {
//					i++
					int c = count.incrementAndGet();

					try {
						Thread.sleep(1000 * 3);//模拟耗时3秒
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("-----线程执行结束：" + Thread.currentThread().getName() + ",	总执行次数：" + c
							+ ",	任务编号：" + taskCode);
				}
			};

			pool.execute(r);
			System.out.println("尝试每秒放一个任务，任务编号：" + taskCode);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void testRejectedExecutionHandler() {
		RejectedExecutionHandler rejected1Abort = new ThreadPoolExecutor.AbortPolicy(); //中止
		RejectedExecutionHandler rejected2Discard = new ThreadPoolExecutor.DiscardPolicy(); //丢弃，不报异常
		RejectedExecutionHandler rejected3DiscardOldest = new ThreadPoolExecutor.DiscardOldestPolicy(); //丢弃 Oledest，不报异常
		RejectedExecutionHandler rejected4CallerRuns = new ThreadPoolExecutor.CallerRunsPolicy(); // 直接运行run，不报异常
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 6, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(1), rejected1Abort);
		doTestRejectedExecutionHandler(pool);
		
		try {
			Thread.sleep(1000 * 30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}
	
	public static void main(String[] args) {
		testRejectedExecutionHandler();
	}
	
	/**
	 * RejectedExecutionHandler rejected1Abort = new ThreadPoolExecutor.AbortPolicy(); //中止,报异常
	 * 
	 * 尝试每秒放一个任务，任务编号：1
尝试每秒放一个任务，任务编号：2
尝试每秒放一个任务，任务编号：3
-----线程执行结束：pool-1-thread-1,	总执行次数：1,	任务编号：1
尝试每秒放一个任务，任务编号：4
-----线程执行结束：pool-1-thread-2,	总执行次数：2,	任务编号：2
尝试每秒放一个任务，任务编号：5
-----线程执行结束：pool-1-thread-1,	总执行次数：3,	任务编号：3
-----线程执行结束：pool-1-thread-2,	总执行次数：4,	任务编号：4
-----线程执行结束：pool-1-thread-1,	总执行次数：5,	任务编号：5
Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task core.chat.user.ChatUser$1@7921b0a2 rejected from java.util.concurrent.ThreadPoolExecutor@174d20a[Running, pool size = 2, active threads = 2, queued tasks = 1, completed tasks = 2]
	 */
	
/**
 * RejectedExecutionHandler rejected2Discard = new ThreadPoolExecutor.DiscardPolicy(); //丢弃，不报异常
 * 
 * 丢弃了编号4的任务
 * 
 * 尝试每秒放一个任务，任务编号：1
尝试每秒放一个任务，任务编号：2
尝试每秒放一个任务，任务编号：3
-----线程执行结束：pool-1-thread-1,	总执行次数：1,	任务编号：1
尝试每秒放一个任务，任务编号：4
-----线程执行结束：pool-1-thread-2,	总执行次数：2,	任务编号：2
尝试每秒放一个任务，任务编号：5
尝试每秒放一个任务，任务编号：6
-----线程执行结束：pool-1-thread-1,	总执行次数：3,	任务编号：3
尝试每秒放一个任务，任务编号：7
-----线程执行结束：pool-1-thread-2,	总执行次数：4,	任务编号：5
尝试每秒放一个任务，任务编号：8
尝试每秒放一个任务，任务编号：9
-----线程执行结束：pool-1-thread-1,	总执行次数：5,	任务编号：6
尝试每秒放一个任务，任务编号：10
-----线程执行结束：pool-1-thread-2,	总执行次数：6,	任务编号：7
-----线程执行结束：pool-1-thread-1,	总执行次数：7,	任务编号：8
-----线程执行结束：pool-1-thread-2,	总执行次数：8,	任务编号：10

 */
	/**
	 * RejectedExecutionHandler rejected3DiscardOldest = new ThreadPoolExecutor.DiscardOldestPolicy(); //丢弃 Oledest
	 * 丢弃了编号4的任务
	 * 
	 * 尝试每秒放一个任务，任务编号：1
尝试每秒放一个任务，任务编号：2
尝试每秒放一个任务，任务编号：3
-----线程执行结束：pool-1-thread-1,	总执行次数：1,	任务编号：1
尝试每秒放一个任务，任务编号：4
尝试每秒放一个任务，任务编号：5
-----线程执行结束：pool-1-thread-2,	总执行次数：2,	任务编号：2
尝试每秒放一个任务，任务编号：6
-----线程执行结束：pool-1-thread-1,	总执行次数：3,	任务编号：3
尝试每秒放一个任务，任务编号：7
-----线程执行结束：pool-1-thread-2,	总执行次数：4,	任务编号：5
尝试每秒放一个任务，任务编号：8
尝试每秒放一个任务，任务编号：9
-----线程执行结束：pool-1-thread-1,	总执行次数：5,	任务编号：6
尝试每秒放一个任务，任务编号：10
-----线程执行结束：pool-1-thread-2,	总执行次数：6,	任务编号：7
-----线程执行结束：pool-1-thread-1,	总执行次数：7,	任务编号：9
-----线程执行结束：pool-1-thread-2,	总执行次数：8,	任务编号：10
	 */
	
	
	/**
	 * RejectedExecutionHandler rejected4CallerRuns = new ThreadPoolExecutor.CallerRunsPolicy(); // 直接运行run
	 * 
	 * 尝试每秒放一个任务，任务编号：1
尝试每秒放一个任务，任务编号：2
尝试每秒放一个任务，任务编号：3
-----线程执行结束：pool-1-thread-1,	总执行次数：1,	任务编号：1
尝试每秒放一个任务，任务编号：4
-----线程执行结束：pool-1-thread-2,	总执行次数：2,	任务编号：2
尝试每秒放一个任务，任务编号：5
-----线程执行结束：pool-1-thread-1,	总执行次数：3,	任务编号：3
-----线程执行结束：pool-1-thread-2,	总执行次数：4,	任务编号：4
-----线程执行结束：main,	总执行次数：5,	任务编号：6
尝试每秒放一个任务，任务编号：6
-----线程执行结束：pool-1-thread-1,	总执行次数：6,	任务编号：5
	 */
	
}
