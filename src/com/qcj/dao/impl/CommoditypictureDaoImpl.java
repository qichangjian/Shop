package com.qcj.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qcj.dao.CommoditypictureDao;
import com.qcj.entity.Commoditypicture;

@Repository("commoditypictureDao")  
public class CommoditypictureDaoImpl implements CommoditypictureDao{
static Logger log = Logger.getLogger(CommoditypictureDaoImpl.class);
	
	@Resource                        //  @Resource默认按照ByName自动注入，
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Commoditypicture> selectCommoditypicture(int commodityId) {
		String sql=" select * from t_commoditypicture where commodityId = ?";
		List<Commoditypicture> list = jdbcTemplate.query(sql, new Object[]{commodityId},
				((RowMapper<Commoditypicture>)(rs,index) -> new Commoditypicture(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4))));
		System.out.println("模糊sql list.size:"+list.size());
		return null==list||list.size()==0?null:list;
	}
}
