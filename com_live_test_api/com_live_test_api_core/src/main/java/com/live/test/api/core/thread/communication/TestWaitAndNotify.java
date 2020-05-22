package com.live.test.api.core.thread.communication;

/**
 * wait()、notify()方法属于Object中的方法。
 * 
 * wait()方法：该方法用来使得当前线程进入等待状态，直到接到通知或者被中断打断为止。在调用wait()方法之前，线程必须要获得该对象的对象级锁；换句话说就是该方法只能在同步方法或者同步块中调用，如果没有持有合适的锁的话，线程将会抛出异常IllegalArgumentException。调用wait()方法之后，当前线程则释放锁。
 * notify()方法：该方法用来唤醒处于等待状态获取对象锁的其他线程。如果有多个线程则线程规划器任意选出一个线程进行唤醒，使其去竞争获取对象锁，但线程并不会马上就释放该对象锁，wait()所在的线程也不能马上获取该对象锁，要程序退出同步块或者同步方法之后，当前线程才会释放锁，wait()所在的线程才可以获取该对象锁。
 * 
 * wait()方法是释放锁的； notify()方法不释放锁，必须等到所在线程把代码执行完。
 * 
 * @author live
 * @2020年5月21日 @下午6:13:40
 */
public class TestWaitAndNotify extends Thread {

	public static void main(String[] args) {

		Object lock = new Object();

		Thread t1 = new Thread(() -> {

			for (;;) {
				System.err.println(1);
				synchronized (lock) {
					System.err.println(2);
					lock.notify();// 1）获取锁。2)唤醒正在此对象监视器上等待的单个线程。
					System.err.println(3);
					System.out.println(Thread.currentThread().getName() + ":" + 1);
					try {
						lock.wait();// 1)导致当前线程等待，直到另一个线程调用此对象的notify()方法或notifyAll()方法。2)释放锁。
					} catch (Exception e) {
						e.getLocalizedMessage();
					}
					System.err.println(4);
				}

				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "t1");

		Thread t2 = new Thread(() -> {

			for (;;) {
				System.err.println(11);
				synchronized (lock) {
					System.err.println(22);
					lock.notify();
					System.err.println(33);
					System.out.println("\t" + Thread.currentThread().getName() + ":" + 2);
					try {
						lock.wait();
					} catch (Exception e) {
						e.getLocalizedMessage();
					}
					System.err.println(44);
				}

				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "t2");

//		t1.setPriority(10);
//		t2.setPriority(1);

		t1.start();

		try {
			Thread.sleep(1 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t2.start();

		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("60s结束");

		//执行顺序 ：1 2 3,11 22 33, 4,1 2 3,44,11 22 33,4
	}
}
