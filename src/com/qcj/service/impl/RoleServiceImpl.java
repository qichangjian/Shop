package com.qcj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qcj.dao.RoleDao;
import com.qcj.entity.Role;
import com.qcj.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Resource
	RoleDao roleDao;

	@Override
	public List<Role> selectPageRole(int pageOn, int pageNumber) {
		return roleDao.selectPageRole(pageOn, pageNumber);
	}
	@Override
	public List<Role> selectPageRole(int pageOn, int pageNumber,String roleNameLike) {
		return roleDao.selectPageRole(pageOn, pageNumber,roleNameLike);
	}

	@Override
	public List<Map<String, Object>> queryPermission(Role role) {
		return roleDao.queryPermission(role);
	}

	@Override
	public int addRole(Role role) {
		return roleDao.addRole(role);
	}

	@Override
	public int roleUpdate(int roleId, String roleName, String roleMessage) {
		return roleDao.roleUpdate(roleId, roleName, roleMessage);
	}

	@Override
	public int deleteRole(int roleId) {
		return roleDao.deleteRole(roleId);
	}

	@Override
	public List<Map<String, Object>> queryNoPermission(Role role1) {		
		return roleDao.queryNoPermission(role1);
	}

	@Override
	public int deleteRolePermission(int roleId, int permissionId) {
		return roleDao.deleteRolePermission(roleId, permissionId);
	}

	@Override
	public int addRolePermission(int roleId, int permissionId) {
		System.out.println("add---"+roleId+":"+permissionId);
		return roleDao.addRolePermission(roleId, permissionId);
	}
	
	
}
