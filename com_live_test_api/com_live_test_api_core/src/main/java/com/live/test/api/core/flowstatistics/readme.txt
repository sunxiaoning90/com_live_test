一、报表统计,采用流式统计
责任链

结果集
过滤器链
过滤器节点

平板jsonData:{"st.sms.creator.admin":100,"st.sms.creator.zhangsan":50}
标准jsonData:{"st":{"creator":{"admin":100,"zhangsan":50}}

二、展示全部域的授权过期信息（授权总数、三天过期个数、五天过期个数、七天过期个数、十五天过期个数）
首先初始化统计 过滤器链（过滤器链中指定头过滤器，以及其它过滤器）。初始化应在系统启动后做一次，这里每次做一次是因为场景特殊，每次进行“数据归零”
iniDomainAuthST

2.1、entity进入后 先由 doDomainAuthSTSUM 解析：
Entity entity = new MongoEntity("Domain");// ?
				entity.setValue("authHistory", d.getAuthHistory());
				entity.setValue("authCount", d.getAuthCount());
				entity.setValue("affiliatedCompany", d.getDomainIdentification());
				DomainAuthSTManager.doDomainAuthST(d.getDomainIdentification(), entity);

2.2判断有auth，则进行auth解析
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
