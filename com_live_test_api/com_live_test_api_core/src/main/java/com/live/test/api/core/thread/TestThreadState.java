package com.live.test.api.core.thread;

import java.lang.Thread.State;

/**
 * 线程5种状态，
 * 查看JDK注释
 * @author live
 * @2020年5月20日 @上午10:36:08
 * 
 */

public class TestThreadState {
	
	
/*	查阅JDK注释，Thread共有6种状态：
 * 		1）NEW		//新建状态
 * 		2）RUNNABLE	//就绪状态+可运行状态
 * 		3）BLOCKED	//阻塞状态
 * 		4）WAITING	//等待状态（无期限等待）
 * 		5）TIMED_WAITING	//等待状态（指定期限等待）
 * 		6）TERMINATED	//终止状态
 * 
 * 	JDK中java.lang.Thread.State的注释：
	   A thread state. A thread can be in one of the following states:
		//一个线程的状态。线程可以处于以下状态之一:
		
		NEW		//新建状态
		A thread that has not yet started is in this state.
		//尚未启动的线程 处于这种状态。
		
		RUNNABLE	//就绪状态+可运行状态
		A thread executing in the Java virtual machine is in this state.
		//在Java虚拟机中执行的线程 处于这种状态。
		
		BLOCKED	//阻塞状态
		A thread that is blocked waiting for a monitor lock is in this state.
		//在等待监视器锁时被阻塞的线程 处于这种状态。
		
		WAITING	//等待状态（无期限等待）
		A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
		//无限期等待另一个线程执行特定操作的线程 处于这种状态。
		
		TIMED_WAITING	//等待状态（指定期限等待）
		A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
		//在指定等待时间内等待另一个线程执行某个操作的线程 处于这种状态。
		
		TERMINATED	//终止状态
		A thread that has exited is in this state.
		//已退出的线程 处于这种状态。
		
		A thread can be in only one state at a given point in time. These states are virtual machine states which do not reflect any operating system thread states.
		//在给定的时间点上，线程只能处于一种状态。这些状态是虚拟机状态，不反映任何操作系统线程状态。
*/
	
	/**
	 * 2、
	 */
	private void seeJDKDocOfState() {
		/**
		 * java.lang.Thread.State.NEW

			Thread state for a thread which has not yet started.
			//尚未启动的线程的线程状态。
		 */		 
		State s = State.NEW;
				
		/**
		 * java.lang.Thread.State.RUNNABLE

			Thread state for a runnable thread. A thread in the runnable state is executing in the Java virtual machine but it may be waiting for other resources from the operating system such as processor.
			//可运行线程的线程状态。处于可运行状态的线程在Java虚拟机中执行，但它可能在等待来自操作系统(如处理器)的其他资源。
		 */
		 s = State.RUNNABLE;
		 
		 /**
		  * java.lang.Thread.State.WAITING

			Thread state for a waiting thread. A thread is in the waiting state due to calling one of the following methods:
			//等待线程的线程状态。由于调用以下方法之一，线程处于等待状态:
			 * 
			Object.wait with no timeout
			Thread.join with no timeout
			LockSupport.park
			
			A thread in the waiting state is waiting for another thread to perform a particular action. For example, a thread that has called Object.wait() on an object is waiting for another thread to call Object.notify() or Object.notifyAll() on that object. A thread that has called Thread.join() is waiting for a specified thread to terminate.
		  	//处于等待状态的线程正在等待另一个线程执行特定的操作。例如，
		  	 * 一个在对象上调用object. wait()的线程正在等待另一个线程在该对象上调用object. notify()或object. notifyall()。
		  	 * 调用thread .join()的线程正在等待指定的线程终止。
		  */
		 s=State.WAITING; 
		 
		 /**
		  * java.lang.Thread.State.TIMED_WAITING

				Thread state for a waiting thread with a specified waiting time. A thread is in the timed waiting state due to calling one of the following methods with a specified positive waiting time:
				//具有指定等待时间的等待线程的线程状态。线程处于定时等待状态，这是由于调用以下具有指定正等待时间的方法之一:
				
				Thread.sleep
				Object.wait with timeout
				Thread.join with timeout
				LockSupport.parkNanos
				LockSupport.parkUntil
		  */
		 s= State.TIMED_WAITING;
		 
		 
		 /**
		  *java.lang.Thread.State.TERMINATED

			Thread state for a terminated thread. The thread has completed execution.
//			终止线程的线程状态。线程已经完成执行。
		  */
		 s=State.TERMINATED;
	}
	
	/**
	 * NEW RUNNABLE run... TIMED_WAITING RUNNABLE TERMINATED
	 */
	public static void main(String[] args) {
//		Thread t = new Thread(() -> System.err.println("run"));

		Thread t = new Thread(() -> {
			System.err.println("run...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(t.getState());

		t.start();

		State state = t.getState();
		System.out.println(state);

		long start = System.currentTimeMillis();
		long end;
		for (;;) {
			State s = t.getState();
			if (!s.equals(state)) {
				state = s;
				System.out.println(state);
			}

			end = System.currentTimeMillis();
			if (end - start > 10 * 1000) {
				break;
			}
		}

	}
	
	
}
