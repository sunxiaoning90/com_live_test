package com.live.spring.tx.XML.hello.service;

import com.live.spring.tx.XML.hello.dao.IAccountDao;

public class AccountService {
	
	IAccountDao dao;
	public IAccountDao getDao() {
		return dao;
	}
	public void setDao(IAccountDao dao) {
		this.dao = dao;
	}

	//	@Transactional 改用xml配置方式
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
