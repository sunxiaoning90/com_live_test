package com.live.test.javase.core.synchronizedTest;

import java.util.HashMap;
import java.util.Map;

public class TestSynchronized {

	public synchronized void m01() {
		// ...
	}

	public void m02() {
		synchronized (this) {
			// ...
		}
	}

	Object lock = new Object();

	public void m03() {
		synchronized (lock) {
			// ...
		}
	}

	public void m04() {
		synchronized (Object.class) {
			// ...
		}
	}

	public static synchronized void m05() {
		// ...
	}

	public static void m06() {
		synchronized (Object.class) {
			// ...
		}
	}

	/**
	 * ************************* map2 = map1 ;synchronized（mp1），mamp2会被锁 ，原因：二者是同一个对象
	 */
	public static Map map1 = new HashMap();
	// public static Map map2 = map1;//new HashMap();
	public static Map map2 = new HashMap();

	public static void m1() {
		synchronized (map1) {
			System.out.println("run...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("end...");
		}
	}

	public static void m2() {
		synchronized (map2) {
			System.out.println("run...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("end...");
		}
	}

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			m1();
		});

		Thread t2 = new Thread(() -> {
			m2();
		});

		t1.start();
		t2.start();
	}
}
