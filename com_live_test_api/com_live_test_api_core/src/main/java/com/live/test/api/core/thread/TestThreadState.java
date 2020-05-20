package com.live.test.api.core.thread;

import java.lang.Thread.State;

/**
 * 线程5种状态，
 * 查看JDK注释
 * @author live
 * @2020年5月20日 @上午10:36:08
 * 
 * 状态注释
 * 
 */
public class TestThreadState {
	/**
	 * NEW RUNNABLE run... TIMED_WAITING RUNNABLE TERMINATED
	 */
	public static void main(String[] args) {
//		Thread t = new Thread(() -> System.err.println("run"));

		Thread t = new Thread(() -> {
			System.err.println("run...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(t.getState());

		t.start();

		State state = t.getState();
		System.out.println(state);

		long start = System.currentTimeMillis();
		long end;
		for (;;) {
			State s = t.getState();
			if (!s.equals(state)) {
				state = s;
				System.out.println(state);
			}

			end = System.currentTimeMillis();
			if (end - start > 10 * 1000) {
				break;
			}
		}

	}
}
