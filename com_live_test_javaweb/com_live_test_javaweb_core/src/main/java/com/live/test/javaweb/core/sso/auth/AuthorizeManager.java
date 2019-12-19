package com.live.test.javaweb.core.sso.auth;

/**
 * 授权管理类
 * 包括域\用户\登录\用户授权数控制等
 * @author live
 * @2019年12月3日 @下午5:36:40
 */
public class AuthorizeManager {
	/**
	 * 超级域
	 */
	public static final String DOMAIN_SUPER = "Super";

	/**
	 * 长用户名分割符 user@domain
	 */
	public static final String USERNAME_LONG_SEPARATOR = "@";

	/**
	 * 短用户名,System
	 */
	public static final String USERNAME_SHORT_SYSTEM = "System";

	private static final String[] WHITELIST_DOMAIN = new String[] { DOMAIN_SUPER };
	private static final String[] WHITELIST_USER = new String[] { USERNAME_SHORT_SYSTEM };

	/**
	 * 检查用户登录数是否大于授权数,判断是否能允许继续登录
	 * 
	 * @param username
	 * @return
	 */
	public static boolean checkAuthCount(String username) {
		if (username == null || !username.contains("@")) {
			return false;
		}
		String[] array = username.split("@");
		String shortUsername = array[0];
		String domianName = array[1];

		// 放行白名单内的域
		for (int i = 0; i < WHITELIST_DOMAIN.length; i++) {
			if (domianName.equals(WHITELIST_DOMAIN[i])) {
				return true;
			}
		}

		// 放行白名单内的用户(短用户名:@前的)
		for (int i = 0; i < WHITELIST_USER.length; i++) {
			if (shortUsername.equals(WHITELIST_USER[i])) {
				return true;
			}
		}

//		Domain domain = DomainManager.getInstance().get(domianName);
//		if (domain == null || domain.getUserManager() == null) {
//			System.out.println("domain为null 或userManager 为null");
//			return false;
//		}

//		Integer authorizeCount = AuthorizeManager.getInstance().getAuthorizeCount(domianId);
		Integer authorizeCount = 0;//domain.getAuthCount();// 授权数
		long loginCount = 0;//domain.getUserManager().getOnlineNumbersIgnoreSystem();// .getOnlineNumbers();// 已登录数
		return loginCount < authorizeCount;
	}

	/**
	 * SAAS平台(多租户)新需求:如何快速登录超级域?即仅输入短用户名即可登录到超级域
	 * 实现:若用户以短用户登录,约定该用户属与超级域,在登录接口需要对短用户名加工: 原始短用户名:zhangsan 加工后:zhangsan@Super
	 * 
	 * @param username
	 * @return
	 */
	public static String appendSuperDomain(String username) {
		if (username == null) {
			return null;
		}
		if (username.contains(AuthorizeManager.USERNAME_LONG_SEPARATOR)) {
			return username;
		}
		return username.concat(AuthorizeManager.USERNAME_LONG_SEPARATOR).concat(AuthorizeManager.DOMAIN_SUPER);
	}
}
