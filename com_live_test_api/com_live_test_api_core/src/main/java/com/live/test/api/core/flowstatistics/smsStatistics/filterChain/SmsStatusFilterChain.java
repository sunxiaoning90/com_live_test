package com.live.test.api.core.flowstatistics.smsStatistics.filterChain;

import com.live.test.api.core.flowstatistics.common.IFilter;
import com.live.test.api.core.flowstatistics.common.IFilterChain;
import com.live.test.api.core.flowstatistics.smsStatistics.SmsStatisticsDataFactroy;
import com.live.test.api.core.flowstatistics.smsStatistics.SmsStatisticsManager;
import com.live.test.api.core.flowstatistics.smsStatistics.filter.SmsStatusFilter;
import com.live.test.api.core.flowstatistics.util.FlatData;
import com.live.test.api.core.povo.entity.Entity;

public class SmsStatusFilterChain implements IFilterChain<Entity, FlatData> {

	private FlatData data;

	private SmsStatusFilterChain() {
	}

	public SmsStatusFilterChain(FlatData data) {
		this();
		this.setData(data);

		IFilter<Entity, FlatData> headFilter = new SmsStatusFilter(this.getData());
		this.setHeadFilter(headFilter);
	}

	private IFilter<Entity, FlatData> headFilter;

	public IFilter<Entity, FlatData> getHeadFilter() {
		return headFilter;
	}

	public void setHeadFilter(IFilter<Entity, FlatData> headFilter) {
		this.headFilter = headFilter;
	}

	@Override
	public FlatData getData() {
		return data;
	}

	@Override
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
		this.setData(SmsStatisticsDataFactroy.build(SmsStatisticsManager.ST_SMS_STATUS));
	}

}
