package com.live.test.api.core.thread;

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
