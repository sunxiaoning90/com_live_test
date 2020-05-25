package com.live.test.api.core.thread;

public class TestDaemonThread {

	/**
	 * JVM主线程退出时，用户线程会继续执行吗？守护线程会继续执行吗？
	 * 
	 * 主线程退出时, 如果有用户线程在执行，会等用户线程会执行完毕后再退出。 <br>
	 * 如果没有'用户线程', 只剩'守护线程',那 守护线程 不再继续执行，主线程退出。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("run...");
		});

		t.setDaemon(true);// 设置 守护线程比较特殊，需啊在start前设置。
		t.start();

		System.out.println("JVM quit!");
	}
}
