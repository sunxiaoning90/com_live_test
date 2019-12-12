package com.live.test.api.core.idcreator.ju;

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
