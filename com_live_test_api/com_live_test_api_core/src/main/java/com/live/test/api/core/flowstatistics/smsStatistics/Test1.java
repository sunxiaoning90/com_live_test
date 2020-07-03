package com.live.test.api.core.flowstatistics.smsStatistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.live.test.api.core.flowstatistics.common.DomainST;
import com.live.test.api.core.flowstatistics.common.IFilterChain;
import com.live.test.api.core.flowstatistics.smsStatistics.filterChain.SmsCreatorFilterChain;
import com.live.test.api.core.flowstatistics.smsStatistics.filterChain.SmsStatusFilterChain;
import com.live.test.api.core.flowstatistics.util.FlatData;
import com.live.test.api.core.povo.entity.Entity;
import com.live.test.api.core.povo.entity.MongoEntity;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class Test1 {
	//@BeforeEach
	
	public void before() {
		initCreatorST();
		initStatusST();
	}
	
	private void initCreatorST() {
		
		FlatData data = SmsStatisticsDataFactroy.build(SmsStatisticsManager.ST_SMS_CREATOR);
		SmsCreatorFilterChain smsCreatorFilterChain = new SmsCreatorFilterChain(data);
		
		String domain = "spzc";
		DomainST.getInstance().getSTManager(domain).putFilterChain(SmsStatisticsManager.ST_SMS_CREATOR, smsCreatorFilterChain);
	}
	
	private void initStatusST() {
		
		FlatData data = new FlatData();
		data.set("startTime", new Date());
		SmsStatusFilterChain smsStatusFilterChain = new SmsStatusFilterChain(data);
		
		String domain = "spzc";
		DomainST.getInstance().getSTManager(domain).putFilterChain(SmsStatisticsManager.ST_SMS_STATUS, smsStatusFilterChain);
	}
	
	@Test
		public void testCreatorST() {
		String domain = "spzc";
		Entity entity = new MongoEntity("SMS");
		entity.setValue("creator", "A");
		entity.setValue("creatorId", "123");
		new SmsStatisticsService().smsCreatorST(domain,entity);
	}
	
	@Test
	public void testStatusST() {
		String domain = "spzc";
		Entity entity = new MongoEntity("SMS");
		entity.setValue("creator", "A");
		entity.setValue("creatorId", "123");
		entity.setValue("status", "success");
		SmsStatisticsService s = new SmsStatisticsService();
		s.smsStatusST(domain,entity);
		
		Entity entity2 = new MongoEntity("SMS");
		entity2.setValue("creator", "B");
		entity2.setValue("creatorId", "456");
		entity2.setValue("status", "failure");
		
		s.smsStatusST(domain,entity2);
		
		testStatusSTClean();
		
		Entity entity3 = new MongoEntity("SMS");
		entity3.setValue("creator", "C");
		entity3.setValue("creatorId", "789");
		entity3.setValue("status", "unknow");
		s.smsStatusST(domain,entity3);
		
	}
	
	@Test
	public void testStatusSTClean() {
		String domain = "spzc";
		IFilterChain chain = (IFilterChain) DomainST.getInstance().getSTManager(domain).getFilterChain(SmsStatisticsManager.ST_SMS_STATUS);
		chain.resetData();
	}
	
	
	@Ignore
		@Test()
		public void test1() {
		List list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(2);
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	@Test()
	public void testSingleton() {
		DomainST st = DomainST.getInstance();
		DomainST st2 = DomainST.getInstance();
		
		System.out.println(st == st2);
		
	}
	
}
