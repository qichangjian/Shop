package com.qcj.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qcj.dao.GategoriesDao;
import com.qcj.entity.Gategories;

@Repository("gategoriesDao")
public class GategoriesDaoImpl implements GategoriesDao {
	static Logger log = Logger.getLogger(GategoriesDaoImpl.class);

	@Resource // @Resource默认按照ByName自动注入，
	JdbcTemplate jdbcTemplate;

	@Override
	public int allCountNum() {
		String sql = "select count(*) from t_gategories";
		Number number = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("sql return is number:" + number + "value:" + number.intValue());
		return (number != null ? number.intValue() : 0);
	}

	@Override
	public List<Gategories> selectPageGategories(int pageOn, int pageNumber) {
		String sql = " select * from t_gategories where rowid in(select rid from (select rownum rn,rid from(select rowid rid,gategoriesId from t_gategories) where rownum<=?) where rn>?)";
		System.out.println("canshu :" + pageOn + ":" + pageNumber + "计算后-" + pageOn * pageNumber + ":"
				+ (pageNumber - 1) * pageOn);
		List<Gategories> list = jdbcTemplate.query(sql, new Object[] { pageOn * pageNumber, (pageNumber - 1) * pageOn },
				((RowMapper<Gategories>) (rs, index) -> new Gategories(rs.getInt(1), rs.getString(2),
						rs.getString(3))));
		System.out.println("sql list.size:" + list.size());
		return null == list || list.size() == 0 ? null : list;
	}

	@Override
	public int addGategories(Gategories gategories) {
		String sql = "insert into t_gategories values(seq_user.nextval,?,?)";
		int result = jdbcTemplate.update(sql,
				new Object[] { gategories.getGategoriesName(), gategories.getGategoriesMessage() });
		return result;
	}

	@Override
	public List<Gategories> selectPageGategories(int pageOn, int pageNumber, String gategoriesNameLike) {
		String sql = " select * from t_gategories where rowid in(select rid from (select rownum rn,rid from(select rowid rid,gategoriesId from t_gategories where gategoriesName like '%"
				+ gategoriesNameLike + "%') where rownum<=?) where rn>?)";
		System.out.println("模糊查询canshu :" + pageOn + ":" + pageNumber + "计算后-" + pageOn * pageNumber + ":"
				+ (pageNumber - 1) * pageOn);
		List<Gategories> list = jdbcTemplate.query(sql, new Object[] { pageOn * pageNumber, (pageNumber - 1) * pageOn },
				((RowMapper<Gategories>) (rs, index) -> new Gategories(rs.getInt(1), rs.getString(2),
						rs.getString(3))));
		System.out.println("模糊sql list.size:" + list.size());
		return null == list || list.size() == 0 ? null : list;
	}

	@Override
	public int allCountNum(String gategoriesNameLike) {
		System.out.println("gategoriesdao中模糊查询传入的条件：" + gategoriesNameLike);
		String sql = "select count(*) from t_gategories where gategoriesName like '%" + gategoriesNameLike + "%'";
		Number number = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("模糊查询 sql return is number:" + number + "value:" + number.intValue());
		return (number != null ? number.intValue() : 0);
	}

	@Override
	public int gategoriesUpdate(int gategoriesId, String gategoriesName, String gategoriesMessage) {
		String sql = "update t_gategories set gategoriesName = ?,gategoriesMessage=? where gategoriesId=?";
		int result = jdbcTemplate.update(sql, new Object[] { gategoriesName, gategoriesMessage, gategoriesId });
		return result;
	}

	@Override
	public List<Map<String, Object>> queryCommodity(Gategories gategories) {
		String sql = "select g.gategoriesName,c.commodityId,c.commodityName,c.commodityPrice,c.commodityName,"
				+ " c.commodityPrice,c.commodityIsMan,c.commodityIsShelves,c.commoditySize"
				+ " from t_gategories g inner join t_commodity c on g.gategoriesId = c.Gategoriesid"
				+ " where g.gategoriesid = '" + gategories.getGategoriesId() + "'";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public int deleteGategories(int gategoriesId) {
		String sql = "delete t_gategories where gategoriesId = ?";
		int result = jdbcTemplate.update(sql, new Object[] { gategoriesId }, new int[] { java.sql.Types.INTEGER });
		return result;
	}

}
