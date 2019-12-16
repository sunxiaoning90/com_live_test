package com.live.test.api.core.flowstatistics.util;

public abstract class Filter {
	private String node;
	public Filter(String node) {
		this.node = node;
	}
	public Filter() {
		this.node = null;
	}
	public void parse (FlatData pojo,  Object value) {
		Object subnode = parse(value);
		if (subnode == null)
			return;
		pojo.increase(subnode, node);
	}
	private Object parse(Object value){
		Class<?> type = value.getClass();
		if (type.equals(Integer.class))
			return parse((Integer) value);
		if (type.equals(Long.class))
			return parse((Long) value);
		if (type.equals(Double.class))
			return parse((Double) value);
		if (type.equals(String.class))
			return parse((String) value);
		return null;
	}

	protected Object parse(Integer value) {
		return null;
	}
	protected Object parse(Long value) {
		return null;
	}
	protected Object parse(Double value) {
		return null;
	}
	protected Object parse(String value) {
		return null;
	}
	
}
