package com.live.autowire;

public class UserService {
	private UserDao userDao;

	public UserService() {

	}

	public UserService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	public void saveUser() {
		userDao.saveUser();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
