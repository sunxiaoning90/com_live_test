package com.live.test.javaee.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.live.test.javaee.springmvc.po.Evaluate;
import com.live.test.javaee.springmvc.service.EvaluateService;

@Controller
public class EvaluateController {
	
	@Autowired
	EvaluateService evaluateService;
	
	public EvaluateService getEvaluateService() {
		return evaluateService;
	}

	public void setEvaluateService(EvaluateService evaluateService) {
		this.evaluateService = evaluateService;
	}

	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping("getEvaluateById")
	public String getEvaluateById() {
		int id = 1;
		 Evaluate entity = evaluateService.getEvaluateById(id);
		   System.out.println(entity);
		   return "ok";///m/WEB-INF/views/ok.jsp
	}
	
	@RequestMapping("getEvaluateById2")
	public Evaluate getEvaluateById2() {
		int id = 1;
		 Evaluate entity = evaluateService.getEvaluateById(id);
		 entity = new Evaluate(1, "满意", "基本满意");
		   System.out.println(entity);
		   return entity;
	}
	
	/**
	 * springmvc默认没有将json转换成对象的转换器，需要手动添加依赖:com.fasterxml.jackson.core
	 * @return
	 */
	@RequestMapping("getEvaluateById3")
	@ResponseBody
	public Evaluate getEvaluateById3() {
		int id = 1;
		Evaluate entity = evaluateService.getEvaluateById(id);
		entity = new Evaluate(1, "满意", "基本满意");
		System.out.println(entity);
		return entity;
	}
	
	@RequestMapping("getEvaluateById4")
	@ResponseBody
	public List<Evaluate> getEvaluateById4() {
		int id = 1;
		Evaluate entity = evaluateService.getEvaluateById(id);
		entity = new Evaluate(1, "满意", "基本满意");
		System.out.println(entity);
		
		List<Evaluate> list = new ArrayList<Evaluate>(2);
		list.add(entity);
		list.add(entity);
		
		return list;
	}
	
	
	
	@RequestMapping("go")
	public ModelAndView go() {
		int id = 1;
		 Evaluate entity = evaluateService.getEvaluateById(id);
		   System.out.println(entity);
		   return new ModelAndView("success");
	}
	@RequestMapping("go2")
	public String go2() {
		int id = 1;
		Evaluate entity = evaluateService.getEvaluateById(id);
		System.out.println(entity);
		return "success";
	}
	
	@RequestMapping("evaluateForm")
	public String evaluateForm() {
		int id = 1;
		Evaluate entity = evaluateService.getEvaluateById(id);
		System.out.println(entity);
		return "evaluate/evaluateForm";
	}
}
