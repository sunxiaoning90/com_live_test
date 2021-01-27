package com.live.test.api.core.schedule.byScheduledThreadPool;

import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		Timer.getInstance("t1",1).add(3000, new Notifiable() {
			
			@Override
			public void notice() {
				System.out.println("start...");
				
				for (;;) {
					System.out.println("loop");
					
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
//				System.out.println("end!");
				}
			}
		});
	}
}
