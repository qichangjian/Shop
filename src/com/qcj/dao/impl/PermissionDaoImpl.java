package com.qcj.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.qcj.dao.PermissionDao;

@Repository("permissionDao")   
public class PermissionDaoImpl implements PermissionDao {
	@Resource                        //  @Resource默认按照ByName自动注入，
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public int allCountNum() {
		String sql ="select count(*) from t_permission";
		Number number=  jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("sql return is number:"+number+"value:"+number.intValue());
	    return (number != null ? number.intValue() : 0);	
	}
	@Override
	public int allCountNum(String permissionNameLike) {
		String sql ="select count(*) from t_permission where pname like '%"+permissionNameLike+"%'";
		Number number=  jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("sql return is number:"+number+"value:"+number.intValue());
	    return (number != null ? number.intValue() : 0);	
	}
	/**
	 * 这个方式出错了：使用了下边的方法queryPermission
	 */
/*	@Override
	public List<Permission> selectPagePermission(int pageOn, int pageNumber) {
		String sql="select * from t_permission where rowid in(select rid from (select rownum rn,rid from(select rowid rid,pid from t_permission) where rownum<=?) where rn>?)";
//		List<Permission> list = jdbcTemplate.query(sql, new Object[]{pageOn*pageNumber,(pageNumber-1) * pageOn},
//				((RowMapper<Permission>)(rs,index) -> new Permission(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4))));
//		System.out.println("sql list.size:"+list.size());
//		return null==list||list.size()==0?null:list;
		return null;
	}*/
	
	public List<Map<String,Object>> queryPermission(int pageOn, int pageNumber){
		String sql = "select * from t_permission where rowid in(select rid from (select rownum rn,rid from(select rowid rid,pid from t_permission) where rownum<="+pageOn*pageNumber+") where rn>"+(pageNumber-1) * pageOn+")";
		return jdbcTemplate.queryForList(sql);
	}
	public List<Map<String,Object>> queryPermission(int pageOn, int pageNumber,String permissionNameLike){
		String sql = "select * from t_permission where rowid in(select rid from (select rownum rn,rid from(select rowid rid,pid from t_permission where pname like '%"+permissionNameLike+"%') where rownum<="+pageOn*pageNumber+") where rn>"+(pageNumber-1) * pageOn+")";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public int addPermission(String pname,String purl,String pmessage) {
		String sql = "insert into t_permission values(seq_permission.nextval,?,?,?)";
		int result = jdbcTemplate.update(sql,new Object[] {pname,purl,pmessage});
		return result;
	}

	@Override
	public int deletePermission(int permissionId) {
		System.out.println("-------dao-permissionId:"+permissionId);
		String sql = "delete t_permission where pid = ?";
		int result = jdbcTemplate.update(sql,new Object[] {permissionId},new int[] {java.sql.Types.INTEGER});
		System.out.println("--------dao result is:"+result);
		return result;
	}

	@Override
	public int permissionUpdate(int permissionId, String permissionName, String permissionUrl,
			String permissionMessage) {
		String sql = "update t_permission set pname = ?,purl= ?,pmessage=? where pid = ?";
		System.out.println(" ------dao canshu:"+permissionId+":"+permissionName+":"+permissionUrl+":"+permissionMessage);
		int result = jdbcTemplate.update(sql,new Object[] {permissionName,permissionUrl,permissionMessage,permissionId});
		return result;
	}


}
