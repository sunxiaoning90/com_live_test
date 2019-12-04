package com.live.test.api.core.schedule.schedule.impl.timer;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;

import com.live.test.api.core.schedule.schedule.ISchedule;

/**
 * 基于Timer实现调度
 * 
 */
public abstract class TimerSchedule implements ISchedule {
	
	private static Logger logger = LogManager.getLogger(TimerSchedule.class);
	
	private Timer timer;
	private TimerTask task;

	private TimerSchedule() {
		timer = new Timer();
	}

	private TimerSchedule(TimerTask task) {
		this();
		this.setTask(task);
	}

	public TimerSchedule(Runnable runnable) {
		this(new TimerTask() {
			@Override
			public void run() {
				try {
					runnable.run();
				} catch (Exception e) {
					logger.error(MarkerManager.getMarker("core.platform"),"TimerSchedule.TimerSchedule","错误信息:{}",e);
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void execute() {
		this.execute(0);
	}

	@Override
	public void execute(long delay) {
		if (this.task != null) {
			timer.schedule(task, delay);
		}
	}

	@Override
	public void execute(long delay, long period) {
		if (this.task != null) {
			timer.schedule(task, delay, period);
		}
	}

	public TimerTask getTask() {
		return task;
	}

	public void setTask(TimerTask task) {
		this.task = task;
	}

	@Override
	public void cancel() {
		timer.cancel();
	}

	public static void main(String[] args) {
//		ISchedule s = new TimerSchedule(new Runnable() {
//			@Override
//			public void run() {
//			}
//		});
//		s.execute(2000,1000);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
