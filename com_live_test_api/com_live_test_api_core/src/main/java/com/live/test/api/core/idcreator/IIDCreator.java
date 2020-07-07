package com.live.test.api.core.idcreator;

/**
 * id生成器
 * @author live
 * @2019年12月12日 @下午2:52:14
 */
public interface IIDCreator {
	
	/**
	 * 获取下一个id
	 * @return
	 */
	String getNext();
	
	/**
	 * 获取当前id
	 * @return
	 */
	String getLast();
}
