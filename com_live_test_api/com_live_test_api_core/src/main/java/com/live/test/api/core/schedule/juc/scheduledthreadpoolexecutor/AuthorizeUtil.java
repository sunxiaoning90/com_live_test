package com.live.test.api.core.schedule.juc.scheduledthreadpoolexecutor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.live.test.api.core.date.ju.DateUtil;

/**
 *  授权工具类
 * @author live
 * @2019年12月3日 @下午5:36:40
 */
public class AuthorizeUtil {
	/**
	 * 默认允许登录的用户授权数
	 */
	public static final int AUTHCOUNT_DEFAULT = 1;
	
	/**
	 * 一.授权数计算规则
	 * 1.判断授权是否过期
	 * 已过期:授权数忽略不计
	 * 未过期:授权数相加(无论授权数是正数还是负数,如果为-1,则为 授权数 + (-1))
	 * 
	 * 2.登录\当前域已登录总数\授权数 三者的关系
	 * 1)用户登录时,需要校验授权数(授权用户总量 > 用户登录数)
	 * 2)用户登录成功后,需要对登录数自增1
	 * 3)用户退出后,需要对登录数自减1
	 * 
	 * 二.授权刷新时机
	 * 1.容器启动后,需要刷新授权数
	 * 2.新增用户授权后,需要刷新授权数
	 * domain 的 授权记录一旦改变,就需要重新计算
	 * 3.每天0点重新计算
	 * 遍历 domainManager,取domain,计算
	 */
	public static Integer getAuthorizeCount(String authHistory) {
		int count = AUTHCOUNT_DEFAULT;
		if (authHistory == null) {
			return count;
		}
		JSONArray array = null;
		try {
			array = JSONArray.parseArray(authHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (array == null) {
			return count;
		}

		String expiresTimeTag = "expiresTime";
		String authCountTag = "authCount";
		for (Object obj : array) {
			JSONObject json = (JSONObject) obj;
			if (json.containsKey(expiresTimeTag)) {
				long expiresTime = json.getLong(expiresTimeTag);
				if (expiresTime < System.currentTimeMillis()) {
					System.out.println("本次授权已过期:授权数忽略不计,总数为:" + count);
					continue;
				}

				if (json.containsKey(authCountTag)) {
					long authCount = json.getIntValue(authCountTag);
					// if(authCount > 0) {}
					count += authCount;
					System.out.println("本次授权未过期,授权数为:" + authCount + ",授权已重新计算,总数为:" + count);
				}

			}
		}
		System.out.println("授权用户总量:" + count);
		return count;
	}

	/**
	 * 刷新当前容器下全部域的授权数
	 */
	public static void refreshAuthCountAll() {
		ScheduledThreadPoolExecutor pool = ScheduledThreadPoolManager.getInstance().getThreadPool(ScheduledThreadPoolManager.THREADPOOL_KEY_REFRESHAUTHCOUNTALL);
//		pool.schedule(new RefreshAuthTask() , delay, TimeUnit.DAYS);
		pool.scheduleAtFixedRate(new RefreshAuthTask(), DateUtil.getNowToZeroMillis(), DateUtil.MILLISECOND_OF_DAY, TimeUnit.MILLISECONDS);
	}
	
}
