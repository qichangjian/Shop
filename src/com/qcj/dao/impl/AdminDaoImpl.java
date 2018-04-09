package com.qcj.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qcj.dao.AdminDao;
import com.qcj.entity.Admin;

@Repository("adminDao") // （实现dao访问）
public class AdminDaoImpl implements AdminDao {
	static Logger log = Logger.getLogger(AdminDaoImpl.class);

	@Resource // @Resource默认按照ByName自动注入，
	JdbcTemplate jdbcTemplate;

	/**
	 * 检查后台登陆用户是否存在
	 */
	@Override
	public Admin checkAdmin(Admin admin) {
		log.info("AdminDaoImpl checkAdmin:-admin name:" + admin.getAname() + "  ,admin pwd:" + admin.getApassword());
		String sql = "select * from t_admin where aname = ? and apassword=?";
		log.info("sql");
		List<Admin> list = jdbcTemplate.query(sql, new Object[] { admin.getAname(), admin.getApassword() },
				((RowMapper<Admin>) (rs, index) -> new Admin(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4))));
		return null == list || list.size() == 0 ? null : list.get(0);// 如果返回结果是null
																		// 或者
																		// 没有返回大小是0，返回0。反之返回list数组中的第一个admin
	}

	/**
	 * 登陆的时候根据输入用户，查询权限列表
	 */
	@Override
	public List<Map<String, Object>> queryPermission(Admin admin) {
		log.info("queryPermission result:-" + admin.getAid());
		/*
		 * String sql = "select a.aname, p.pid,p.pname,p.purl,p.pmessage" +
		 * "from t_admin a inner join t_admin_role ar on a.aid=ar.aid" +
		 * "inner join t_role_permission rp on ar.rid=rp.rid" +
		 * "inner join t_permission p on rp.pid=p.pid" +
		 * "where a.aid='"+admin.getAid()+"'";
		 */
		String sql = "select a.aname, p.pid,p.pname,p.purl,p.pmessage \r\n"
				+ "       from t_admin a inner join t_admin_role ar on a.aid=ar.aid \r\n"
				+ "       inner join t_role_permission rp on ar.rid=rp.rid \r\n"
				+ "       inner join t_permission p on rp.pid=p.pid \r\n" + "       where a.aid='" + admin.getAid()
				+ "'";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public int allCountNum() {
		String sql = "select count(*) from t_admin";
		Number number = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("sql return is number:" + number + "value:" + number.intValue());
		return (number != null ? number.intValue() : 0);
	}

	@Override
	public int allCountNum(String adminNameLike) {
		System.out.println("admindao中模糊查询传入的条件：" + adminNameLike);
		String sql = "select count(*) from t_admin where aname like '%" + adminNameLike + "%'";
		Number number = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("模糊查询 sql return is number:" + number + "value:" + number.intValue());
		return (number != null ? number.intValue() : 0);
	}

	@Override
	public List<Admin> selectPageAdmin(int pageOn, int pageNumber) {
		String sql = " select * from t_admin where rowid in(select rid from (select rownum rn,rid from(select rowid rid,aid from t_admin) where rownum<=?) where rn>?)";
		System.out.println("canshu :" + pageOn + ":" + pageNumber + "计算后-" + pageOn * pageNumber + ":"
				+ (pageNumber - 1) * pageOn);
		List<Admin> list = jdbcTemplate.query(sql, new Object[] { pageOn * pageNumber, (pageNumber - 1) * pageOn },
				((RowMapper<Admin>) (rs, index) -> new Admin(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4))));
		System.out.println("sql list.size:" + list.size());
		return null == list || list.size() == 0 ? null : list;
	}

	// 模糊查询
	@Override
	public List<Admin> selectPageAdmin(int pageOn, int pageNumber, String adminNameLike) {
		String sql = " select * from t_admin where rowid in(select rid from (select rownum rn,rid from(select rowid rid,aid from t_admin where aname like '%"
				+ adminNameLike + "%') where rownum<=?) where rn>?)";
		System.out.println("模糊查询canshu :" + pageOn + ":" + pageNumber + "计算后-" + pageOn * pageNumber + ":"
				+ (pageNumber - 1) * pageOn);
		List<Admin> list = jdbcTemplate.query(sql, new Object[] { pageOn * pageNumber, (pageNumber - 1) * pageOn },
				((RowMapper<Admin>) (rs, index) -> new Admin(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4))));
		System.out.println("模糊sql list.size:" + list.size());
		return null == list || list.size() == 0 ? null : list;
	}

	@Override
	public int addAdmin(Admin admin) {
		String sql = "insert into t_admin values(seq_admin.nextval,?,?,?)";
		int result = jdbcTemplate.update(sql,
				new Object[] { admin.getAname(), admin.getApassword(), admin.getAmessage() });
		return result;
	}

	@Override
	public int deleteAdmin(int adminId) {
		String sql = "delete t_admin where aid = ?";
		int result = jdbcTemplate.update(sql, new Object[] { adminId }, new int[] { java.sql.Types.INTEGER });
		return result;
	}

	@Override
	public int adminUpdate(int adminId, String adminName, String adminPassword, String adminMessage) {
		String sql = "update t_admin set aname = ?,apassword=?,amessage=? where aid=?";
		int result = jdbcTemplate.update(sql, new Object[] { adminName, adminPassword, adminMessage, adminId });
		return result;
	}

	@Override
	public List<Map<String, Object>> queryRole(Admin admin) {
		String sql = "select a.aname, r.rid,r.rname,r.rmessage \r\n"
				+ "       from t_admin a inner join t_admin_role ar on a.aid=ar.aid \r\n"
				+ "       inner join t_role r on ar.rid=r.rid \r\n" + "       where a.aid='" + admin.getAid() + "'";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> queryNoRole(Admin admin) {
		String sql = " select rid,rname,rmessage from t_role\r\n" + " where rid not in(\r\n"
				+ "       select r.rid \r\n" + "       from t_admin a inner join t_admin_role ar on a.aid=ar.aid \r\n"
				+ "       inner join t_role r on ar.rid=r.rid \r\n" + "       where a.aid='" + admin.getAid() + "'"
				+ " )";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public int addAdminRole(int adminId, int roleId) {
		String sql = "insert into t_admin_role values(seq_ar.nextval,?,?)";
		int result = jdbcTemplate.update(sql, new Object[] { adminId, roleId });
		return result;
	}

	@Override
	public int deleteAdminRole(int adminId, int roleId) {
		String sql = "delete t_admin_role where aid = ? and rid = ? ";
		int result = jdbcTemplate.update(sql, new Object[] { adminId, roleId },
				new int[] { java.sql.Types.INTEGER, java.sql.Types.INTEGER });
		return result;
	}

}
