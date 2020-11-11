package com.live.aop.user.service;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {

	public UserService() {
		System.out.println("This is UserService Contructor!");
	}

	@Override
	public boolean saveUser() {
		System.out.println("service:saveUser!");
//		int a = 1/0;
		return false;
	}

	@Override
	public boolean deleteUserById(String id) {
		System.out.println("service:deleteUserById!");
		return false;
	}

}
