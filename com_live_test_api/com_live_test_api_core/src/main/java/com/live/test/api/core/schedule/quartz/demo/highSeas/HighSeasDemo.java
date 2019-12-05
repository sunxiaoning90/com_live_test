package com.live.test.api.core.schedule.quartz.demo.highSeas;

import java.io.Serializable;
import java.util.Date;

import org.quartz.Scheduler;

import com.live.test.api.core.schedule.ISchedule;
import com.live.test.api.core.schedule.ScheduleManager;
import com.live.test.api.core.schedule.quartz.QuartzSchedule;
import com.live.test.api.core.schedule.quartz.Task;
import com.live.test.api.core.schedule.quartz.factory.SchedulerFactory;


/**
 * 
 * @author live
 *
 *         May 27, 2019 6:13:58 PM
 */
public class HighSeasDemo implements Serializable {
	private static final String NAME = "公海回收";
	/**
	 * 
	 */
	private static final long serialVersionUID = 2548466791100954623L;
	public HighSeasDemo(){
		this.initCusSchedule();
	}
	private void initCusSchedule() {
		System.out.println("模块启动时初始化Schedule：" + NAME);
		Scheduler scheduler = SchedulerFactory.buildScheduler();

		ISchedule schedule = new QuartzSchedule(scheduler, ScheduleManager.SCHEDULE_HIGHSEAS_RECYCLE);

		System.out.println("====" + schedule.getId());
		ScheduleManager.getInstance().put(ScheduleManager.SCHEDULE_HIGHSEAS_RECYCLE, schedule);
		ScheduleManager.getInstance().get(ScheduleManager.SCHEDULE_HIGHSEAS_RECYCLE).execute();
		System.out.println("初始化Schedule完毕:" + NAME);
	}

	public void save() {
		System.out.println("保存");
		String param = "参数测试";
		ScheduleManager.getInstance().get(ScheduleManager.SCHEDULE_HIGHSEAS_RECYCLE).addTask(new Task() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -3703852172508358053L;

			@Override
			public void run() {
				System.out.println("*** 具体业务 ***" + param + new Date());
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("*** 具体业务完毕 ***" + param + new Date());
			}

			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return null;
			}
		}
		// , 1000 * 0, 2, 1, 0
		);

		System.out.println("<<<<保存完毕");
		
	}

	public static void main(String[] args) {
		HighSeasDemo c = new HighSeasDemo();
		c.initCusSchedule();

		int count = 1;
		for (int i = 0; i < count; i++) {

			new Thread(new Runnable() {
				@Override
				public void run() {
					c.save();
				}
			}).start();
		}
	}
	
}
