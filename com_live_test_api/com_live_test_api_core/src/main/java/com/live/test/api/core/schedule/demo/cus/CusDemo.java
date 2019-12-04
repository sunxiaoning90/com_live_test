package com.live.test.api.core.schedule.demo.cus;

import java.io.Serializable;
import java.util.Date;

import org.quartz.Scheduler;

import com.live.test.api.core.schedule.schedule.ISchedule;
import com.live.test.api.core.schedule.schedule.ScheduleManager;
import com.live.test.api.core.schedule.schedule.impl.qz.QuartzSchedule;
import com.live.test.api.core.schedule.schedule.impl.qz.Task;
import com.live.test.api.core.schedule.schedule.impl.qz.factory.SchedulerFactory;


public class CusDemo implements Serializable {
	private static final String NAME = "客户提醒";
	/**
	 * 
	 */
	private static final long serialVersionUID = 2548466791100954623L;

	private void initCusSchedule() {
		System.out.println("模块启动时初始化Schedule：" + NAME);
		Scheduler scheduler = SchedulerFactory.buildScheduler();

		ISchedule schedule = new QuartzSchedule(scheduler,ScheduleManager.SCHEDULE_CUS_REMIND);

		System.out.println(schedule.getId());
		ScheduleManager.getInstance().put(ScheduleManager.SCHEDULE_CUS_REMIND, schedule);
		ScheduleManager.getInstance().get(ScheduleManager.SCHEDULE_CUS_REMIND).execute();
		System.out.println("初始化Schedule完毕:" + NAME);
	}

	private void saveContactPlan() {
		System.out.println("保存客户联系计划");
		String param = "参数测试";
//		ScheduleManager.getInstance().get("cus").addTask(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("*** 具体业务 ***" + param);
//			}
//		});

		ScheduleManager.getInstance().get(ScheduleManager.SCHEDULE_CUS_REMIND).addTask(new Task() {

			
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
		//, 1000 * 0, 5, 1, 0
		);

		System.out.println("保存客户联系计划完毕");
	}

	public static void main(String[] args) {
		CusDemo c = new CusDemo();
		c.initCusSchedule();

		int count = 5;
		for (int i = 0; i < count; i++) {

			new Thread(new Runnable() {
				@Override
				public void run() {
					c.saveContactPlan();
				}
			}).start();
		}

	}
}
