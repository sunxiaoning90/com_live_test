package com.live.test.api.core.schedule;

import com.live.test.api.core.schedule.quartz.Task;

/**
 * 调度，接口
 * 
 * @author live
 */
public interface ISchedule {
	String getId();

	void execute();

	void execute(long delay);

	void execute(long delay, long period);

	/**
	 * 取消全部任务
	 */
	void cancel();

	/**
	 * 取消单个任务
	 * 
	 * @param id
	 */
	void cancelOne(String id);

	/**
	 * 加入任务，立刻执行
	 * 
	 * @param r
	 */
	public void addTask(Task r);

	/**
	 * 
	 * @param r
	 * @param delay             : 延迟
	 * @param repeatCount       ： 重复次数
	 * @param intervalInSeconds ： 间隔
	 * @param endAt             ： 执行多久后终止
	 */
	public void addTask(Task r, long delay, int repeatCount, int intervalInSeconds, long endAt);
}
