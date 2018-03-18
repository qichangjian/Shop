package com.qcj.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qcj.dao.CommodityDao;
import com.qcj.entity.Commodity;
import com.qcj.entity.Role;
@Repository("commodityDao")              //（实现dao访问）
public class CommodityDaoImpl implements CommodityDao {
	static Logger log = Logger.getLogger(CommodityDaoImpl.class);
	
	@Resource                        //  @Resource默认按照ByName自动注入，
	JdbcTemplate jdbcTemplate;

	@Override
	public int allCountNum(String commodityNameLike, int gategoriesId) {
		String sql ="select count(*) from t_commodity where gategoriesId = "+ gategoriesId +" and commodityName like '%"+commodityNameLike+"%'";
		Number number=  jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("sql return is number:"+number+"value:"+number.intValue());
	    return (number != null ? number.intValue() : 0);	
	}

	@Override
	public List<Commodity> selectPageCommodity(int pageOn, int pageNumber, String commodityNameLike, int gategoriesId) {
		String sql = "select * from t_commodity where rowid in(select roid from (select rownum rn,roid from(select rowid roid ,commodityId from t_commodity where gategoriesId = "+ gategoriesId +" and commodityName like '%"+commodityNameLike+"%') where rownum<=?) where rn>?)";
		List<Commodity> list = jdbcTemplate.query(sql, new Object[]{pageOn*pageNumber,(pageNumber-1) * pageOn},
				((RowMapper<Commodity>)(rs,index) -> new Commodity(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getLong(4),rs.getString(5),rs.getString(6),rs.getString(7))));
		System.out.println("sql list.size:"+list.size());
		return null==list||list.size()==0?null:list;
	}

	@Override
	public int addCommodity(Commodity commodity) {
		String sql = "insert into t_commodity values(1,?,?,?,?,?,?)";
		System.out.println(sql);
		int result = jdbcTemplate.update(sql,new Object[] {commodity.getGategoriesId(),commodity.getCommodityName(),
						commodity.getCommodityPrice(),commodity.getCommodityIsMan(),commodity.getCommodityIsShelves(),commodity.getCommoditySize()});
		return result;
	}

	@Override
	public int commodityUpdate(int commodityId, String commodityName, String commodityPrice, String commodityIsMan,
			String commodityIsShelves,String commoditySize) {
		String sql = "update t_commodity set commodityName = ?,commodityPrice=?,commodityIsMan=?,commodityIsShelves=?,commoditySize=? where commodityId=?";
		int result = jdbcTemplate.update(sql,new Object[] {commodityName,commodityPrice,commodityIsMan,commodityIsShelves,commoditySize,commodityId});
		return result;
	}

	@Override
	public int upDownCommodity(int commodityId, String commodityIsShelves) {
		String sql = "update t_commodity set commodityIsShelves=? where commodityId=?";
		int result = jdbcTemplate.update(sql,new Object[] {commodityIsShelves,commodityId});
		return result;
	}

	@Override
	public int deleteCommodity(int commodityId) {
		String sql = "delete t_commodity where commodityId = ?";
		int result = jdbcTemplate.update(sql,new Object[] {commodityId},new int[] {java.sql.Types.INTEGER});
		return result;
	}
}
