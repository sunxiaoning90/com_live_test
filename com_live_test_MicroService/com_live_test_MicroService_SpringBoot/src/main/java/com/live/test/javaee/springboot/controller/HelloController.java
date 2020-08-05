package com.live.test.javaee.springboot.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@Controller
public class HelloController {

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello l";
	}
}
