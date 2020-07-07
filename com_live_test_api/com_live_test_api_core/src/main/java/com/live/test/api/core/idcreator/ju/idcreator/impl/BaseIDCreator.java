package com.live.test.api.core.idcreator.ju.idcreator.impl;

import com.live.test.api.core.idcreator.IIDCreator;

/**
 * ID生成器基础类,具体的创建器若继承本类,只需要重写 doNext()即可
 * 抽象类
 * @author live
 * @2019年12月16日 @下午3:09:00
 */
public abstract class BaseIDCreator implements IIDCreator{
	private String last;
	private String next;
	
	abstract public String doNext();
	
	public String getNext() {
		this.setNext(this.doNext());
		return next;
	}
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}
	public void setNext(String next) {
		this.next = next;
		this.setLast(next);
	}
}
