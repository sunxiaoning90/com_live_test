package com.live.test.javase.core.jdbc;

import net.sf.json.JSONObject;

public class Keyword {
	//
	private String id;
	private String number;
	private String question;
	private String answer;
	private String faqTag;

	//
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	//
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("number", number);
		json.put("question", question);
		json.put("answer", answer);
		return json;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keyword [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", question=");
		builder.append(question);
		builder.append(", answer=");
		builder.append(answer);
		builder.append("]");
		return builder.toString();
	}

	public String getFaqTag() {
		return faqTag;
	}

	public void setFaqTag(String faqTag) {
		this.faqTag = faqTag;
	}
	
}
