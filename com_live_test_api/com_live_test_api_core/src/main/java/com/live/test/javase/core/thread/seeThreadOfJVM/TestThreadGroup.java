package com.live.test.javase.core.thread.seeThreadOfJVM;

public class TestThreadGroup {

	/**
	 * 一、通过 java.lang.ThreadGroup 实时查看JVM中的线程明细 1、在JVM 退出时，如何保证已start的 '用户线程' 执行完毕？
	 * JVM 自身能保证已经start的线程进行执行完毕 <br>
	 * 
	 * 2、在JVM 退出时，如何保证未start的 用户线程' 执行完毕？ ThreadGroup（线程组） -> 遍历 ->判断是NEW状态则start
	 * (可惜无法获取到状态为NEW的线程)
	 */
	public static void main(String[] args) {
		ThreadGroup group = Thread.currentThread().getThreadGroup();

		while (group.getParent() != null) {
			// 返回此线程组的父线程组
			group = group.getParent();
		}

		// 此线程组中活动线程的计数
		int tCount = group.activeCount();
		Thread[] ts = new Thread[tCount];
		// 把对此线程组中的所有活动子组的引用复制到指定数组中。
		group.enumerate(ts);

		for (Thread thread : ts) {
			System.out.println("线程数量：" + tCount + " 线程id：" + thread.getId() + " 线程名称：" + thread.getName() + " 线程状态："
					+ thread.getState());
		}

		System.out.println("JVM quit!");
	}

}
