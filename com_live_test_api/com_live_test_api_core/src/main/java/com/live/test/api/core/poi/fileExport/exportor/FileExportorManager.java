package com.live.test.api.core.poi.fileExport.exportor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileExportorManager {
	public static final String EXPORTOR_PACKAGE = "spzc.module.fileImport.fileExport.exportor.impl.";
	public static final String EXPORTOR_SUFFIX = "Exportor";

	private Map<String, IFileExportor> importors = new ConcurrentHashMap<>();

	private volatile static FileExportorManager instance;

	private FileExportorManager() {
	}

	public static FileExportorManager getInstance() {
		if (instance == null) {
			synchronized (FileExportorManager.class) {
				if (instance == null) {
					instance = new FileExportorManager();
				}
			}
		}
		return instance;
	}

	@SuppressWarnings({ "unchecked" })
	public IFileExportor get(String key) {
		if (importors == null) {
			return null;
		}
		if (importors.containsKey(key)) {
			return importors.get(key);
		}

		IFileExportor exportor = null;
		synchronized (Object.class) {
			if (importors.containsKey(key)) {
				return importors.get(key);
			}
			try {
				Class<? extends IFileExportor> clazz = (Class<? extends IFileExportor>) Class
						.forName(EXPORTOR_PACKAGE + key + EXPORTOR_SUFFIX);
				exportor = clazz.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			importors.put(key, exportor);
		}
		return exportor;
	}

}
