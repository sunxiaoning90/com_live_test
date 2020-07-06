package spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.filter;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import core.spzc.data.entity.Entity;
import spzc.manager.domain.utils.AuthorizeUtil;
import spzc.module.systemhelp.util.flowstatistics.common.IFilter;
import spzc.module.systemhelp.util.flowstatistics.util.Filter;
import spzc.module.systemhelp.util.flowstatistics.util.FlatData;

public class DomainAuth3Filter extends Filter implements IFilter<Entity, FlatData> {
	private FlatData data;
	IFilter<Entity, FlatData> next;

	private DomainAuth3Filter() {
		super("auth.expires");
	}

	public DomainAuth3Filter(FlatData data) {
		this();
		this.setData(data);
	}

	@Override
	public FlatData getData() {
		return data;
	}

	@Override
	public void setData(FlatData data) {
		this.data = data;
	}

	@Override
	public Object parse(String value) {
//		return value;
//		return "expires." + value;

		Integer v = Integer.valueOf(value);
		if (v <= 3)
			return "3";
		if (v <= 5)
			return "5";
		if (v <= 7)
			return "7";
		if (v <= 15)
			return "15";
		return "15+"; //大于15天过期
	};

	@Override
	public FlatData handle(Entity source) throws Exception {
		FlatData d = (FlatData) data;

		String auth = source.getJson();
		long count = AuthorizeUtil.getAuthorizeExpiresDayByOne(auth);
		// Object v = this.parse(String.valueOf(count));

		try {
			JSONObject json = JSONObject.parseObject(auth);
			if (json.containsKey("authCount")) {
				int authCount = json.getInteger("authCount");
				for (int i = 0; i < authCount; i++) {
					// data.decrease(parse);
					this.parse(d, String.valueOf(count));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		data.set("modifiyTime", new Date());
		if (this.getNext() != null) {
			return getNext().handle(source);
		}
		return data;
	}

	@Override
	public void setNext(IFilter<Entity, FlatData> filter) {
		this.next = filter;
	}

	@Override
	public IFilter<Entity, FlatData> getNext() {
		return this.next;
	}

}
