package com.live.test.javase.core.loop;

public class LoopTest {

	public static void m1() {
		boolean flg = true;
		while (flg)
			; // 注意分号，不要误操作
		{
			System.out.println("run...");
		}
	}

	public static void m2() {
		for (;;) {
			System.out.println("run...");

			try {
				Thread.sleep(1); // 休眠 1 毫秒，避免cpu占用 100%
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * <pre>
	 * Thread.Sleep(0)的作用，就是“触发操作系统立刻重新进行一次CPU竞争”。
	 * 竞争的结果也许是当前线程仍然获得CPU控制权，也许会换成别的线程获得CPU控制权。
	 * 这也是我们在大循环里面经常会写一句Thread.Sleep(0) ，因为这样就给了其他线程比如Paint线程获得CPU控制权的权力，这样界面就不会假死在那里。
	 * </pre>
	 */
	public static void m3() {
		for(;;) {
			try {
				Thread.sleep(0);
			} catch (InterruptedException egnore) {
			}
		}
	}
	
}
