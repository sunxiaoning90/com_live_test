package com.live.test.api.core.initalizable;

/**
 * 实现类，该类则具有以下功能：设置状态为“未初始化”、“初始化中”、“初始化完毕”;判断对象是否可用，需要调available()
 * @author live
 * Jan 11, 2019 3:31:50 PM
 */
public class NormalInitialize implements IInitializable{

	private static final String STATUS_UNINITIALIZED = "uninitialized";// 状态，未初始化
	private static final String STATUS_ININIALIZING = "ininializing";// 初始化中
	private static final String STATUS_INITIALIZED = "initialized";// 初始化完毕

	private String status = STATUS_UNINITIALIZED;
	protected boolean available = false;

	public String getStatus() {
		return status;
	}

	public void setUninitialized() {
		setStatus(STATUS_UNINITIALIZED);
	}

	public void setIninializing() {
		setStatus(STATUS_ININIALIZING);
	}

	public void setInitialized() {
		setStatus(STATUS_INITIALIZED);
	}

	public void setStatus(String status) {
		this.status = status;
		if (!STATUS_INITIALIZED.equals(this.getStatus())) {
			this.setAvailable(false);
		} else {
			this.setAvailable(true);
		}
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
