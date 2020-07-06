package spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics;

import java.util.List;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import core.spzc.data.entity.Entity;
import core.spzc.data.entity.MongoEntity;
import spzc.manager.domain.manager.domain.DomainManager;
import spzc.manager.domain.model.domain.Domain;
import spzc.module.systemhelp.service.SystemStatusService;
import spzc.module.systemhelp.util.flowstatistics.common.DomainST;
import spzc.module.systemhelp.util.flowstatistics.common.IFilterChain;
import spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.filterChain.DomainAuth357FilterChain;
import spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.filterChain.DomainAuthSumFilterChain;
import spzc.module.systemhelp.util.flowstatistics.util.FlatData;

public class DomainAuthSTManager {

	private static Logger log = LogManager.getLogger(SystemStatusService.class);

	public static final String ST_DOMAINAUTH_SUM = "ST_DOMAINAUTH_SUM";
	public static final String ST_DOMAINAUTH_357 = "ST_DOMAINAUTH_357";

	public static void iniDomainAuthST() {
		log.info("iniDomainAuthST all start...");
		List<Domain> domains = DomainManager.getInstance().getAll().getAllAsList();
		if (domains != null) {
			for (Domain domain : domains) {
				String domainId = domain.getDomainIdentification();
				if (domainId != null) {
					iniDomainAuthST(domainId);
				}
			}
		}
	}

	private static void iniDomainAuthST(String domain) {
//		String domain = "spzc";
		log.info("iniDomainAuthST：" + domain);

		// TODO 无需传入data
//		FlatData data = new FlatData();
//		data.set("startTime", new Date());
		DomainAuthSumFilterChain csum = new DomainAuthSumFilterChain();

		FlatData data = csum.getData();
		DomainAuth357FilterChain c357 = new DomainAuth357FilterChain(data);

		DomainST.getInstance().getSTManager(domain).putFilterChain(DomainAuthSTManager.ST_DOMAINAUTH_SUM, csum);
		DomainST.getInstance().getSTManager(domain).putFilterChain(DomainAuthSTManager.ST_DOMAINAUTH_357, c357);
	}

	public static FlatData getDomainAuthSTData(String domain) {
//		String domain = "spzc";
		log.info("N：" + domain);
		return DomainST.getInstance().getSTManager(domain).getFilterChain(DomainAuthSTManager.ST_DOMAINAUTH_SUM)
				.getData();
	}

	public static void doDomainAuthST(Entity entity) {
		log.info("start...");
		List<Domain> domains = DomainManager.getInstance().getAll().getAllAsList();
		if (domains != null) {
			for (Domain domain : domains) {
				String domainId = domain.getDomainIdentification();
				if (domainId != null) {
					doDomainAuthST(domainId, entity);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void doDomainAuthST(String domain, Entity entity) {
		doDomainAuthSTSUM(domain, entity);

		Object authHistory = entity.getValue("authHistory");
		if (authHistory == null) {
			return;
		}

		String expiresTimeTag = "expiresTime", validTimeTag = "validTime", authCountTag = "authCount";
		Consumer<? super JSONObject> action = new Consumer<JSONObject>() {

			@Override
			public void accept(JSONObject t) {
				Entity e = new MongoEntity("Auth");
				if (t.containsKey(expiresTimeTag)) {
					e.setValue(expiresTimeTag, t.getString(expiresTimeTag));
				}

				if (t.containsKey(validTimeTag)) {
					e.setValue(validTimeTag, t.getString(validTimeTag));
				}

				if (t.containsKey(authCountTag)) {
					e.setValue(authCountTag, t.getString(authCountTag));
				}

				doDomainAuthST357(domain, e);
			}
		};

		JSONArray array = JSONArray.parseArray((String) authHistory);
		if (array != null) {
			array.forEach((Consumer<? super Object>) action);
		}

	}

	public static void doDomainAuthSTSUM(String domain, Entity entity) {
		IFilterChain chain = DomainST.getInstance().getSTManager(domain)
				.getFilterChain(DomainAuthSTManager.ST_DOMAINAUTH_SUM);

		try {
			chain.handle(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void doDomainAuthST357(String domain, Entity entity) {
		IFilterChain chain = DomainST.getInstance().getSTManager(domain)
				.getFilterChain(DomainAuthSTManager.ST_DOMAINAUTH_357);

//		try {
//			chain.handle(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		FlatData data = null;
		try {
			data = (FlatData) chain.handle(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(data.getFlatJson());
	}

	public static FlatData build357() {
		return DomainAuthSTDataFactroy.build(DomainAuthSTManager.ST_DOMAINAUTH_357);
	}

	public static FlatData buildSUM() {
		return DomainAuthSTDataFactroy.build(DomainAuthSTManager.ST_DOMAINAUTH_SUM);
	}
}
