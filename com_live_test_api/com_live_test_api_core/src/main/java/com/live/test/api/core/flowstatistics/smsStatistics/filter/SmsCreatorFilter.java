package com.live.test.api.core.flowstatistics.smsStatistics.filter;

import java.util.Date;

import com.live.test.api.core.flowstatistics.common.IFilter;
import com.live.test.api.core.flowstatistics.util.Filter;
import com.live.test.api.core.flowstatistics.util.FlatData;
import com.live.test.api.core.povo.entity.Entity;

public class SmsCreatorFilter extends Filter implements IFilter<Entity, FlatData>{
	private FlatData data;
	private IFilter<Entity, FlatData> next;
	private SmsCreatorFilter() {
		super("creator");
	}

	public SmsCreatorFilter(FlatData data) {
		this();
		this.setData(data);
	}
	
	public FlatData getData() {
		return data;
	}

	public void setData(FlatData data) {
		this.data = data;
	}

	@Override
	public Object parse(String value) {
		return "座席"+value;
	};
	
	@Override
	public FlatData handle(Entity source) throws Exception {
		String name = (String) source.getValue("creator");//"admin";
		this.parse(data,name);
		data.set("modifiyTime", new Date());
		
		if(this.getNext() != null) {
			return getNext().handle(source);
		}
		return data;
	}

	@Override
	public void setNext(IFilter<Entity, FlatData> filter) {
		this.next = filter;
	}

	@Override
	public IFilter<Entity, FlatData> getNext() {
		return this.next;
	}
}
