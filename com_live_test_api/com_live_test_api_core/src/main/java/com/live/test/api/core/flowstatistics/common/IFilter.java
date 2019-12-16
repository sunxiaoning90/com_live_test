package com.live.test.api.core.flowstatistics.common;

public interface IFilter<S, R> extends IHandleable<S, R> {
	R getData();

	void setData(R data);

	void setNext(IFilter<S, R> filter);

	IFilter<S, R> getNext();

}
