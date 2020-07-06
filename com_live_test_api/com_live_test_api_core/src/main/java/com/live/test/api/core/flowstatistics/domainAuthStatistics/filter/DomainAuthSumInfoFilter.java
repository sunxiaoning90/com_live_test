package spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.filter;

import java.util.Date;

import core.spzc.data.entity.Entity;
import spzc.module.systemhelp.util.flowstatistics.common.IFilter;
import spzc.module.systemhelp.util.flowstatistics.util.Filter;
import spzc.module.systemhelp.util.flowstatistics.util.FlatData;

public class DomainAuthSumInfoFilter extends Filter implements IFilter<Entity, FlatData> {
	private FlatData data;
	IFilter<Entity, FlatData> next;

	private DomainAuthSumInfoFilter() {
		super("info");
	}

	public DomainAuthSumInfoFilter(FlatData data) {
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
		return value;
	};

	@Override
	public FlatData handle(Entity source) throws Exception {
		Object affiliatedCompany = source.getValue("affiliatedCompany");
		// FlatData d = (FlatData)data;
		// this.parse(data, authCount);
		if (affiliatedCompany != null) {
			data.set("company", affiliatedCompany);
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

	public static void main(String[] args) {
		String authCount = String.valueOf(null);
		System.out.println(authCount);
	}
}
