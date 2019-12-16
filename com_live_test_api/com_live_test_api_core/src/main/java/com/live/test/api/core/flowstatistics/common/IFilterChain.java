package com.live.test.api.core.flowstatistics.common;

import com.live.test.api.core.flowstatistics.util.FlatData;

//public interface IFilterChain<S, R> extends IFilter<S, R> {
public interface IFilterChain<S, R> extends IHandleable<S, R> {
	IFilter<S, R> getHeadFilter();

	void setHeadFilter(IFilter<S, R> headFilter);

	FlatData getData();

	void setData(FlatData data);

	void resetData();
}
