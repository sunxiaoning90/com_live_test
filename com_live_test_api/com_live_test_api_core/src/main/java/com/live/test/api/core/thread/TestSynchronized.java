package com.live.test.api.core.thread;

public class TestSynchronized {

	public synchronized void m1() {
		// ...
	}

	public void m2() {
		synchronized (this) {
			// ...
		}
	}

	Object lock = new Object();

	public void m3() {
		synchronized (lock) {
			// ...
		}
	}

	public void m4() {
		synchronized (Object.class) {
			// ...
		}
	}

	public static synchronized void m5() {
		// ...
	}

	public static void m6() {
		synchronized (Object.class) {
			// ...
		}
	}

	public static void main(String[] args) {
		
	}
}
