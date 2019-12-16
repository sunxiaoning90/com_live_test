package com.live.test.api.core.flowstatistics.smsStatistics.filter;

import java.util.Date;

import com.live.test.api.core.flowstatistics.common.IFilter;
import com.live.test.api.core.flowstatistics.util.Filter;
import com.live.test.api.core.flowstatistics.util.FlatData;
import com.live.test.api.core.povo.entity.Entity;

public class SmsCreatorIdFilter extends Filter implements IFilter<Entity, FlatData>{
	private FlatData data;
	IFilter<Entity, FlatData> next;
	private SmsCreatorIdFilter() {
		super("creatorId");
	}

	public SmsCreatorIdFilter(FlatData data) {
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
		return "座席id-"+value;
	};
	
	@Override
	public FlatData handle(Entity source) throws Exception {
		String id = (String) source.getValue("creatorId");//"AU-001";
		this.parse(data,id);
		
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
