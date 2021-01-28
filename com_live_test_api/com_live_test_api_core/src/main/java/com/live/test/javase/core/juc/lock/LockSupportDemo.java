package com.live.test.javase.core.juc.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
	   public static Object u = new Object();
	    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

	    public static class ChangeObjectThread extends Thread {
	        public ChangeObjectThread(String name) {
	            super(name);
	        }
	        @Override public void run() {
	            synchronized (u) {
	                System.out.println("in " + getName());
	                LockSupport.park();
	                if (Thread.currentThread().isInterrupted()) {
	                    System.out.println("被中断了");
	                }
	                System.out.println("继续执行");
	            }
	        }
	    }

	    public static void main(String[] args) throws InterruptedException {
	    	System.out.println(1);
	    	
	        t1.start();
	        Thread.sleep(1000L);
	        t2.start();
	        Thread.sleep(3000L);
	        t1.interrupt();
	        LockSupport.unpark(t2);
	        
	        //在很多情况下，主线程创建并启动子线程，如果子线程中要进行大量的耗时运算，主线程将可能早于子线程结束。如果主线程需要知道子线程的执行结果时，就需要等待子线程执行结束了。主线程可以sleep(xx),但这样的xx时间不好确定，因为子线程的执行时间不确定，join()方法比较合适这个场景。
	        t1.join(); //等待线程死亡。
	        t2.join();
	        
	        t1.interrupt(); //测试该线程是否已被中断。线程的中断状态不受此方法的影响。
	        t1.isInterrupted();
	        
	        t1.yield();
	    }
	}
