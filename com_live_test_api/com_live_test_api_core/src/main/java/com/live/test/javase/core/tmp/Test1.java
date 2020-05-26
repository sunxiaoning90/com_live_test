package com.live.test.javase.core.tmp;

import java.util.Random;

import org.junit.Test;

public class Test1 {
//	public static void main(String[] args) {
//		short s1 = 1;
//		System.out.println(s1);
////		s1 =s1 + 1;//s1 + 1 运算结果是int,需强转
//		s1 =(short) (s1 + 1);
//		System.out.println(s1);
//		
//		s1+=1;
//		System.out.println(s1);
//	}
	@Test
	public void test1() {
		System.out.println(Integer.MAX_VALUE-45025685);
	}
	
	public static void main(String[] args) {
        final int start = Integer.MAX_VALUE - 100;
        final int end = Integer.MAX_VALUE;
        int count = 0;
        for (int i = start; i <= end; i++)
            count++;
        System.out.println(count);
    }
	private void name() {
		  Random rnd = new Random();
	        boolean toBe = rnd.nextBoolean();
	        Number result = (toBe || !toBe) ?
	                new Integer(3) : new Float(1);
	        System.out.println(result);
	}
	
	@Test
	public void test0925() {
		System.out.println(Byte.MIN_VALUE);
		System.out.println(Byte.MAX_VALUE);
		
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
	}
}
