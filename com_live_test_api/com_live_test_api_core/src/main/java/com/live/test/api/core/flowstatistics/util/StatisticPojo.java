package com.live.test.api.core.flowstatistics.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class StatisticPojo {
	transient private Map<String, Field> fieldMap = new HashMap<String, Field>();
	transient private Map<String, Object> objectMap = new HashMap<String, Object>();
	private void putField(String name, Field field, Map<String, Field> map, Map<String, Object> objMap) {
		if (name == null || name.equals("")) {
			map.put(field.getName(), field);
			objMap.put(field.getName(), this);
		} else {
			map.put(name.concat(".").concat(field.getName()), field);
			objMap.put(name.concat(".").concat(field.getName()), this);
		}
	}
	
	public StatisticPojo initialize() {
		return initializeZero("", fieldMap, objectMap);
	}
	public StatisticPojo initializeZero(String name, Map<String, Field> map, Map<String, Object> objMap) {
		Class<?> clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (field.getType().equals(Integer.class)) {
					field.set(this, 0);
					putField(name, field, map, objMap);
				} else if (StatisticPojo.class.isAssignableFrom(field.getType())) {
					StatisticPojo sub = (StatisticPojo)(field.getType().newInstance());
					if (name == null || name.equals("")) {
						field.set(this, sub.initializeZero(field.getName(), map, objMap));
					} else {
						field.set(this, sub.initializeZero(name.concat(".").concat(field.getName()), map, objMap));
					}
				} 
				System.out.println(field.getName()+": "+field.get(this).toString());
			} catch (Exception e) {
			}
		}
		return this;
	}
	public Integer value(String node) {
		Field field = fieldMap.get(node);
		Object obj = objectMap.get(node);
		if (field == null || obj == null)
			return null;
		try {
			return (Integer)(field.get(obj));
		} catch (Exception e) {
		}
		return null;
	}
	public void exchange(String node, int num) {
		Field field = fieldMap.get(node);
		Object obj = objectMap.get(node);
		if (field == null || obj == null)
			return;
		try {
			field.setAccessible(true);
			field.set(obj, (Integer)(field.get(obj))+num);
		} catch (Exception e) {
		}
	}
	public void increase(String node) {
		exchange(node, 1);
	}
	public void decrease(String node) {
		exchange(node, -1);
	}
}
