package com.live.test.api.core.schedule.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import com.live.test.api.core.schedule.ISchedule;
import com.live.test.api.core.schedule.ScheduleManager;
import com.live.test.api.core.schedule.quartz.factory.TriggerFactory;
import com.live.test.api.core.schedule.quartz.job.ProxyJob;

public class QuartzSchedule implements ISchedule {
	private String id;

	public QuartzSchedule() {
		//this.setId(UUID.randomUUID().toString());
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	private Scheduler scheduler;
//	private Trigger trigger;
//	private JobDetail jobDetail;

	public QuartzSchedule(Scheduler scheduler) {
		this();
		this.scheduler = scheduler;
	}

	public QuartzSchedule(Scheduler scheduler, String id) {
		this(scheduler);
		this.setId(id);
	}

	@Override
	public void execute() {
		try {
			// 调度器开始调度任务
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(long delay) {

	}

	@Override
	public void execute(long delay, long period) {

	}

	@Override
	public void cancel() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

//	public void addTask_bak(Task r) {
//		//
//		Trigger trigger = TriggerFactory.buildScheduler();
//
//		//
////	JobBuilder jobBuilder = JobBuilder.newJob(ProxyJob.class);
//		JobBuilder jobBuilder = JobBuilder.newJob(ProxyJob.class).withIdentity(new JobKey(r.getId()));
//
//		JobDetail jobDetail = jobBuilder.build();
//		//jobDetail.getJobDataMap().put("realJob", new ConcurrentJob(r));
//		jobDetail.getJobDataMap().put("realJob", ScheduleManager.getInstance().getJob(this.id,r));
//		try {
//			scheduler.scheduleJob(jobDetail, trigger);
//		} catch (SchedulerException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void addTask(Task r) {
		this.addTask(r, 0, 0, 0, 0);
	}

	@Override
	public void addTask(Task r, long delay, int repeatCount, int intervalInSeconds, long endAt) {
		if (r == null) {
			try {
				throw new Exception("任务不允许为null");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//
		Trigger trigger = TriggerFactory.buildScheduler(delay, repeatCount, intervalInSeconds, endAt);

		//
		JobBuilder jobBuilder = JobBuilder.newJob(ProxyJob.class);
		if (r.getId() != null) {
			jobBuilder.withIdentity(JobKey.jobKey(r.getId()));
		}

		JobDetail jobDetail = jobBuilder.build();
		jobDetail.getJobDataMap().put("realJob", ScheduleManager.getInstance().getJob(this.id,r));
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cancelOne(String id) {
		if (id != null) {
			try {
				scheduler.deleteJob(JobKey.jobKey(id));
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
	}

}
