package com.live2.annotation.user.dao;

import org.springframework.stereotype.Repository;

import com.live2.annotation.user.model.User;

@Repository
public class UserDao implements IUserDao {

	public UserDao() {
		System.out.println("This is UserDao Contructor!");
	}

	@Override
	public boolean saveUser(User user) {
		System.out.println("dao:saveUser!");
		return true;
	}

}
