package com.live.test.javase.core.loop;

public class LoopTest {

	public static void m1() {
		int a = 1;
		for (; a < 2;);{ // 注意分号
			System.out.println("run...");
		}
	}

	public static void m2() {
		int a = 1;
		for (; a < 2;){
			System.out.println("run...");

			try {
				Thread.sleep(1); // 休眠 1 毫秒，避免cpu占用 100%
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
