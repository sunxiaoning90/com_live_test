package com.live.web.spring.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public void login(String username, String pwd) {
		System.out.println("登陆成功,您的用户名：" + username);
	}

}
