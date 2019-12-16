package com.live.test.api.core.flowstatistics.common;

public interface IHandleable<S, R> {
	/**
	 * 处理
	 * 
	 * @param source
	 * @return
	 * @throws Exception
	 */
	R handle(S source) throws Exception;
}
