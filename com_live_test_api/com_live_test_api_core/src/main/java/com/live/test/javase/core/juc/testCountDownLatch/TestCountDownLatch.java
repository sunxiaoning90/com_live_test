package com.live.test.javase.core.juc.testCountDownLatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLatch {
	
	public static void main(String[] args) {
		//CountDownLatch demo， 顺便测试 map 多线程扩容时，CPU 100%
		Map<Integer, String> map = new HashMap<Integer, String>(1);

		CountDownLatch c = new CountDownLatch(1); // 计数器 设置总数为1 ，当计数器为0时，唤起 await的线程

		for (int i = 0; i < 10000; i++) {
			new Thread(() -> {
				try {
					c.await(); // 等待

					for (int j = 0; j < 100000; j++) {
						String r = map.put(new Integer(j), "1");
						// System.out.println(">>>" + r);
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					System.out.println(1);
					// c.countDown();
				}
			}).start();
		}

		new Thread(() -> {
			try {
				c.await();

				for (int i = 0; i < 10000000; i++) {
					String r = map.get(new Integer(i));
					// System.out.println("<<<<<<<<<<<<" +r);
				}

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				System.out.println(2);
				// c.countDown();
			}
		}).start();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		c.countDown(); // 计数器 -1

		System.out.println("end...");
	}
}
