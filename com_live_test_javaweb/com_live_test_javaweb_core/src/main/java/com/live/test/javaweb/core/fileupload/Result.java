package com.live.test.javaweb.core.fileupload;

/**
 * @author live
 * @2019年12月16日 @下午5:34:47
 */
public class Result {
	private int code;
	private Object data;

	public Result(int code, Object data) {
		this();
		this.code = code;
		this.data = data;
	}

	public Result(Object data) {
		this();
		this.code = 0;
		this.data = data;
	}

	public Result() {
		super();
		this.code = -1;
	}

	public boolean isOk() {
		return code == 0;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(Class<T> t) {
		return (T) data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
