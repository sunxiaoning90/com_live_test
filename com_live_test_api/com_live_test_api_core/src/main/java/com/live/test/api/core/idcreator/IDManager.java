package com.live.test.api.core.idcreator;

import com.live.test.api.core.idcreator.snowflake.SnowFlakeGeneratorAdapter;

/**
 * id管理器,用于生成全局唯一id
 * 
 * @author live
 * @2019年12月16日 @下午2:59:37
 */
public class IDManager {
	IIDCreator idCreator;

	private IDManager() {
//		idCreator = new DateAndUuidIDCreator();
		idCreator = new SnowFlakeGeneratorAdapter();
	}

	public static IDManager getInstance() {
		return SingleHolder.idManager;
	}

	private static class SingleHolder {
		private static final IDManager idManager = new IDManager();
	}

	public String getNext() {
		return this.idCreator.getNext();
	}

	public String getLast() {
		return this.idCreator.getLast();
	}
}
