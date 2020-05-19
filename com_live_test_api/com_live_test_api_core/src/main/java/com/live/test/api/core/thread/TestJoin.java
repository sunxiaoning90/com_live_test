package com.live.test.api.core.thread;

/**
 * 线程必须要先start，才能join，只有启动了，才能对线程进copy行操作。
 * 
 * A.start(); //启动A线程 A.join(); //邀请A线程先执行度，本线程先暂停执行，等待A线程执行完后，主知线程再接着往下执行
 * 
 * @author live
 * @2020年5月19日 @下午3:08:42
 */
public class TestJoin {

	public static void main(String[] args) {

		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("run...");
			}
		};

		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		Thread t2 = new Thread() {
			@Override
			public void run() {
				System.out.println("run2...");
			}
		};

		t2.start();
	}
}
