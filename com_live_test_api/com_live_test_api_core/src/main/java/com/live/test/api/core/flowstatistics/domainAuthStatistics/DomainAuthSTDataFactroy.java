package spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics;

import java.util.Date;

import spzc.module.systemhelp.util.flowstatistics.util.FlatData;

public class DomainAuthSTDataFactroy {
	public static FlatData build(String type) {

		switch (type) {
		case DomainAuthSTManager.ST_DOMAINAUTH_SUM:
			return buildSUM();

		case DomainAuthSTManager.ST_DOMAINAUTH_357:
			return build357();

		default:
			break;
		}
		return null;

	}

	private static FlatData build357() {
		FlatData data = new FlatData();
		data.set("startTime", new Date());
		//
		return data;
	}

	private static FlatData buildSUM() {
		FlatData data = new FlatData();
		data.set("startTime", new Date());
		data.set("auth.sum", 0);
		//
		return data;
	}
}
