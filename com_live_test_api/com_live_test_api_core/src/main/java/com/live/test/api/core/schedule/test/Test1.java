package com.live.test.api.core.schedule.test;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.live.test.api.core.schedule.schedule.ISchedule;
import com.live.test.api.core.schedule.schedule.ScheduleManager;
import com.live.test.api.core.schedule.schedule.impl.qz.Task;
import com.live.test.api.core.schedule.schedule.impl.qz.job.ConcurrentJob;

public class Test1 {

	public void a() {
		System.out.println("start_");
		// 创建JobDetial
		JobBuilder jobBuilder = JobBuilder.newJob(ConcurrentJob.class).withIdentity("job1", "group1");
		JobDetail jobDetail = jobBuilder.build();

		// 触发器
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		triggerBuilder.withIdentity("trigger1", "group1");

		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule();
		schedBuilder.withIntervalInSeconds(2);// 时间间隔
		schedBuilder.withRepeatCount(6);// 重复次数

		triggerBuilder.withSchedule(schedBuilder);

		// 设置停止时间
//        triggerBuilder.endAt(new Date(System.currentTimeMillis() + 5));

		Trigger trigger = triggerBuilder.build();

		// 调度器 :创建Scheduler对象，并配置JobDetail和Trigger对象
		Scheduler scheduler;
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			System.out.println("scheduler:" + scheduler);

			// 将任务及其触发器放入调度器
			scheduler.scheduleJob(jobDetail, trigger);

			// 调度器开始调度任务
			scheduler.start();

			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 关闭调度器
			scheduler.shutdown(true);
		} catch (SchedulerException e1) {
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) throws SchedulerException {
		// new Test1().a();
		ISchedule s = ScheduleManager.getInstance().get("1");
		System.out.println(s);

		s = ScheduleManager.getInstance().get("1");
		System.out.println(s);

		s.addTask(new Task() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void run() {
				System.out.println("---id-001第一个" + new Date());
			}

			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return "id-001";
			}
		}, 1000 * 10, 0, 0, 0
//		,0,0,0,0
		);

		s.addTask(new Task() {

			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public void run() {
				System.out.println("---id-002" + new Date());
			}

			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return "id-002";
			}
		}, 1000 * 10, 0, 0, 0);

		s.cancelOne("id-002");
	}

}
