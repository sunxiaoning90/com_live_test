package com.live.test.api.core.thread.stopThread;

/**
 * 停止线程方式之一：
 * 判断线程是否处于interrupt状态,isInterrupted() （中断状态），是则退出。
 * @author live
 * @2020年5月20日 下午6:51:33
 */
public class StopThreadByTagIsInterrupted extends Thread {

	@Override
	public void run() {
		while (!this.isInterrupted()) {//判断是否 interrupted
			System.out.println("run...");
		}
	}

	public static void main(String[] args) {
		StopThreadByTagIsInterrupted t = new StopThreadByTagIsInterrupted();
		t.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t.interrupt(); //设置 interrupted状态
	}

}
