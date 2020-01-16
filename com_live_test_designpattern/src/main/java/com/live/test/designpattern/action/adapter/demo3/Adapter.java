package com.live.test.designpattern.action.adapter.demo3;

public class Adapter implements ITarget {
	BMWParts parts = new BMWParts();
	@Override
	public void show() {
		parts.bmwEngine();
		parts.bmwWheel();
	}

}
