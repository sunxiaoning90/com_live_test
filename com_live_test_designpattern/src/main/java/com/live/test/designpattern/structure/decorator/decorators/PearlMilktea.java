package com.live.test.designpattern.structure.decorator.decorators;

import com.live.test.designpattern.structure.decorator.IMilktea;

public class PearlMilktea implements IMilktea{

	IMilktea m;
	
	public PearlMilktea(IMilktea m) {
		super();
		this.m = m;
	}

	@Override
	public void show() {
		System.out.println("加冰");
		m.show();
	}

}
