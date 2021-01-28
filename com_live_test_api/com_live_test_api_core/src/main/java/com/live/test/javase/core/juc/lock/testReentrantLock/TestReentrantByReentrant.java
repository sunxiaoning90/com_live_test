package com.live.test.javase.core.juc.lock.testReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantByReentrant {

	Lock l = new ReentrantLock(); // 可重入 锁

	private void m1() {
		try {
			l.lock();
			System.out.println(Thread.currentThread().getName() + "-> m1.");
			this.m2();
		} catch (Exception e) {
		} finally {
			l.unlock();
		}

	}

	private void m2() {
		try {
			l.lock();
			System.out.println(Thread.currentThread().getName() + "-> m2.");
		} catch (Exception e) {
		} finally {
			l.unlock();
		}
		
		synchronized (this) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		TestReentrantByReentrant o = new TestReentrantByReentrant();

		Thread t1 = new Thread(() -> {
			o.m1();
		}, "t1");

		t1.start();
	}
}
