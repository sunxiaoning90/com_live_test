package com.live.test.javaee.springboot.messageBoard.po;

import lombok.Getter;
import lombok.Setter;

//@Data
public class MessageBoard {
	@Getter
	@Setter
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private String content;

	@Getter
	@Setter
	private String time;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
