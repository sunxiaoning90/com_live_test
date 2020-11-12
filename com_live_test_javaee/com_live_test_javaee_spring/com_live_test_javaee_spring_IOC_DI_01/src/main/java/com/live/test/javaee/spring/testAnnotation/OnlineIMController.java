package com.live.test.javaee.spring.testAnnotation;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

@Controller
public class OnlineIMController{
	
	@PostConstruct
	void postC(){
	}
	
}
