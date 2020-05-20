package com.live.test.api.core.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolRejected {

	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor poll = new ThreadPoolExecutor(2, 4, 7, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(2));
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("run...");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("end...");
			}
		};

		Runnable r2 = new Runnable() {

			@Override
			public void run() {
				System.out.println("run2...");
				System.out.println("end2...");
			}
		};

		for (int i = 0; i < 5; i++) {
			poll.execute(r);
			poll.execute(r2);
		}

		Thread.sleep(5000);

		poll.shutdown();
	}
}
