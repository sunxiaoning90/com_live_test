package com.live.test.javaee.springboot.discovery.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.live.test.javaee.springboot.app.App;

@Component
@RestController
@RequestMapping("zuulTest")
public class ZuulTestController {

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echoByFeign(@PathVariable String str) {
		String r = "*响应来自：" + App.APP_NAME_ALIAS + str;
		return r;
	}
}
