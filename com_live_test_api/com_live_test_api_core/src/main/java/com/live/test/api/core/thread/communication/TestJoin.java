package com.live.test.api.core.thread.communication;

/**
 * join: 当前正在执行的线程暂停执行,邀请A线程先执行，当前线程暂停执行;等待A线程执行完后，当前线程继续执行
 * 必须首先start，才能join，只有启动了，才能对线程进行操作。
 * @author live
 * @2020年5月19日 @下午7:08:42
 */
public class TestJoin {

	public static void main(String[] args) {
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
}
