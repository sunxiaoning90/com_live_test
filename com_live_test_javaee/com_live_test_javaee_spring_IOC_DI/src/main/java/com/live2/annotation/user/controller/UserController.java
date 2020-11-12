package com.live2.annotation.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.live2.annotation.user.model.User;
import com.live2.annotation.user.service.IUserService;

//@Controller(value="userController")
//@Controller("userController")
@Controller
public class UserController {
	@Autowired
	IUserService userService;

	public void saveUser() {
		User user = new User();
		boolean flg = userService.saveUser(user);
		System.out.println("controller:saveUser!" + flg);
	}
}
