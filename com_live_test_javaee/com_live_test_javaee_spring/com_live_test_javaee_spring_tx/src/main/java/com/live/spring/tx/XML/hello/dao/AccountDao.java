package com.live.spring.tx.XML.hello.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.live.spring.tx.annotation.hello.exception.AccountException;

public class AccountDao implements IAccountDao {
	
	JdbcTemplate jt;
	public JdbcTemplate getJt() {
		return jt;
	}
	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}

	@Override
	public boolean payMomeny(String accountId, double money) {
		
		//1、扣款前，需要检查当前余额，若余额不足肯定不允许扣款
		String sql1 = "select balance from account where id=?";
		Double balance = jt.queryForObject(sql1, new Object[] { accountId }, Double.class);
		if (balance < money) {
			throw new AccountException("该账户余额不足，账户：" + accountId + "，余额：" + balance + "，期望支付：" + money);
		}
		
		//2、扣款
		String sql = "update account set balance=balance-? where id=?";
		return jt.update(sql, new Object[] { money, accountId }) == 1;
	}

	@Override
	public boolean reciveMomeny(String accountId, double money) {
		//1、检查被加钱账户是否存在，若不存在，肯定不能转账
		String sql1 = "select count(*) from account where id=? limit 0,1";
		Integer count = jt.queryForObject(sql1, new Object[] { accountId }, Integer.class);
		if (count == 0) {
			throw new AccountException("该账户不存在，账户：" + accountId);
		}
		
		//2、加钱
		String sql = "update account set balance=balance+? where id=?";
		return jt.update(sql, new Object[] { money, accountId }) == 1;
	}

}
