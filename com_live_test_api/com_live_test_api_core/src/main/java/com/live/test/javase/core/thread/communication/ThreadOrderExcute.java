package com.live.test.javase.core.thread.communication;

/**
 * @author live
 * @2020年5月19日 @下午6:17:35
 * 
 *             * 线程顺序执行的几种方式：<br>
 *             1）join： t1.start(); t1.join();
 * 
 *             2）自旋判断线程活着 t.isAlive：<br>
 *             t1.start(); while (t1.isAlive()) { }
 * 
 *             3) 自旋判断线程活着 + wait（）：<br>
 *             t1.start(); while (t1.isAlive()) { synchronized (t1) { t1.wait();
 *             // 线程结束时，会自己调用notifyAll方法 } }
 */
public class ThreadOrderExcute {

	@SuppressWarnings("unused")
	private void testNoOrder() {
		Thread t1 = new Thread(() -> System.out.println("run1..."), "t1");
		Thread t2 = new Thread(() -> System.out.println("run2..."), "t2");
		Thread t3 = new Thread(() -> System.out.println("run2..."), "t3");

		t1.start();
		t2.start();
		t3.start();
	}

	@SuppressWarnings("unused")
	private void orderExcuteByJoin() {
		Thread t1 = new Thread(() -> System.out.println("run1..."), "t1");
		Thread t2 = new Thread(() -> System.out.println("run2..."), "t2");
		Thread t3 = new Thread(() -> System.out.println("run3..."), "t3");

		//
		t1.start();

		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//
		t2.start();

		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//
		t3.start();

		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void orderExcuteByIsAlive() {
		Thread t1 = new Thread(() -> System.out.println("run1..."), "t1");
		Thread t2 = new Thread(() -> System.out.println("run2..."), "t2");
		Thread t3 = new Thread(() -> System.out.println("run3..."), "t3");

		//
		t1.start();
		while (t1.isAlive()) {
//			System.out.println("检查t1,isAlive:"+t1.isAlive());
		}
		System.out.println("检查t1,isAlive:" + t1.isAlive());

		//
		t2.start();
		while (t2.isAlive()) {
//			System.out.println("检查t2,isAlive:"+t2.isAlive());
		}
		System.out.println("检查t2,isAlive:" + t2.isAlive());

		//
		t3.start();
		while (t3.isAlive()) {
//			System.out.println("检查t3,isAlive:"+t3.isAlive());
		}
		System.out.println("检查t3,isAlive:" + t3.isAlive());
	}

	private void orderExcuteByIsAliveAndWait() {
		Thread t1 = new Thread(() -> System.out.println("run1..."), "t1");
		Thread t2 = new Thread(() -> System.out.println("run2..."), "t2");
		Thread t3 = new Thread(() -> System.out.println("run3..."), "t3");

		t1.start();
		while (t1.isAlive()) {
			synchronized (t1) {
				System.out.println("1检查t1,isAlive:" + t1.isAlive());
				try {
					t1.wait(); // 线程结束时，会自己调用notifyAll方法
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("2检查t1,isAlive:" + t1.isAlive());
			}
		}
		System.out.println("检查t1,isAlive:" + t1.isAlive());

		t2.start();
		while (t2.isAlive()) {
			synchronized (t2) {
				System.out.println("1检查t2,isAlive:" + t2.isAlive());
				try {
					t2.wait(); // 线程结束时，会自己调用notifyAll方法
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("2检查t2,isAlive:" + t2.isAlive());
			}
		}
		System.out.println("检查t2,isAlive:" + t2.isAlive());

		t3.start();
		while (t3.isAlive()) {
			synchronized (t3) {
				System.out.println("1检查t3,isAlive:" + t3.isAlive());
				try {
					t3.wait(); // 线程结束时，会自己调用notifyAll方法
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("2检查t3,isAlive:" + t3.isAlive());
			}
		}
		System.out.println("检查t3,isAlive:" + t3.isAlive());
	}

	public static void main(String[] args) {

		ThreadOrderExcute t = new ThreadOrderExcute();

//		t.testNoOrder();
//		t.orderExcuteByJoin();
//		t.orderExcuteByIsAlive();
		t.orderExcuteByIsAliveAndWait();
	}
}
