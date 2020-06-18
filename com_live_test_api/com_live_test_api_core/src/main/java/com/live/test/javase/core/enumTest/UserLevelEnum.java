package com.live.test.javase.core.enumTest;

public enum UserLevelEnum {
	Normal("normal"), Vip("vip");

	private String type;

//	public UserLevelEnum(String type){ //只允许为private，不允许为public Illegal modifier for the enum constructor; only private is permitted.
	UserLevelEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	/**
	 * 可以重写toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
