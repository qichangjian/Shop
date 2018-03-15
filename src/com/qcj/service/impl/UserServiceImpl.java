package com.qcj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qcj.dao.UserDao;
import com.qcj.entity.User;
import com.qcj.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	UserDao userDao;

	@Override
	public List<User> selectPageUser(int pageOn, int pageNumber) {
		return userDao.selectPageUser(pageOn, pageNumber);
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public int deleteUser(int userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public List<User> selectPageUser(int pageOn, int pageNumber, String userNameLike) {
		return userDao.selectPageUser(pageOn, pageNumber, userNameLike);
	}

	@Override
	public int userUpdate(int userId, String userName, String userEmail, String userPassword, String userPhone) {
		return userDao.userUpdate(userId, userName, userEmail, userPassword, userPhone);
	}

	
}
