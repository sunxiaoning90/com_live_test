package com.live.test.designpattern.structure.decorator;

import com.live.test.designpattern.structure.decorator.decorators.IceMilktea;
import com.live.test.designpattern.structure.decorator.decorators.PearlMilktea;

public class Client {
		public static void main(String[] args) {
			System.out.println("普通奶茶:");
			IMilktea m = new Milktea();
			m.show();
			
			System.out.println("奶茶,加冰");
			IMilktea iceM = new IceMilktea(new Milktea());
			iceM.show();
			
			System.out.println("奶茶,加冰,加珍珠");
			IMilktea iceAndPearlM = new PearlMilktea(new IceMilktea(m));
			iceAndPearlM.show();
			
		}
}
