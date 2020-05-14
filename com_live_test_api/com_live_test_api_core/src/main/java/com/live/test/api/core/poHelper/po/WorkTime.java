package com.live.test.api.core.poHelper.po;

import net.sf.json.JSONObject;

public class WorkTime {
	// Field
	private String id;
	private String startTime;
	private String endTime;

	// Contructor
	public WorkTime() {
		super();
	}

	// getter setter
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("startTime", startTime);
		json.put("endTime", endTime);
		return json;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkTime [id=");
		builder.append(id);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append("]");
		return builder.toString();
	}
}
