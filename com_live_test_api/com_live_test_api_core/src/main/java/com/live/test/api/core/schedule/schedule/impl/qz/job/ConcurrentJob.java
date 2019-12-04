package com.live.test.api.core.schedule.schedule.impl.qz.job;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleTrigger;

/**
 * 客户资料提醒
 * @author live
 *
 */
public class ConcurrentJob implements Job, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2548466791100954623L;

	private String name = "并行job";

	private Runnable runnable;

	public ConcurrentJob() {
	}

	public ConcurrentJob(Runnable runnable) {
		this();
		this.runnable = runnable;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("开始任务执行：" + this.name);
		// ...

//		System.out.println("上次执行时间: " + context.getPreviousFireTime());// 上次执行时间
//		System.out.println("本次执行时间  " + context.getFireTime());// 本次执行时间
//		System.out.println("下一次执行时间: " + context.getNextFireTime());// 下一次执行时间
//		System.out.println("循环次数: " + context.getJobRunTime());// 循环次数

		SimpleTrigger st = (SimpleTrigger) context.getTrigger();
		System.out.println("当前次数: " + st.getTimesTriggered());
//		if (st.getTimesTriggered() == 2) {
//			JobExecutionException e = new JobExecutionException();
////			e.setUnscheduleAllTriggers(true);// 设置 将自动 去除 这个任务的触发器,所以这个任务不会再执行
//			e.setUnscheduleAllTriggers(false);//后续任务继续执行
//			throw e;
////			int a = 1/0;
//		}
		if (this.runnable != null) {
			this.runnable.run();
		}

		System.out.println("结束\n");
	}

	public Runnable getRunnable() {
		return runnable;
	}

	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}

}
