package com.live.test.api.core.schedule.byScheduledThreadPool;

import java.util.concurrent.ScheduledFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Notifiable implements Runnable {
	private final int LONGTIME_EXECUTE_MS = 3000;
	protected static Logger log4j = LogManager.getLogger("Notifiable");
	public String stackTrace = null;

	public Long getHandler() {
		return handler;
	}

	public void setHandler(Long handler) {
		this.handler = handler;
	}

	private Long handler;
	private Timer timer;
	public Notifiable timeoutAction;
	private ScheduledFuture<?> scheduledFuture;

	public ScheduledFuture<?> getScheduledFuture() {
		return scheduledFuture;
	}

	public void setScheduledFuture(ScheduledFuture<?> scheduledFuture) {
		this.scheduledFuture = scheduledFuture;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Notifiable() {
		StackTraceElement[] stea = Thread.currentThread().getStackTrace();
		StackTraceElement ste = stea[3];
		stackTrace = String.format("%s.%s(%s:%d)", ste.getClassName(), ste.getMethodName(), ste.getFileName(),
				ste.getLineNumber());
		addTime = System.currentTimeMillis();
	}

	public Notifiable(Notifiable timeoutAction) {
		StackTraceElement[] stea = Thread.currentThread().getStackTrace();
		StackTraceElement ste = stea[3];
		stackTrace = String.format("%s.%s(%s:%d)", ste.getClassName(), ste.getMethodName(), ste.getFileName(),
				ste.getLineNumber());
		addTime = System.currentTimeMillis();
		this.timeoutAction = timeoutAction;
	}

	private Long notifyTime;
	private Long addTime;
	private Long startTime;
	private Long endTime;

	public String getStackTrace() {
		return stackTrace;
	}

	public Long getNotifyTime() {
		return notifyTime;
	}

	public Long getAddTime() {
		return addTime;
	}

	public Long getStartTime() {
		return startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setNotifyTime(Long time) {
		this.notifyTime = time;
	}

	public abstract void notice();

	public long getRunningTime(long stamp) {
		if (startTime == null)
			return 0;
		return stamp - startTime;
	}

	@Override
	public void run() {
		startTime = System.currentTimeMillis();
		try {
			notice();
		} catch (Exception e) {
			log4j.warn(String.format("任务执行发生错误, 任务位置: %s, stacktrace: ", stackTrace), e);
		}
		endTime = System.currentTimeMillis();
		if (endTime - startTime >= LONGTIME_EXECUTE_MS) {
			log4j.warn(String.format("Long time task execution: %s, time: %.3fs", stackTrace,
					(endTime - startTime) * 1.0 / 1000));
		}
	}
}
