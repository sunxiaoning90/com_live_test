package com.live.test.javase.core.OOM;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

/**
 * 
 * @author live
 * 
 * 參考：https://mp.weixin.qq.com/s/Pcb-4oE_gjQNz2HVprYfKw
 */
public class TestOOM {

	@Test
	public static void testPergemOutOfMemory1(){ 
		   //方法一失败 
		    List<String> list = new ArrayList<String>(); 
		 
		   while(true){ 
		      list.add(UUID.randomUUID().toString().intern()); 
		   } 
		} 
	
}
