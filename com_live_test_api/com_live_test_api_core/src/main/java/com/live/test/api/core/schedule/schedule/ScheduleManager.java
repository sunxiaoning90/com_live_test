package com.live.test.api.core.schedule.schedule;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.Scheduler;

import com.live.test.api.core.cache.local.ICache;
import com.live.test.api.core.cache.local.ju.map.MapCache;
import com.live.test.api.core.schedule.schedule.impl.qz.QuartzSchedule;
import com.live.test.api.core.schedule.schedule.impl.qz.Task;
import com.live.test.api.core.schedule.schedule.impl.qz.factory.SchedulerFactory;
import com.live.test.api.core.schedule.schedule.impl.qz.job.ConcurrentJob;
import com.live.test.api.core.schedule.schedule.impl.qz.job.SerialJob;

/**
 *@author live
 */
public class ScheduleManager {

	private static final Logger log = LogManager.getLogger(ScheduleManager.class);
	private volatile ICache<String, ISchedule> cache;
	private ICache<String, Boolean> JobTypeCache;// true：并行，false：串行

	public static final String SCHEDULE_CUS_REMIND = "SCHEDULE_CUS_REMIND";
	public static final String SCHEDULE_HIGHSEAS_RECYCLE = "SCHEDULE_HIGHSEAS_RECYCLE";
	public static final String SCHEDULE_REPORT_CUS = "SCHEDULE_REPORT_CUS";

	private volatile static ScheduleManager instance;

	private ScheduleManager() {
		this.init();
	}

	private void init() {
		log.info("初始化");
		cache = new MapCache<String, ISchedule>();
		JobTypeCache = new MapCache<String, Boolean>();

		this.JobTypeCache.put(SCHEDULE_CUS_REMIND, false);
		this.JobTypeCache.put(SCHEDULE_HIGHSEAS_RECYCLE, false);
		this.JobTypeCache.put(SCHEDULE_REPORT_CUS, false);
	}

	public static ScheduleManager getInstance() {
		if (instance == null) {
			synchronized (ScheduleManager.class) {
				if (instance == null) {
					instance = new ScheduleManager();
				}
			}
		}
		return instance;
	}

	public void put(String id, ISchedule schedule) {
		this.cache.put(id, schedule);
	}

	/*
	 * public ISchedule get(String id) { return this.cache.get(id); }
	 */

	public ISchedule get(String id) {
		ISchedule s;
		s = this.cache.get(id);
		if (s == null) {
			synchronized (this) {
				s = this.cache.get(id);
				if (s == null) {
					s = this.initSchedule(id);
					if (s != null) {
						this.cache.put(id, s);
					}
				}
			}
		}
		return s;
	}

	private ISchedule initSchedule(String id) {
		/// 暂时初始化schedule
		System.out.println("初始化Schedule：" + id);
		Scheduler scheduler = SchedulerFactory.buildScheduler();

		ISchedule schedule = new QuartzSchedule(scheduler, id);
		System.out.println(schedule.getId());
		schedule.execute();
		// put(ScheduleManager.SCHEDULE_CUS_REMIND, schedule);
		System.out.println("初始化Schedule完毕:" + id);
		return schedule;
	}

	public void remove(String id) {
		this.cache.remove(id);
	}

	public void execute() {
		List<ISchedule> allAsList = cache.getAllAsList();
		for (ISchedule schedule : allAsList) {
			schedule.execute();
		}
	}

	public void addJob(String id, Task r) {
		ISchedule iSchedule = get(id);
		// QuartzSchedule q = (QuartzSchedule) iSchedule;
		// q.addJob(r);
		iSchedule.addTask(r);
	}

	public boolean getJobType(String id) {
		Boolean flg = this.JobTypeCache.get(id);
		if (flg == null) {
			return false;
		}
		return flg;
	}

	public Job getJob(String id, Task r) {
		if (Boolean.valueOf(this.getJobType(id))) {
			return new ConcurrentJob(r);
		} else {
			return new SerialJob(r);
		}
	}

}
