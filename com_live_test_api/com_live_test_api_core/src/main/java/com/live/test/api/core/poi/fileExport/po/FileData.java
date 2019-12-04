package com.live.test.api.core.poi.fileExport.po;

import java.util.List;
import java.util.Map;

import com.live.test.api.core.povo.entity.Entity;

public class FileData {
	private String filePath;
	private String fileName;
	private String fileSuffix;
	
	//private List<String> header;
//	private String[] header;
	private Map<String,String> header;
	private List<Entity> boday;


	public List<Entity> getBoday() {
		return boday;
	}

	public void setBoday(List<Entity> boday) {
		this.boday = boday;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Map<String,String> getHeader() {
		return header;
	}

	public void setHeader(Map<String,String> header) {
		this.header = header;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
}
