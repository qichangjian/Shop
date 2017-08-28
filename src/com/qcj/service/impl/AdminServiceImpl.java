package com.qcj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qcj.dao.AdminDao;
import com.qcj.entity.Admin;
import com.qcj.service.AdminService;
@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Resource
	AdminDao adminDao;
	
	@Override
	public Admin checkAdmin(Admin admin) {
		return adminDao.checkAdmin(admin);
	}

	@Override
	public List<Map<String, Object>> queryPermission(Admin admin) {	
		return adminDao.queryPermission(admin);
	}

	@Override
	public List<Admin> selectPageAdmin(int pageOn, int pageNumber) {
		return adminDao.selectPageAdmin(pageOn, pageNumber);
	}
	@Override
	public List<Admin> selectPageAdmin(int pageOn, int pageNumber,String adminNameLike) {
		return adminDao.selectPageAdmin(pageOn, pageNumber,adminNameLike);
	}
	
	@Override
	public int addAdmin(Admin admin) {
		return adminDao.addAdmin(admin);
	}

	@Override
	public int deleteAdmin(int adminId) {
		return adminDao.deleteAdmin(adminId);
	}

	@Override
	public int adminUpdate(int adminId, String adminName, String adminPassword, String adminMessage) {
		return adminDao.adminUpdate(adminId, adminName, adminPassword, adminMessage);
	}

	@Override
	public List<Map<String, Object>> queryRole(Admin admin) {
		return adminDao.queryRole(admin);
	}

	@Override
	public List<Map<String, Object>> queryNoRole(Admin admin) {
		return adminDao.queryNoRole(admin);
	}

	@Override
	public int addAdminRole(int adminId, int roleId) {
		return adminDao.addAdminRole(adminId, roleId);
	}

	@Override
	public int deleteAdminRole(int adminId, int roleId) {
		return adminDao.deleteAdminRole(adminId, roleId);
	}



}
