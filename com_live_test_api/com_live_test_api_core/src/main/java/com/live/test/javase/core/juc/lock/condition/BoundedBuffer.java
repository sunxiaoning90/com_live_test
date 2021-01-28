package com.live.test.javase.core.juc.lock.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 等待/唤醒 :await()/signal()（by juc.Condition）
 * 
 * @author live
 */
public class BoundedBuffer {

	final Lock lock = new ReentrantLock();

	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();

	final Object[] items = new Object[2];
	int putptr, takeptr, count;

	public BoundedBuffer() {
	}

	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length)
				notFull.await();
			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0)
				notEmpty.await();
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		BoundedBuffer bb = new BoundedBuffer();

		Thread t1 = new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					bb.put(i);
					System.out.println("存：" + i);
					TimeUnit.SECONDS.sleep(1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					Object take = bb.take();
					System.out.println("取：" + take);
					TimeUnit.SECONDS.sleep(2);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		t2.start();
		t1.start();

		System.out.println("end");
	}
}
