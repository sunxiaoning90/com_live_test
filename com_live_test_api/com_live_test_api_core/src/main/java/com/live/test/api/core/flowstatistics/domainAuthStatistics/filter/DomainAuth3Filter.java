package spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.filter;

import java.util.Date;

import core.spzc.data.entity.Entity;
import spzc.manager.domain.utils.AuthorizeUtil;
import spzc.module.systemhelp.util.flowstatistics.common.IFilter;
import spzc.module.systemhelp.util.flowstatistics.util.Filter;
import spzc.module.systemhelp.util.flowstatistics.util.FlatData;

public class DomainAuth3Filter extends Filter implements IFilter<Entity, FlatData> {
	private FlatData data;
	IFilter<Entity, FlatData> next;

	private DomainAuth3Filter() {
		super("357");
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
		return "auth." + value;
	};

	@Override
	public FlatData handle(Entity source) throws Exception {
		// FlatData d = (FlatData)data;
		
		String auth = source.getJson();
		long count = AuthorizeUtil.getAuthorizeExpiresDayByOne(auth);
		this.parse(String.valueOf(count));
		
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
