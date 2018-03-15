package com.qcj.dao;

import java.util.List;

import com.qcj.entity.User;

public interface UserDao {
	//分页查询中，查询admin一共多少条数据
	int allCountNum();
	int allCountNum(String userNameLike);
	//根据分页查询用户(每页多少条数，第几页)
	List<User> selectPageUser(int pageOn,int pageNumber);
	List<User> selectPageUser(int pageOn,int pageNumber,String userNameLike);
	
	//增加管理员
	int addUser(User user);
	
	//删除某个用户
	int deleteUser(int userId);
	
	//修改管理员
	int userUpdate(int userId,String userName,String userEmail,String userPassword,String userPhone);
}
