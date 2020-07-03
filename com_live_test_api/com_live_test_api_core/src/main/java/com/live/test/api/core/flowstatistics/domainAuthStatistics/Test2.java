//package spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//import core.spzc.data.entity.Entity;
//import core.spzc.data.entity.MongoEntity;
//import spzc.module.systemhelp.util.flowstatistics.common.DomainST;
//
//public class Test2 {
//	
//	public void test() {
//		String domain = "spzc";
//		DomainAuthSTManager.iniDomainAuthST();
//		Entity entity = new MongoEntity("Auth");
//		
//		JsonObject auth = new JsonObject();
//		auth.add("", value);
//		
//		JsonArray authHistory = new JsonArray();
//		
//		entity.setValue("authHistory", authHistory);
//		
//		DomainAuthSTManager.doDomainAuthST(domain, entity);
//		DomainST.getInstance().getSTManager(domain ).getFilterChain(DomainAuthSTManager.ST_DOMAINAUTH_SUM).getData();
//	}
//}
