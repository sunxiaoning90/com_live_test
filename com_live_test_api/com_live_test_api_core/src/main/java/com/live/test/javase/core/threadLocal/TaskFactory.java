package com.live.test.javase.core.threadLocal;

public class TaskFactory {
	public static Runnable createTask(String name) {
		Runnable task = new Runnable() {

			@Override
			public void run() {
				long threadId = Thread.currentThread().getId();
				// System.out.println(threadId);
				ThreadLocalHelper.remove();
				ThreadLocalHelper.set(name);
				Object v = ThreadLocalHelper.get();
				System.out.println("<<<<threadId:	" + threadId + ", get:	" + v);
			}
		};

		return task;
	}
}
