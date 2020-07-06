package spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.filterChain;

import core.spzc.data.entity.Entity;
import spzc.module.systemhelp.util.flowstatistics.common.IFilter;
import spzc.module.systemhelp.util.flowstatistics.common.IFilterChain;
import spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.DomainAuthSTDataFactroy;
import spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.DomainAuthSTManager;
import spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.filter.DomainAuthSumFilter;
import spzc.module.systemhelp.util.flowstatistics.domainAuthStatistics.filter.DomainAuthSumInfoFilter;
import spzc.module.systemhelp.util.flowstatistics.util.FlatData;

public class DomainAuthSumFilterChain implements IFilterChain<Entity, FlatData> {

	private FlatData data;

	public DomainAuthSumFilterChain() {
//		this.setData(DomainAuthSTManager.buildSUM());
//		IFilter<Entity, FlatData> headFilter = new DomainAuthSumFilter(this.getData());
//		this.setHeadFilter(headFilter);
		this(DomainAuthSTManager.buildSUM());
	}

	public DomainAuthSumFilterChain(FlatData data) {
//		this();
		this.setData(data);

		IFilter<Entity, FlatData> headFilter = new DomainAuthSumFilter(this.getData());
		headFilter.setNext(new DomainAuthSumInfoFilter(data));
		this.setHeadFilter(headFilter);
	}

	private IFilter<Entity, FlatData> headFilter;

	@Override
	public IFilter<Entity, FlatData> getHeadFilter() {
		return headFilter;
	}

	@Override
	public void setHeadFilter(IFilter<Entity, FlatData> headFilter) {
		this.headFilter = headFilter;
	}

	@Override
	public FlatData getData() {
		return data;
	}

	@Override
	public void setData(FlatData data) {
		this.data = data;
		if (this.getHeadFilter() != null) {
			this.getHeadFilter().setData(data);
		}
	}

	@Override
	public FlatData handle(Entity source) throws Exception {
		if (this.getData() == null) {
			throw new Exception("data为null");
		}
		if (this.getHeadFilter() == null) {
			throw new Exception("headFilter为null");
		}

		return this.getHeadFilter().handle(source);
	}

	@Override
	public void resetData() {
		this.setData(DomainAuthSTDataFactroy.build(DomainAuthSTManager.ST_DOMAINAUTH_SUM));
	}

}
