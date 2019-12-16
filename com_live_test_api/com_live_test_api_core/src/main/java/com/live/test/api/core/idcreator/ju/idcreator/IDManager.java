package com.live.test.api.core.idcreator.ju.idcreator;

import com.live.test.api.core.idcreator.ju.idcreator.impl.DateAndUuidIDCreator;

/**
 * id管理器,用于生成全局唯一id
 * @author live
 * @2019年12月16日 @下午2:59:37
 */
public class IDManager {
	IIDCreator idCreator;
	private IDManager() {
		idCreator = new DateAndUuidIDCreator();
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
}
