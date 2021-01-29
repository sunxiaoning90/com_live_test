package com.live.test.javase.core.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestAll {
	
	public static void main(String[] args) {
		System.out.println(1);
	}

	public static void testNull() {
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
