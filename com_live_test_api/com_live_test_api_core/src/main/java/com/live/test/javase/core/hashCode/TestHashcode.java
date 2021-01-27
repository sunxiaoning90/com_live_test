package com.live.test.javase.core.hashCode;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import com.live.test.javase.core.javaBaseType8.Test_char;

public class TestHashcode {

	public static void main(String[] args) {
		System.out.println("ZZ".hashCode());//2880
		
		printSameHashCodeFromString();
		getSameHashCodeFromString(2880);
	}

	public void test1() {
		Person p = new Person("1");
		Person p2 = new Person("1");

		System.out.println(p);
		System.out.println(p2);

		System.out.println(p.hashCode());
		System.out.println(p2.hashCode());

		HashMap<Person, String> map = new HashMap<Person, String>();
		map.put(p, "11");
		System.out.println(map.get(p2));
	}

	public static void printSameHashCodeFromString() {
		String[] c = Test_char.createString2();
		System.out.println(c.length);
		System.out.println(Arrays.toString(c));
		int h;
		for (String s1 : c) {
			for (String s2 : c) {
				if ((h = s1.hashCode()) == s2.hashCode()) {
					System.out.println("s1:" + s1 + " ,s2:" + s2 + ",二者hashCode相同：" + h);
				}
			}
		}
	}

	@Test
	public static void getSameHashCodeFromString(int hashcode) {
		String[] c = Test_char.createString2();
		System.out.println(c.length);
		System.out.println(Arrays.toString(c));
		for (String s1 : c) {
			if (s1.hashCode() == hashcode) {
				
				System.out.println("hashcode:" + s1.hashCode()+",s1:" + s1 + " ,hashCode：" + hashcode);
			}
		}
	}
}
