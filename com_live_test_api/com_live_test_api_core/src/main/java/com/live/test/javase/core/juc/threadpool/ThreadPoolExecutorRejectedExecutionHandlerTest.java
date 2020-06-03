package com.live.test.javase.core.juc.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池拒绝策略

	<pre>
	1、什么情况下拒绝？
 		当缓冲池满了。
	</pre>
	
 	<pre>
 	2、线程池4种拒绝策略简单使用
	   AbortPolicy、DiscardPolicy、DiscardOldestPolicy、CallerRunsPolicy
	   
	   RejectedExecutionHandler rejected1Abort = new ThreadPoolExecutor.AbortPolicy(); //中止 ，报异常
		RejectedExecutionHandler rejected2Discard = new ThreadPoolExecutor.DiscardPolicy(); //丢弃，不报异常
		RejectedExecutionHandler rejected3DiscardOldest = new ThreadPoolExecutor.DiscardOldestPolicy(); //丢弃 Oledest，不报异常
		RejectedExecutionHandler rejected4CallerRuns = new ThreadPoolExecutor.CallerRunsPolicy(); // 直接运行run，不报异常
	</pre>
	
	<pre>
	3、线程池4种拒绝策略 详解
		1）ThreadPoolExecutor.AbortPolicy: //中止 策略
		 
		A handler for rejected tasks that throws a RejectedExecutionException.
		//一个处理被拒绝任务的处理程序，抛出RejectedExecutionException异常。
		
		2）ThreadPoolExecutor.DiscardPolicy: //丢弃 策略
		A handler for rejected tasks that silently discards the rejected task.
		//一个处理被拒绝任务的处理程序，它无声地丢弃被拒绝的任务。
		
		3）ThreadPoolExecutor.DiscardOldestPolicy: //丢弃最老的 策略
		A handler for rejected tasks that discards the oldest unhandled request and then retries execute, unless the executor is shut down, in which case the task is discarded.
		//一个处理被拒绝任务的处理程序，它丢弃最旧的未处理请求，然后重试执行，除非执行程序关闭，在这种情况下任务被丢弃。
		
		4）ThreadPoolExecutor.CallerRunsPolicy: //直接执行策略 直接调用任务的Run方法
		A handler for rejected tasks that runs the rejected task directly in the calling thread of the execute method, unless the executor has been shut down, in which case the task is discarded.
		//一个处理被拒绝任务的处理程序，它直接在execute方法的调用线程中运行拒绝任务，除非executor已经关闭，在这种情况下任务被丢弃。
	</pre>
 */

public class ThreadPoolExecutorRejectedExecutionHandlerTest {

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
