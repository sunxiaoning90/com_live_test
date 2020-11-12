package com.live.test.javaee.springmvc.po;

import java.io.Serializable;

/**
 * 满意度评价
 * 
 * @author live
 * @2019年12月27日 @下午5:43:56
 */
public class Evaluate implements Serializable {

	private static final long serialVersionUID = -6483538856330973836L;

	private Integer id;
	private String evaluate;
	private String suggest;

	public Evaluate(Integer id, String evaluate, String suggest) {
		super();
		this.id = id;
		this.evaluate = evaluate;
		this.suggest = suggest;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getSuggest() {
		return suggest;
	}

	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	@Override
	public String toString() {
		return "Evaluate [id=" + id + ", evaluate=" + evaluate + ", suggest=" + suggest + "]";
	}
}