package com.live.test.api.core.schedule.quartz.job;

import java.io.Serializable;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//@DisallowConcurrentExecution
public class ProxyJob implements Job, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3746693388092388399L;
	private Job realJob;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		this.realJob = (Job) jobDataMap.get("realJob");
		if (this.realJob != null) {
			this.realJob.execute(context);
		}
	}

}
