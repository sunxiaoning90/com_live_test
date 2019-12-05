package com.live.test.api.core.schedule.jl.thread;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;

import com.live.test.api.core.schedule.ISchedule;


public abstract class ThreadSchedule implements ISchedule {
	private static Logger logger = LogManager.getLogger(ThreadSchedule.class);
	private String id;
	public String getId() {
		return id == null ? UUID.randomUUID().toString() : id;
	}
	private Thread thread;
	private volatile boolean allowRun = true;
	private ThreadSchedule() {
	}

	ThreadSchedule(Runnable runnable) {
		this();
		setThread(new Thread(runnable));
	}

	@Override
	public void execute() {
		this.getThread().start();
	}

	@Override
	public void execute(long delay) {
		execute(delay, -1);
	}

	@Override
	public void execute(long delay, long period) {
		if (isAllowRun()) {
			try {
				Thread.sleep(delay);
				this.getThread().start();
				if (period >= 0) {
					this.getThread();
					Thread.sleep(period);
					execute(delay, period);
				}
			} catch (InterruptedException e) {
				logger.error(MarkerManager.getMarker("core.platform"),"ThreadSchedule.execute","错误信息:{}",e);
				e.printStackTrace();
			}
		}
	}

	@Override
	public void cancel() {
		this.setAllowRun(false);
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isAllowRun() {
		return allowRun;
	}

	public void setAllowRun(boolean allowRun) {
		this.allowRun = allowRun;
	}

	public static void main(String[] args) {
//		ISchedule s = new ThreadSchedule(new Runnable() {
//			
//			@Override
//			public void run() {
//			}
//		});
		
//		s.execute();
//		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
//		s.execute(2000);
//		s.execute(2000,1000);
		
	}

}
