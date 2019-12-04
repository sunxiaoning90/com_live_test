package com.live.test.api.core.poi.fileExport.exportor.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.live.test.api.core.poi.fileExport.exportor.IFileExportor;
import com.live.test.api.core.poi.fileExport.po.FileData;
import com.live.test.api.core.povo.entity.Entity;

public class CSVExportor implements IFileExportor {
	private final String SUFFIX = ".csv";

	@Override
	public void export(FileData data) {
		data.setFileSuffix(SUFFIX);
		System.out.println("生成CVS...");
		String filePath = data.getFilePath();
		data.setFileName(data.getFileName()+ SUFFIX);
		String fileName = filePath + data.getFileName();
//		String[] header = data.getHeader();
		Map<String, String> header = data.getHeader();
		if (header == null || header.size() == 0) {
			System.out.println("表头为空");
			return;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
			System.out.println("****正在创建目录:" + filePath);
		} else {
			System.out.println("****目录已存在:" + filePath);
		}

		FileOutputStream fos = null;
		CSVPrinter csvPrinter = null;
		try {
			fos = new FileOutputStream(fileName);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf8");
//			String a[][];
			Set<String> keySet = header.keySet();
			String[] headerColumn = new String[keySet.size()];
			String[] headerAlias = new String[keySet.size()];
			int i = 0;
			String column = "";
			String alias = "";
			for (String s : keySet) {
				try {
					if (header.get(s) != null) {
						column = s;
						alias = header.get(s);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				headerColumn[i] = column;
				headerAlias[i] = alias;
				i++;
			}

			CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(headerAlias);
			csvPrinter = new CSVPrinter(osw, csvFormat);
//			FileWriter writer = new FileWriter(file,true);
//			csvPrinter = new CSVPrinter(writer, csvFormat);

			List<Entity> list = data.getBoday();
			if (list != null) {
				for (Entity entity : list) {
					String[] tmp = new String[headerColumn.length];

					for (int j = 0; j < header.keySet().size(); j++) {
						String v = entity.getValue(headerColumn[j], String.class);
						if (v != null) {
							try {
								v = new String(v.getBytes(), "UTF-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						} else {
							v = "";
						}
						tmp[j] = getCSVParsedString(v);
					}
					csvPrinter.printRecord(Arrays.asList(tmp));
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (csvPrinter != null) {
				try {
					csvPrinter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

//	public static void main(String[] args) {
//		FileData data = new FileData();
//
//		// 标头行
////		List<String> header = new ArrayList<String>();
////		header.add("name");
////		header.add("age");
//		String[] header = new String[] { "name", "age" };
//		data.setHeader(header);
//
//		List<Entity> boday = new ArrayList<Entity>();
//
//		Entity entity1 = new MongoEntity("CUS");
//		entity1.setValue("name", "导出测试_姓名");
//		entity1.setValue("age", "导出测试——性别");
//		boday.add(entity1);
//		boday.add(entity1);
//		boday.add(entity1);
//
//		data.setBoday(boday);
//
//		data.setFilePath("/opt/cus/");
//		data.setFileName("aaa");
//
//		new CSVExportor().export(data);
//	}

	private String getCSVParsedString(String content) {
		if (content == null)
			return "";
		// csv格式如果有逗号，整体用双引号括起来；如果里面还有双引号就替换成两个双引号，这样导出来的格式就不会有问题了
		String tempDescription = content;
		// 如果有逗号
		if (content.contains(",")) {
			// 如果还有双引号，先将双引号转义，避免两边加了双引号后转义错误
			if (content.contains("\"")) {
				tempDescription = content.replace("\"", "\"\"");
			}
			// 在将逗号转义
			tempDescription = "\"" + tempDescription + "\"";
		}
		return tempDescription;
	}
}
