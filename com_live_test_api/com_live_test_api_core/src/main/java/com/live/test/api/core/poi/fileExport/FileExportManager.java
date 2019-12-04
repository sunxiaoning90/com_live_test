package com.live.test.api.core.poi.fileExport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.live.test.api.core.povo.entity.Entity;

public class FileExportManager {
	
	public static String getFileDir(String domainIdentification) {
		return "/opt/saasExport/"+domainIdentification+"/";
	}
	
	public static String getFilePath() {
		return "cus-" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_sss").format(new Date())+"-" + UUID.randomUUID();
	}

	public static String getSql(String entityName, String queryCondition) {
		return "select * from " + entityName;
	}

	public static List<Entity> getEntityList(String domainIdentification, String entityName, String queryCondition) {
		
		String sql = getSql(entityName,queryCondition);
		//TODO moduleInfo??
		String moduleInfo = "FileImport.FileImport001";
		moduleInfo = "customerInfo.customerInfo";
		//EntityDao dao = DaoHelper.getDao(domainIdentification, moduleInfo, entityName);
		
		return null;//dao.search(sql, null, 0, 0, null, moduleInfo);
	}
}
