package com.live.test.api.core.flowstatistics.smsStatistics;

import com.live.test.api.core.flowstatistics.common.DomainST;
import com.live.test.api.core.flowstatistics.common.IFilterChain;
import com.live.test.api.core.flowstatistics.smsStatistics.filterChain.SmsCreatorFilterChain;
import com.live.test.api.core.flowstatistics.util.FlatData;
import com.live.test.api.core.povo.entity.Entity;

public class SmsStatisticsService {
	SmsStatisticsService(){
		
	}
	
	public  void smsCreatorST(String domain, Entity entity) {
		SmsCreatorFilterChain chain = (SmsCreatorFilterChain) DomainST.getInstance().getSTManager(domain).getFilterChain(SmsStatisticsManager.ST_SMS_CREATOR);
//		Object source = entity;//new Object();
		FlatData data = null;
		try {
			data = chain.handle(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(data);
		System.out.println(data.getFlatJson());
	}
	
	@SuppressWarnings("unchecked")
	public  void smsStatusST(String domain, Entity entity) {
		@SuppressWarnings("rawtypes")
		IFilterChain chain = (IFilterChain) DomainST.getInstance().getSTManager(domain).getFilterChain(SmsStatisticsManager.ST_SMS_STATUS);
		
		try {
			chain.handle(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		FlatData data = null;
		try {
			data = (FlatData) chain.handle(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(data);
		System.out.println(data.getFlatJson());
	}
}
