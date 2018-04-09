package com.qcj.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qcj.dao.RoleDao;
import com.qcj.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
	@Resource // @Resource默认按照ByName自动注入，
	JdbcTemplate jdbcTemplate;

	int pageNumber = 1;

	@Override
	public int allCountNum() {
		String sql = "select count(*) from t_role";
		Number number = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("role sql return is:" + number.intValue());
		return (number != null ? number.intValue() : 0);
	}

	@Override
	public int allCountNum(String roleNameLike) {
		String sql = "select count(*) from t_role where rname like '%" + roleNameLike + "%'";
		Number number = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("role sql return is:" + number.intValue());
		return (number != null ? number.intValue() : 0);
	}

	@Override
	public List<Role> selectPageRole(int pageOn, int pageNumber) {
		String sql = "select * from t_role where rowid in(select roid from (select rownum rn,roid from(select rowid roid ,rid from t_role) where rownum<=?) where rn>?)";
		List<Role> list = jdbcTemplate.query(sql, new Object[] { pageOn * pageNumber, (pageNumber - 1) * pageOn },
				((RowMapper<Role>) (rs, index) -> new Role(rs.getInt(1), rs.getString(2), rs.getString(3))));
		System.out.println("sql list.size:" + list.size());
		return null == list || list.size() == 0 ? null : list;
	}

	@Override
	public List<Role> selectPageRole(int pageOn, int pageNumber, String roleNameLike) {
		String sql = "select * from t_role where rowid in(select roid from (select rownum rn,roid from(select rowid roid ,rid from t_role where rname like '%"
				+ roleNameLike + "%') where rownum<=?) where rn>?)";
		List<Role> list = jdbcTemplate.query(sql, new Object[] { pageOn * pageNumber, (pageNumber - 1) * pageOn },
				((RowMapper<Role>) (rs, index) -> new Role(rs.getInt(1), rs.getString(2), rs.getString(3))));
		System.out.println("sql list.size:" + list.size());
		return null == list || list.size() == 0 ? null : list;
	}

	@Override
	public List<Map<String, Object>> queryPermission(Role role) {
		System.out.println("sql roleid:" + role.getRid());
		String sql = " select r.rname, p.pid,p.pname,p.purl,p.pmessage \r\n"
				+ "       from t_role r inner join t_role_permission rp on r.rid=rp.rid \r\n"
				+ "       inner join t_permission p on rp.pid=p.pid \r\n" + "       where r.rid='" + role.getRid()
				+ "'";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public int addRole(Role role) {
		String sql = "insert into t_role values(seq_role.nextval,?,?)";
		int result = jdbcTemplate.update(sql, new Object[] { role.getRname(), role.getRmessage() });
		return result;
	}

	@Override
	public int roleUpdate(int roleId, String roleName, String roleMessage) {
		String sql = "update t_role set rname = ?,rmessage= ? where rid = ?";
		System.out.println(" ------dao canshu:" + roleId + ":" + roleName + ":" + roleMessage);
		int result = jdbcTemplate.update(sql, new Object[] { roleName, roleMessage, roleId });
		return result;
	}

	@Override
	public int deleteRole(int roleId) {
		String sql = "delete t_role where rid = ?";
		int result = jdbcTemplate.update(sql, new Object[] { roleId }, new int[] { java.sql.Types.INTEGER });
		System.out.println("--------dao result is:" + result);
		return result;
	}

	@Override
	public List<Map<String, Object>> queryNoPermission(Role role) {
		System.out.println("sql roleid:" + role.getRid());
		String sql = "select pid,pname,purl,pmessage from t_permission\r\n" + " where pid not in(\r\n"
				+ "       select p.pid\r\n"
				+ "       from t_role r inner join t_role_permission rp on r.rid=rp.rid \r\n"
				+ "       inner join t_permission p on rp.pid=p.pid \r\n" + "       where r.rid='" + role.getRid() + "'"
				+ " )";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public int deleteRolePermission(int roleId, int permissionId) {
		String sql = "delete t_role_permission where rid=? and pid = ?";
		int result = jdbcTemplate.update(sql, new Object[] { roleId, permissionId },
				new int[] { java.sql.Types.INTEGER, java.sql.Types.INTEGER });
		return result;
	}

	@Override
	public int addRolePermission(int roleId, int permissionId) {
		System.out.println("add---" + roleId + ":" + permissionId);
		String sql = "insert into t_role_permission values(seq_rp.nextval,?,?)";
		int result = jdbcTemplate.update(sql, new Object[] { roleId, permissionId });
		return result;
	}

}
