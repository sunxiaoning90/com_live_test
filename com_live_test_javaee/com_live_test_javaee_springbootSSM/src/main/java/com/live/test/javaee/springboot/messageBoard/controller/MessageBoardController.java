package com.live.test.javaee.springboot.messageBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.live.test.javaee.springboot.messageBoard.po.MessageBoard;
import com.live.test.javaee.springboot.service.MessageBoardService;

@ComponentScan
@Controller
@RequestMapping(value="/messageBoardForIM")
public class MessageBoardController {
	
	@Autowired
	MessageBoardService service;

//	@RequestMapping("/messageBoard")
	@PostMapping(value="messageBoard")
	@ResponseBody
	public String createMessageBoard(@RequestParam(value="time1") String time ,MessageBoard messageBoard,HttpServletRequest req) {
		System.out.println(time);
		
		System.out.println(req);
		System.out.println(req.getParameter("content"));
		
		System.out.println(messageBoard);
		System.out.println(messageBoard.getContent());
		System.out.println(messageBoard.getTime());
		
		service.save(messageBoard);
		return "test";
	}
}
