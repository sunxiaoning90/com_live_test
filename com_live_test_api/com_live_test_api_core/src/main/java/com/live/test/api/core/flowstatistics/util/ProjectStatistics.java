package com.live.test.api.core.flowstatistics.util;

import com.google.gson.Gson;

public class ProjectStatistics extends StatisticPojo {
	Integer totalNumber = 100;
	Status status;
//	Map<String, Integer> tags = new HashMap<String, Integer>() {{
//		put("有房", 1);
//	}};
//	
	public static void main(String[] args) {
		StatisticPojo pojo = new ProjectStatistics().initialize(); 
		Gson gson = new Gson();
		pojo.increase("status.busy");
		pojo.exchange("totalNumber", 23);
		System.out.println(pojo.value("status.busy"));
		System.out.println(gson.toJson(pojo));
		
	}
}
