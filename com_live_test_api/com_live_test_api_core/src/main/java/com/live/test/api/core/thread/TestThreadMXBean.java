package com.live.test.api.core.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * JVM中线程的信息 java.lang包下有实现<br>
 * sun包下也有实现？可以获取到线程占用的内存资源？
 * 
 * @author live
 * @2020年5月20日 @下午6:00:38
 */
public class TestThreadMXBean {

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			System.out.println("run...");

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("end...");

		}, "t01");

		t.start();

		ThreadMXBean mxb = ManagementFactory.getThreadMXBean();
		long[] allThreadIds = mxb.getAllThreadIds();
		System.out.println(allThreadIds);
		System.out.println(mxb.getDaemonThreadCount());

		for (long id : allThreadIds) {
			ThreadInfo tInfo = mxb.getThreadInfo(id);
			System.out.println(tInfo);
			System.out.println(mxb.getCurrentThreadCpuTime());
			System.out.println(mxb.getCurrentThreadUserTime());
		}
	}
}
