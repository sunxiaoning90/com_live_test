package com.live.test.api.core.poi.fileExport.exportor.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.live.test.api.core.poi.fileExport.exportor.IFileExportor;
import com.live.test.api.core.poi.fileExport.po.FileData;
import com.live.test.api.core.povo.entity.Entity;

public class ExcelExportor implements IFileExportor {

	private static final String SUFFIX = ".xlsx";

	@Override
	public void export(FileData data) {
		data.setFileSuffix(SUFFIX);
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
		
		List<Entity> list = data.getBoday();

		int titleRowAt = 0; // 标头所在行
		int pagesize = 10000; // 单个sheet最大容量

		Set<String> keySet = header.keySet();
		String[] headerColumn = new String[keySet.size()];
		String[] headerAlias = new String[keySet.size()];
		int j = 0;
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
			headerColumn[j] = column;
			headerAlias[j] = alias;
			j++;
		}
		
//		Workbook wb = new HSSFWorkbook();
		Workbook wb = new XSSFWorkbook();

		double sheetCount = Math.ceil(list.size() / (double) pagesize);
		for (int i = 0; i < sheetCount; i++) {
			Sheet sheet = wb.createSheet("sheet-" + (i + 1));

			// 分页的起始位置和结束位置
			int startRow = i * pagesize;
			int endRow = (i + 1) * pagesize;
			if (list.size() < endRow) {
				endRow = list.size();
			}

			// 生成标头行
			Row row = sheet.createRow(titleRowAt);

			for (int colIdx = 0; colIdx < headerAlias.length; colIdx++) {
				Cell cell = row.createCell(colIdx);
				cell.setCellValue(headerAlias[colIdx]);
			}

			// 遍历List，生成每一行
			for (int sheetRowIdx = titleRowAt + 1, idx = startRow; idx < endRow; idx++, sheetRowIdx++) {
				// 取出元素
				Entity entity = list.get(idx);

				// 生成行
				Row sheetRow = sheet.createRow(sheetRowIdx);

				for (int colIdx = 0; colIdx < headerColumn.length; colIdx++) {
					Cell sheetCell = sheetRow.createCell(colIdx);
					String value = entity.getValue(headerColumn[colIdx], String.class);
					sheetCell.setCellValue(value);
				}
			}
		}
		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("写文件");
			wb.write(out);
			out.flush();
			System.out.println("写文件结束");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (wb != null) {
				try {
					wb.close();
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
//		List<String> header = new ArrayList<String>();
//		header.add("name");
//		header.add("age");
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
//		data.setFilePath("/opt/cus/TestExport.xlsx");
//		
//	}
}
