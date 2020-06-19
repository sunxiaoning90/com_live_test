package com.live.test.javase.core.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeywordDao extends JdbcTemplate implements Dao<Keyword>{

	private final static String ENTITYNAME = "Keyword";

	@Deprecated
	public boolean save(String sql, Object... args) {
		return executeUpdate(sql,args) > 0;
	}

	@Override
	public boolean save(Keyword entity) {
		String sql = "insert into Keyword(number,question,answer,faqTag) values(?,?,?,?)";
		Object[] params = new Object[] { entity.getNumber(), entity.getQuestion(), entity.getAnswer() ,entity.getFaqTag()};
		return executeUpdate(sql, params) > 0;
	}

	@Override
	public List<Keyword> findOfListByProperty(String property, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Keyword findOfSingleById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	//FIXME 单个？list？
	@Override
	public Keyword findOfSingleByProperty(String property, String value) {
		String sql = "select * from Keyword where " + property +"=? limit 0,1";
		List<Keyword> list = retrieveOfListBySql(sql, value);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	 public List<Keyword> retrieveOfListBySql(String sql, Object... args) {
		List<Keyword> entityList = new ArrayList<Keyword>();
		//
		List<Map<String, Object>> list = query(sql,args);
		for (Map<String, Object> map : list) {
			Keyword entity = new Keyword();

			entity.setId(MapUtil.getWrapperValue(map, "id"));
			entity.setNumber(MapUtil.getWrapperValue(map, "number"));
			entity.setQuestion(MapUtil.getWrapperValue(map, "question"));
			entity.setAnswer(MapUtil.getWrapperValue(map, "answer"));
			entity.setAnswer(MapUtil.getWrapperValue(map, "faqTag"));
			entityList.add(entity);
		}
		return entityList;
	}

	public Long getCount() {
		String sql = "select count(id) as count_ from Keyword";
		return getCount(sql);
	}

	private Long getCount(String sql) {
		List<Map<String, Object>> list = query(sql);
		if(list!= null && list.size() > 0){
			Map<String, Object> map = list.get(0);
			//FIXME 
			//return DatatypeConvertUtil.convert(map.get("count_"), Long.class);
			return Long.parseLong( String.valueOf(map.get("count_") ));
		}
		return 0L;
	}

	@Override
	public boolean update(Keyword entity) {
		if(entity == null){
			return false;
		}
		
		//FIXME 应该为updataNotNull
		String sql = "update " + ENTITYNAME + " set number=?, question=?, answer=? where id=?";
		Object[] params = new Object[] { entity.getNumber(), entity.getQuestion(), entity.getAnswer(),entity.getId() };
		return executeUpdate(sql, params) > 0;
	}

	public boolean deleteById(String id) {
		String sql = "delete from " + ENTITYNAME + " where id=?";
		return executeUpdate(sql, id) > 0;
	}
}
