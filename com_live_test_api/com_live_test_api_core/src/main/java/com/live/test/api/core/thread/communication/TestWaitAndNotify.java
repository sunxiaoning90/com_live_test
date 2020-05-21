package com.live.test.api.core.thread.communication;

/**
 * wait()、notify()方法属于Object中的方法；对于Object中的方法，每个对象都拥有。

wait()方法：该方法用来使得当前线程进入等待状态，直到接到通知或者被中断打断为止。在调用wait()方法之前，线程必须要获得该对象的对象级锁；换句话说就是该方法只能在同步方法或者同步块中调用，如果没有持有合适的锁的话，线程将会抛出异常IllegalArgumentException。调用wait()方法之后，当前线程则释放锁。
notify()方法：该方法用来唤醒处于等待状态获取对象锁的其他线程。如果有多个线程则线程规划器任意选出一个线程进行唤醒，使其去竞争获取对象锁，但线程并不会马上就释放该对象锁，wait()所在的线程也不能马上获取该对象锁，要程序退出同步块或者同步方法之后，当前线程才会释放锁，wait()所在的线程才可以获取该对象锁。

wait()方法是释放锁的；notify()方法不释放锁，必须等到所在线程把代码执行完。
 * @author live
 * @2020年5月21日 @下午6:13:40
 */
public class TestWaitAndNotify {

	static boolean stop;
	
	
	public static void main(String[] args) {
		
		Object obj = new Object();
		
		Thread t1 = new Thread(() -> {
			System.out.println("start1...");
			while (true) {
				try {
					synchronized (obj) {
						obj.wait();
					}
					
					System.out.println(1);
					
					synchronized (obj) {
						obj.notify();
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			});
		
		t1.start();
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("5结束");
		
		while (true) {
			try {
				synchronized (obj) {
					obj.notify();
				}
				
				System.out.println(2);
				
				synchronized (obj) {
					obj.wait();
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		//System.out.println("jvm quit!");
	}
}
