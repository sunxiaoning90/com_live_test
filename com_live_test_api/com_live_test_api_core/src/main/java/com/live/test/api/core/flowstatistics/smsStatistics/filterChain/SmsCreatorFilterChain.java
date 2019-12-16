package com.live.test.api.core.flowstatistics.smsStatistics.filterChain;

import com.live.test.api.core.flowstatistics.common.IFilter;
import com.live.test.api.core.flowstatistics.common.IFilterChain;
import com.live.test.api.core.flowstatistics.smsStatistics.SmsStatisticsDataFactroy;
import com.live.test.api.core.flowstatistics.smsStatistics.SmsStatisticsManager;
import com.live.test.api.core.flowstatistics.smsStatistics.filter.SmsCreatorFilter;
import com.live.test.api.core.flowstatistics.smsStatistics.filter.SmsCreatorIdFilter;
import com.live.test.api.core.flowstatistics.util.FlatData;
import com.live.test.api.core.povo.entity.Entity;

public class SmsCreatorFilterChain implements IFilterChain<Entity, FlatData> {

	private FlatData data;

	private SmsCreatorFilterChain() {
	}

	public SmsCreatorFilterChain(FlatData data) {
		this();
		this.setData(data);

		IFilter<Entity, FlatData> headFilter = new SmsCreatorFilter(this.getData());
		headFilter.setNext(new SmsCreatorIdFilter(data));
		this.setHeadFilter(headFilter);
	}

	private IFilter<Entity, FlatData> headFilter;

	public IFilter<Entity, FlatData> getHeadFilter() {
		return headFilter;
	}

	public void setHeadFilter(IFilter<Entity, FlatData> headFilter) {
		this.headFilter = headFilter;
	}

	public FlatData getData() {
		return data;
	}

	public void setData(FlatData data) {
		this.data = data;
		if (this.getHeadFilter() != null) {
			this.getHeadFilter().setData(data);
		}
	}

	@Override
	public FlatData handle(Entity source) throws Exception {
		if (this.getData() == null) {
			throw new Exception("data为null");
		}
		if (this.getHeadFilter() == null) {
			throw new Exception("headFilter为null");
		}

		return this.getHeadFilter().handle(source);
	}

	@Override
	public void resetData() {
		this.setData(SmsStatisticsDataFactroy.build(SmsStatisticsManager.ST_SMS_CREATOR));
	}

}
