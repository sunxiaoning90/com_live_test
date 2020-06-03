package com.live.test.javase.core;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) {
		Timer t = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("run..." + new Date());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("end...");
			}
		};
//		t.schedule(task , 0, 2000);
		t.scheduleAtFixedRate(task, 0, 2000);
	}
}
