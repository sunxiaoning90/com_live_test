package com.live.test.api.core.serverloadbalance.serverdispatcher;

import com.live.test.api.core.serverloadbalance.serverdispatcher.impl.PollServerDispatcher;

public class Client {
	public static void main(String[] args) {
		new Client().test1();
	}
	
	private void test1() {
		int min = 1;
		int max = 5;
		IServerDispatcher sd = new PollServerDispatcher(min, max);
		
		new java.lang.Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					testGetNext(sd);
				}
			}
		}).start();
		
		for (int i = 1; i <= 10; i++) {
			testGetNext(sd);
		}
	}

	private void testGetNext(IServerDispatcher sd) {
//		int next = sd.getNext();
//		long count = sd.getCount();
//		int last = sd.getLast();
//		System.out.println("count:" + count + ",last:" + last + ",next:" + next);
	}
}
