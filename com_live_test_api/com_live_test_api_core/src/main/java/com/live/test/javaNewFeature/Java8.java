package com.live.test.javaNewFeature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
/**
 * Java8新特性
 * @author live
 * @2020年5月27日 @上午10:15:35
 */
public class Java8 {

	/**
	 * lumbda 
	 */
	public void test1() {
		Thread t = new Thread(() -> {
			System.out.println("run...");
		});

		t.start();
	}
	
	/**
	 * lumbda 
	 *  ::
	 */
	public void test2() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		map.forEach(System.out::printf);
	}
	
	public void test3() {
			Thread mainThread = Thread.currentThread();

        ThreadGroup mainThreadGroup = mainThread.getThreadGroup();

        int count = mainThreadGroup.activeCount();
        Thread[] threads = new Thread[count];
        mainThreadGroup.enumerate(threads, true);

        Stream.of(threads)
                .filter(Thread::isAlive)
                .forEach(thread -> {
                    System.out.println("线程 : " + thread);
                });
	}
	public static void main(String[] args) {
		new Java8().test1();
	}
}
