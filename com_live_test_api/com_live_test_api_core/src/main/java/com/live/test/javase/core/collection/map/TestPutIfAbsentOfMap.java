package com.live.test.javase.core.collection.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TestPutIfAbsentOfMap {
	public static void main(String[] args) {
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		System.out.println(map.get("k1"));
		
		System.out.println("***********put 和 putIfAbsen");
//		put 1）若key有旧值，覆盖旧值 2） 返回的是旧值（无旧值则返回null）
		String v = map.put("k1", "A");
		System.out.println(v);
		System.out.println(map.get("k1"));
		
//		putIfAbsent：1）若key有旧值，不会覆盖旧值 2） 返回的是旧值（无旧值则返回null）
		v = map.putIfAbsent("k1", "B");
		System.out.println(v);
		System.out.println(map.get("k1"));
		
		
		System.out.println("***********compute 和 computeIfAbsent");
		BiFunction<Object, Object, String> f1 = new BiFunction<Object, Object, String>() {

			@Override
			public String apply(Object t, Object u) {
				return "C";
			}
		};
		
//		compute：1）若key有旧值，覆盖旧值 2） 返回的是 当前key的实际值
		v = map.compute("k1", f1);
		System.out.println(v);
		System.out.println(map.get("k1"));
		System.out.println("***********");
		
		map.clear();
//		putIfAbsent：1）若key有旧值，不会覆盖旧值 2） 返回的当前key的实际值
		Function<Object, String> f2 = new Function<Object, String>() {

			@Override
			public String apply(Object t) {
				return "D";
			}
		};
		
		v = map.computeIfAbsent("k1", f2);
		System.out.println(v);
		System.out.println(map.get("k1"));
	}
}
