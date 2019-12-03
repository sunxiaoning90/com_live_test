package com.live.test.designpattern.create.singleton.staticinnerclass.pro;

import org.junit.Test;

//import com.alibaba.fastjson.JSONObject;

/**
 * 路由访问记录 测试
 * @author live
 * @2019年12月3日 @上午11:01:56
 */
public class RouterAccessManagerTest {
	@Test
	public void t1() {
		String key = "containerServer.ContainerDomainService.getContainerDomains.SXN";
		RouterAccessManager.getInstance().increment(key);
		//logger.error("\n>>>routerAccess:{}", RouterAccessManager.getInstance().toJson());
	}
}
