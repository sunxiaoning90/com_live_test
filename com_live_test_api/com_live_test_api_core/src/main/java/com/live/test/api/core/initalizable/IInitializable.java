package com.live.test.api.core.initalizable;

/**
 * 接口，
 * 如果一个类需要初始成功后才能使用，则需要实现该接口
 * @author live
 * Jan 11, 2019 3:16:50 PM
 */
public interface IInitializable {
	boolean isAvailable();

	void setAvailable(boolean available);

	String getStatus();

	void setStatus(String status);

	void setUninitialized();

	void setIninializing();

	void setInitialized();
}
