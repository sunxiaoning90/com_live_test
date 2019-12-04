package com.live.test.api.core.schedule.schedule.impl.qz;

import java.io.Serializable;

//public abstract class Task implements Runnable,Serializable{
public interface Task extends Runnable, Serializable {
//	static final long serialVersionUID = -829829062149259933L;

	String getId();

//	@Override 
//	public void run() {
//  
//  }

}
