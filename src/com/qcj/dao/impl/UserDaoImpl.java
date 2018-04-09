package com.qcj.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.qcj.dao.UserDao;
import com.qcj.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	static Logger log = Logger.getLogger(UserDaoImpl.class);

	@Resource // @Resource默认按照ByName自动注入，
	JdbcTemplate jdbcTemplate;

	@Override
	public int allCountNum() {
		String sql = "select count(*) from t_user";
		Number number = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("sql return is number:" + number + "value:" + number.intValue());
		return (number != null ? number.intValue() : 0);
	}

	@Override
	public List<User> selectPageUser(int pageOn, int pageNumber) {
		String sql = " select * from t_user where rowid in(select rid from (select rownum rn,rid from(select rowid rid,userId from t_user) where rownum<=?) where rn>?)";
		System.out.println("canshu :" + pageOn + ":" + pageNumber + "计算后-" + pageOn * pageNumber + ":"
				+ (pageNumber - 1) * pageOn);
		List<User> list = jdbcTemplate.query(sql, new Object[] { pageOn * pageNumber, (pageNumber - 1) * pageOn },
				((RowMapper<User>) (rs, index) -> new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5))));
		System.out.println("sql list.size:" + list.size());
		return null == list || list.size() == 0 ? null : list;
	}

	@Override
	public List<User> selectPageUser(int pageOn, int pageNumber, String userNameLike) {
		String sql = " select * from t_user where rowid in(select rid from (select rownum rn,rid from(select rowid rid,userId from t_user where userName like '%"
				+ userNameLike + "%') where rownum<=?) where rn>?)";
		System.out.println("模糊查询canshu :" + pageOn + ":" + pageNumber + "计算后-" + pageOn * pageNumber + ":"
				+ (pageNumber - 1) * pageOn);
		List<User> list = jdbcTemplate.query(sql, new Object[] { pageOn * pageNumber, (pageNumber - 1) * pageOn },
				((RowMapper<User>) (rs, index) -> new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(4))));
		System.out.println("模糊sql list.size:" + list.size());
		return null == list || list.size() == 0 ? null : list;
	}

	@Override
	public int addUser(User user) {
		String sql = "insert into t_user values(seq_user.nextval,?,?,?,?)";
		int result = jdbcTemplate.update(sql,
				new Object[] { user.getUserName(), user.getUserEmail(), user.getUserPassword(), user.getUserPhone() });
		return result;
	}

	@Override
	public int deleteUser(int userId) {
		String sql = "delete t_user where userId = ?";
		int result = jdbcTemplate.update(sql, new Object[] { userId }, new int[] { java.sql.Types.INTEGER });
		return result;
	}

	@Override
	public int allCountNum(String userNameLike) {
		System.out.println("userdao中模糊查询传入的条件：" + userNameLike);
		String sql = "select count(*) from t_user where userName like '%" + userNameLike + "%'";
		Number number = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println("模糊查询 sql return is number:" + number + "value:" + number.intValue());
		return (number != null ? number.intValue() : 0);
	}

	@Override
	public int userUpdate(int userId, String userName, String userEmail, String userPassword, String userPhone) {
		String sql = "update t_user set userName = ?,userEmail=?,userPassword=?,userPhone=? where userId=?";
		int result = jdbcTemplate.update(sql, new Object[] { userName, userEmail, userPassword, userPhone, userId });
		return result;
	}

}
