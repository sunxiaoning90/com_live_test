package com.live.test.javase.core.collection.testList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;

public class TestList {
	public static void main(String[] args) {
		System.out.println(1);
		test();
	}

	/**
	 * <pre>
	 * com.live.test.javase.core.collection.testList.Persion@1794d431
		0
		com.live.test.javase.core.collection.testList.Persion@42e26948
		1
		com.live.test.javase.core.collection.testList.Persion@57baeedf
		2
	 * </pre>
	 */
	public static void testListAdd() {
		List<Persion> list = new ArrayList<Persion>();

		Persion h = null;
		for (int i = 0; i < 3; i++) {
//			if(h==null) {
			h = new Persion();
//			}
			h.setName(i + "");
			list.add(h);
		}

		list.forEach((e) -> {
			System.out.println(e);
			System.out.println(e.getName());
		});
	}

	public static void test() {
		List list = new ArrayList<Object>();
		list.add(null);

//		Map map = new ConcurrentHashMap(); k v均不允许为null
		Map map = new HashMap(); // k v 允许为null
		map.put(null, "");
		map.put(1, null);

		Set set = new HashSet();
		set.add(null);
		System.out.println("end");
		System.out.println("end");
	}
}
