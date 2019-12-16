package com.live.test.api.core.flowstatistics.util;

import java.util.Random;

public class SecondFilter extends Filter {

	public SecondFilter() {
		super("seconds");
	}
	@Override
	public Object parse(Integer value) {
		if (value >= 300)
			return "300+"; 
		if (value >= 120)
			return "120-299";
		if (value >= 60)
			return "60-119";
		if (value >= 10)
			return "10-59";
		return "0-9";
	};
	
	public static void main(String[] args) {
		FlatData pojo = new FlatData(); 
		pojo.set("totalNumber", 23);
		pojo.set("项目名称", "测试");
		pojo.set("开始时间", "2018-09-04 10:00:00");
		pojo.increase("status.busy");
		pojo.increase("status.connected");
		pojo.increase("status.connected");
		pojo.increase("status.connected");
		pojo.increase("status.connected");
		pojo.increase("status.outservice");

		Random random = new Random();
		SecondFilter sf = new SecondFilter();
		for (int i = 0; i < 100; i++) {
			sf.parse(pojo, random.nextInt() % 500);
		}
		
		System.out.println(pojo.getFlatJson());
		System.out.println(pojo.getJson());
		
	}
}
