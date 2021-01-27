package com.live.test.javase.core.thread;

public class TestThreadStackTrace {
	
	public static void main(String[] args) {
		m2();
	}

	public static void m2() {
		m3();
	}

	public static void m3() {
		StackTraceElement[] stea = Thread.currentThread().getStackTrace();
		// StackTraceElement ste = stea[3];
		for (StackTraceElement ste : stea) {
			String info = String.format("%s#%s(%s:%d)", ste.getClassName(), ste.getMethodName(), ste.getFileName(),
					ste.getLineNumber());
			System.out.println(info);
		}
	}
}
