package com.live.test.javase.core.enumTest;

public enum UserLevelEnum {
	Normal("normal"),Vip("vip");
	
	String type;
	
	UserLevelEnum(String type){
		this.type = type;
	}
}
