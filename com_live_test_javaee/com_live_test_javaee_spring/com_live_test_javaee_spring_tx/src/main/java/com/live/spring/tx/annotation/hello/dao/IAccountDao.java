package com.live.spring.tx.annotation.hello.dao;

public interface IAccountDao {
	/**
	 * 付款
	 * @return boolean
	 */
	boolean payMomeny(String accountId, double money);

	/**
	 * 收款
	 * @return boolean
	 */
	boolean reciveMomeny(String accountId, double money);
}
