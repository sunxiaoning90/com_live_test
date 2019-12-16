package com.live.test.api.core.flowstatistics.smsStatistics.filter;

import java.util.Date;

import com.live.test.api.core.flowstatistics.common.IFilter;
import com.live.test.api.core.flowstatistics.util.Filter;
import com.live.test.api.core.flowstatistics.util.FlatData;
import com.live.test.api.core.povo.entity.Entity;

public class SmsStatusFilter extends Filter implements IFilter<Entity, FlatData> {
	private FlatData data;
	IFilter<Entity, FlatData> next;

	private SmsStatusFilter() {
		super("status");
	}

	public SmsStatusFilter(FlatData data) {
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
		return "状态-" + value;
	};

	@Override
	public FlatData handle(Entity source) throws Exception {
		String status = (String) source.getValue("status");// "AU-001";
		// FlatData d = (FlatData)data;
		this.parse(data, status);

		data.set("modifiyTime", new Date());
		if (this.getNext() != null) {
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
