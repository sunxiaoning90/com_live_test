package com.live.test.api.core.schedule.quartz.test;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleTrigger;

public class CusJob implements Job,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6033962688887939842L;
	private String name = "客户资料任务";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("开始任务执行：" + this.name );
		//...

//		System.out.println("上次执行时间: " + context.getPreviousFireTime());// 上次执行时间
//		System.out.println("本次执行时间  " + context.getFireTime());// 本次执行时间
//		System.out.println("下一次执行时间: " + context.getNextFireTime());// 下一次执行时间
//		System.out.println("循环次数: " + context.getJobRunTime());// 循环次数

		SimpleTrigger st = (SimpleTrigger) context.getTrigger();
		System.out.println("当前次数: " + st.getTimesTriggered());
		if (st.getTimesTriggered() == 2) {
			JobExecutionException e = new JobExecutionException();
//			e.setUnscheduleAllTriggers(true);// 设置 将自动 去除 这个任务的触发器,所以这个任务不会再执行
			e.setUnscheduleAllTriggers(false);//后续任务继续执行
			throw e;
//			int a = 1/0;
		}
		
		
		System.out.println("结束\n");
	}

}
