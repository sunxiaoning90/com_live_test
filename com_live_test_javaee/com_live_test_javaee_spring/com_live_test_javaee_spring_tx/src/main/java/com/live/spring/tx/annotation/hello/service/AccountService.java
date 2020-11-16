package com.live.spring.tx.annotation.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.live.spring.tx.annotation.hello.dao.IAccountDao;

@Service("accountService")
public class AccountService {
	@Autowired
	IAccountDao dao;

	@Transactional
	public boolean transferMomeny(String form, String to, double money) {
		boolean payFlg = dao.payMomeny(form, money);
		if (payFlg) {
			System.out.println("付款成功，账户：" + form + "，付款：" + money);
		}

		boolean reciveFlg = dao.reciveMomeny(to, money);
		if (reciveFlg) {
			System.out.println("收款成功，账户：" + form + "，收款：" + money);
		}

		return payFlg && reciveFlg;
	}

}
