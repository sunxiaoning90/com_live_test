package com.live.test.api.core.thread;

/**
 * 关于interrupt(), isInterrupted()
 * interrupt()方法是设置一个标记，标记未中断线程，然后isInterrupted()方法是获取这个标记位，但是这里为什么多次执行，有时在调用了interrupt()方法后 ，isInterrupted()居然是返回false的？？
 *  因为thread线程可能已经挂掉了，如果还没有挂掉，最后会打印true，如果已经挂掉了，就会打印false。
 * @author live
 * @2019年12月31日 @下午18:53:01
 */
public class TestInterrupt {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			while (true) {
				System.out.println("run...");
				if (Thread.currentThread().isInterrupted())
					break;
			}
		});
		System.out.println("thread getState:" + thread.getState());
		thread.start();
		System.out.println("thread getState:" + thread.getState());

		Thread.sleep(1);
		thread.interrupt();
		System.out.println("thread getState:" + thread.getState());
		System.out.println("interrupt");
		Thread.sleep(1000 * 2);
		System.out.println("thread getState:" + thread.getState());
		System.out.println("isInterrupted:" + thread.isInterrupted());
	}
	/**
	 * 打印结果:
	 * thread getState:NEW
	thread getState:RUNNABLE
	run...
	run...
	//...
	thread getState:RUNNABLE
	interrupt
	thread getState:TERMINATED
	isInterrupted:false
	 */

}
