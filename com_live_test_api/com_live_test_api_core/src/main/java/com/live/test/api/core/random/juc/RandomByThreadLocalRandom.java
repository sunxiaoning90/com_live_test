package com.live.test.api.core.random.juc;

import java.util.concurrent.ThreadLocalRandom;

/*
 * 测试随机数类 
java.util.concurrent.ThreadLocalRandom 工具类
ThreadLocalRandom 是 JDK 7 之后提供，也是继承至 java.util.Random。
 */
public class RandomByThreadLocalRandom {
	   public static void main(String args[]) {
	        new MyThread().start();
	        new MyThread().start();
	    }
	}
	class MyThread extends Thread {
	    public void run() {
	        for (int i = 0; i < 2; i++) {
	            System.out.println(Thread.currentThread().getName() + ": " + ThreadLocalRandom.current().nextDouble());
	        }
	    }
	}