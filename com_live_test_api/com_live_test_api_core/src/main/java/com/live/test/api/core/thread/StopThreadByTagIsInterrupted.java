package com.live.test.api.core.thread;

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
