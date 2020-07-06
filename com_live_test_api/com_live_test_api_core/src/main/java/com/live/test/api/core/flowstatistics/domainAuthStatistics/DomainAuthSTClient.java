package spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import core.spzc.data.entity.Entity;
import core.spzc.data.entity.MongoEntity;
import spzc.manager.domain.manager.domain.DomainManager;
import spzc.manager.domain.model.domain.Domain;
import spzc.module.systemhelp.util.flowstatistics.util.FlatData;

/**
 * 展示全部域的授权过期信息（授权总数、三天过期个数、五天过期个数、七天过期个数、十五天过期个数）
 * 
 * @author live
 *
 */
public class DomainAuthSTClient {

	public static JSONObject doDomainAuthST() {
		/// 初始化
		DomainAuthSTManager.iniDomainAuthST();

		/// 计算
		List<Domain> all = DomainManager.getInstance().getAll().getAllAsList();
		Consumer<? super Domain> init = new Consumer<Domain>() {
			@Override
			public void accept(Domain d) {
				if (Objects.isNull(d)) {
					return;
				}

				Entity entity = new MongoEntity("Domain");// ?
				entity.setValue("authHistory", d.getAuthHistory());
				entity.setValue("authCount", d.getAuthCount());
				entity.setValue("affiliatedCompany", d.getDomainIdentification());
				DomainAuthSTManager.doDomainAuthST(d.getDomainIdentification(), entity);
			}
		};
		all.forEach(init);

		/// 取值
		JSONArray dataArray = new JSONArray();
		Consumer<? super Domain> get = new Consumer<Domain>() {
			@Override
			public void accept(Domain d) {
				if (Objects.isNull(d)) {
					return;
				}
				FlatData data = DomainAuthSTManager.getDomainAuthSTData(d.getDomainIdentification());
				System.out.println(data);

				String jsonStr = data.getJson();
				System.out.println(jsonStr);
				dataArray.add(JSONObject.parse(jsonStr));
			}
		};
		all.forEach(get);

		///
		JSONObject r = new JSONObject();
		r.put("auths", dataArray);
		return r;
	}

}
