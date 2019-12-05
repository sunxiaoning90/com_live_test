package com.live.test.api.core.schedule.quartz.factory;

import java.util.Date;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class TriggerFactory {

	public static Trigger buildScheduler() {
		return buildScheduler(0, 0, 0, 0);
	}

	public static Trigger buildScheduler(long delay, int repeatCount, int intervalInSeconds, long endAt) {
		// 触发器
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		// triggerBuilder.withIdentity("trigger1", "group1");

		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule();
		// triggerBuilder.startNow();//零延时
		if (delay > 0) {
			triggerBuilder.startAt(new Date(System.currentTimeMillis() + delay));
		}
		if (repeatCount > 0) {
			schedBuilder.withRepeatCount(repeatCount);// 重复次数
		}
		if (intervalInSeconds > 0) {
			schedBuilder.withIntervalInSeconds(intervalInSeconds);// 时间间隔
		}
		triggerBuilder.withSchedule(schedBuilder);
		if (endAt > 0) {
			triggerBuilder.endAt(new Date(System.currentTimeMillis() + endAt));// 设置停止时间
		}
		Trigger trigger = triggerBuilder.build();
		return trigger;
	}
}
