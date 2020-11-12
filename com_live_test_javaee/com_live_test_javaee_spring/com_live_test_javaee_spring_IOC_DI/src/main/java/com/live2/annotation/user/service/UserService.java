package com.live2.annotation.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.live2.annotation.user.dao.IUserDao;
import com.live2.annotation.user.model.User;

@Service
public class UserService implements IUserService {

	@Autowired
	IUserDao userDao;

	public UserService() {
		System.out.println("This is UserService Contructor!");
	}

	@Override
	public boolean saveUser(User user) {
		System.out.println("service:saveUser!");
		return userDao.saveUser(user);
	}
}
