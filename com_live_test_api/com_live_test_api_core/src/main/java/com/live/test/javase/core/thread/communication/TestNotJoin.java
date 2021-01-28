package com.live.test.javase.core.thread.communication;

public class TestNotJoin {
	private void m1() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + "-> m1.");
			this.m2();
		}
	}

	private void m2() {
		synchronized (this) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "-> m2.");
		}
	}

	/**
	 * 随机一种结果： t1-> m1. t1-> m2. t2-> m1. t2-> m2. t3-> m2.
	 */
	public static void main(String[] args) {
		TestNotJoin o = new TestNotJoin();

		Thread t1 = new Thread(() -> {
			o.m1();
		}, "t1");

		Thread t2 = new Thread(() -> {
			o.m1();
		}, "t2");

		Thread t3 = new Thread(() -> {
			o.m2();
		}, "t3");

		t1.start();
		t2.start();
		t3.start();
	}
}
