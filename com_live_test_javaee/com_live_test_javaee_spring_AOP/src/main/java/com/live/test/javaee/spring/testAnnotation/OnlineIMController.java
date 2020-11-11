package com.live.test.javaee.spring.testAnnotation;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

//@Component
public class OnlineIMController{
	
	@PostConstruct
	void postC(){
	}
	
}
