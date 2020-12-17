package com.live.test.javase.core.sizeOfAObject;

import java.lang.instrument.Instrumentation;

public class ObjectSizeUtil {
	private static Instrumentation instrumentation;

	public static void premain(String args, Instrumentation inst) {
		instrumentation = inst;
	}

	public static long getObjectSize(Object o) {
		return instrumentation.getObjectSize(o);
	}
	
	public static void main(String[] args) {
		ObjectSizeUtil.getObjectSize(new Object());
	}
}
