package com.live.test.javase.core.thread.stopThread;

/**
 * 停止线程方式之一：
 * 设置并判断停止标识flg，判断flg是否为true（停止），true则退出。
 * @author live
 * @2020年5月20日 下午6:51:33
 */
public class StopThreadByTagMine extends Thread {
	/**
	 * 停止标识
	 */
	private volatile boolean stopped = false;

	@Override
	public void run() {
		while (!stopped) {
			System.out.println("run...");
		}
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public static void main(String[] args) {
		StopThreadByTagMine t = new StopThreadByTagMine();
		t.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t.setStopped(true);
	}

}
