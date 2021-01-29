package com.live.test.javase.core.thread;

public class TestThread {
	public static void main(String[] args) {
		Object lock = new Object();
		Thread t = new Thread(() -> {
			synchronized (lock) {
				boolean holdsLock = Thread.holdsLock(lock);
				assert false;
				System.out.println(holdsLock);
			}
		});
		t.start();

		TimeUnit.SECONDS.sleep(2);
		t.dumpStack();

		boolean holdsLock = Thread.holdsLock(lock);
		System.out.println(holdsLock);

		assert Thread.holdsLock(new Object());

		t.join(10);
		t.interrupted();

		t.start();
		t.stop();
		t.destroy();
		t.sleep(0);

		t.isAlive();

		t.interrupt();
		t.isInterrupted();

		t.join();

		t.yield();

		t.wait();
		t.notify();
		t.notifyAll();
	}
}
