package com.live.test.api.core.flowstatistics.smsStatistics;

import java.util.Date;

import com.live.test.api.core.flowstatistics.util.FlatData;


public class SmsStatisticsDataFactroy {
	public static FlatData build(String type) {

		switch (type) {
		case SmsStatisticsManager.ST_SMS_CREATOR:
			return buildCreatorData();

		case SmsStatisticsManager.ST_SMS_STATUS:
			return buildStatusData();

		default:
			break;
		}
		return null;

	}

	private static FlatData buildStatusData() {
		FlatData data = new FlatData();
		data.set("startTime", new Date());
		return data;
	}

	private static FlatData buildCreatorData() {
		FlatData data = new FlatData();
		data.set("startTime", new Date());
		return data;
	}
}
