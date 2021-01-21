package com.live.test.javase.core.synchronizedTest;

import java.util.concurrent.TimeUnit;

public class TestSynchronizedByString {

	/**
	 * 使用 String 做为锁时synchronized (s)，实际上存在线程安全问题
	 * 解决：synchronized (s.intern()）;
	 * @param s
	 */
private void testStr(String s) {
		
		System.out.println(s);
		System.out.println(s.hashCode());
		new ThreadTest(s).start();
	}
	
	class ThreadTest extends Thread{
		String s;
		public ThreadTest(String s){
			this.s =s;
		}
		@Override
		public void run() {
			super.run();
			synchronized (s) {
//			synchronized (s.intern()) {
				for(;;) {
					System.out.println(Thread.currentThread().getName());
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		String a = new String("hi");
		new TestSynchronizedByString().testStr(a);
		
		String b= "hi";
		new TestSynchronizedByString().testStr(b);
		
		String cc= "hi";
		new TestSynchronizedByString().testStr(cc);
		
		System.out.println(a == b);
		System.out.println(cc == b);
				
		

//	System.exit(0);
	}
}
