//package spzc.module.schedule.test;
//
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SimpleScheduleBuilder;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.impl.StdSchedulerFactory;
//
//import spzc.module.schedule.schedule.ISchedule;
//import spzc.module.schedule.schedule.ScheduleManager;
//import spzc.module.schedule.schedule.impl.qz.QuartzSchedule;
//import spzc.module.schedule.schedule.impl.qz.Task;
//
//public class ScheduleTest {
//	public Scheduler createScheduler() {
//		System.out.println("start_");
//		// 创建JobDetial
////		JobBuilder jobBuilder = JobBuilder.newJob(TestJob.class).withIdentity("job1", "group1");
////		JobBuilder jobBuilder = JobBuilder.newJob(ConcurrentJob.class);
////		JobDetail jobDetail = jobBuilder.build();
//
//		// 触发器
//		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
////		triggerBuilder.withIdentity("trigger1", "group1");
//
//		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule();
//		schedBuilder.withIntervalInSeconds(2);// 时间间隔
//		schedBuilder.withRepeatCount(1);// 重复次数
//
//		triggerBuilder.withSchedule(schedBuilder);
//
//		// 设置停止时间
////        triggerBuilder.endAt(new Date(System.currentTimeMillis() + 5));
//
////		Trigger trigger = triggerBuilder.build();
//		
//		// 调度器 :创建Scheduler对象，并配置JobDetail和Trigger对象
//		Scheduler scheduler = null;
//		try {
//			scheduler = StdSchedulerFactory.getDefaultScheduler();
//			System.out.println("scheduler:" + scheduler);
//
//			// 将任务及其触发器放入调度器
//			//scheduler.scheduleJob(jobDetail, trigger);
//
//		} catch (SchedulerException e1) {
//			e1.printStackTrace();
//		}
//		return scheduler;
//
//	}
//	public ISchedule createSchedule() {
//		System.out.println("a");
//		
//		Scheduler scheduler = createScheduler();
//		ISchedule schedule = new QuartzSchedule(scheduler);
//		return schedule;
//		
//	}
//	
//	public static void main(String[] args) {
//		ScheduleTest s = new ScheduleTest();
//		ISchedule schedule = s.createSchedule();
//		System.out.println(schedule.getId());
//		ScheduleManager.getInstance().put("cus", schedule);
//		ScheduleManager.getInstance().get("cus").execute();
//		s.cus();
////		ISchedule schedule2 = new ScheduleTest().createSchedule();
////		System.out.println(schedule2.getId());
////		ScheduleManager.getInstance().put(schedule2.getId(), schedule);
////		ScheduleManager.getInstance().execute();
//		
//	}
//	
//	private void cus() {
//		System.out.println("开始");
//
//		ISchedule iSchedule = ScheduleManager.getInstance().get("cus");
//		System.out.println(iSchedule);
//
//		String id = "cus";
//		Task r = new Task() {
//			
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void run() {
//				System.out.println("******* 业务代码 *****");
//			}
//		};
//		
////		ScheduleManager.getInstance().get("cus");
//		
////		ScheduleManager.getInstance().addTask(id,r);
//	}
//}
