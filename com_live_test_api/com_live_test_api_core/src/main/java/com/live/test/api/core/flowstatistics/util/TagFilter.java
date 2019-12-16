package com.live.test.api.core.flowstatistics.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class TagFilter extends Filter {
	public TagFilter() {
		super("tags");
	}
	@Override
	protected Object parse(String value) {
		List<String> tags = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(value, " ,");
		while (st.hasMoreTokens()) {
			String tag = st.nextToken().trim();
			if (!tag.equals(""))
				tags.add(tag);
		}
		return tags.toArray(new String[0]);
	}
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
		TagFilter tf = new TagFilter();
		tf.parse(pojo, " 有房 有车 ");
		tf.parse(pojo, " 有房 ");
		tf.parse(pojo, " 有房 已婚 ");
		tf.parse(pojo, " 有房 已婚 有车 ");
		System.out.println(pojo.getFlatJson());
		System.out.println(pojo.getJson());
	}
}
