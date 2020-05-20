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
	
	private void seeDoc() {
		/**
		 * java.lang.Thread.State
		 * A thread state. A thread can be in one of the following states:
			//一个线程的状态。线程可以处于以下状态之一:
			
			NEW	//就绪状态
			A thread that has not yet started is in this state.
			//尚未启动的线程处于这种状态。
			
			RUNNABLE
			A thread executing in the Java virtual machine is in this state.
			//在Java虚拟机中执行的线程处于这种状态。
			
			BLOCKED
			A thread that is blocked waiting for a monitor lock is in this state.
			//在等待监视器锁时被阻塞的线程处于这种状态。
			
			WAITING
			A thread that is waiting indefinitely for another thread to perform a particular action is in this state.
			//无限期等待另一个线程执行特定操作的线程处于这种状态。
			
			TIMED_WAITING
			A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
			//在指定等待时间内等待另一个线程执行某个操作的线程处于这种状态。
			 * 
			TERMINATED
			A thread that has exited is in this state.
			//已退出的线程处于这种状态。
			
			A thread can be in only one state at a given point in time. These states are virtual machine states which do not reflect any operating system thread states.
			//在给定的时间点上，线程只能处于一种状态。这些状态是虚拟机状态，不反映任何操作系统线程状态。
		 */
		
		/**
		 * java.lang.Thread.State.NEW

			Thread state for a thread which has not yet started.
		 */
		State s = State.NEW;
		
		/**
		 * java.lang.Thread.State.RUNNABLE

			Thread state for a runnable thread. A thread in the runnable state is executing in the Java virtual machine but it may be waiting for other resources from the operating system such as processor.
		 */
		 s = State.RUNNABLE;
		 
		 /**
		  * java.lang.Thread.State.WAITING

			Thread state for a waiting thread. A thread is in the waiting state due to calling one of the following methods:
			
			Object.wait with no timeout
			Thread.join with no timeout
			LockSupport.park
			A thread in the waiting state is waiting for another thread to perform a particular action. For example, a thread that has called Object.wait() on an object is waiting for another thread to call Object.notify() or Object.notifyAll() on that object. A thread that has called Thread.join() is waiting for a specified thread to terminate.
		  */
		 s=State.WAITING; 
		 
		 /**
		  * java.lang.Thread.State.TIMED_WAITING

				Thread state for a waiting thread with a specified waiting time. A thread is in the timed waiting state due to calling one of the following methods with a specified positive waiting time:
				
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
