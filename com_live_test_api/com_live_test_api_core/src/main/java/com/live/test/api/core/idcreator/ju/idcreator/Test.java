package com.live.test.api.core.idcreator.ju.idcreator;

public class Test {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(IDManager.getInstance().getNext());
		}
	}
}
