package com.qcj.service;

import java.util.List;
import java.util.Map;


import com.qcj.entity.Role;

public interface RoleService {
	//根据分页查询用户(每页多少条数，第几页)
	List<Role> selectPageRole(int pageOn,int pageNumber);
	List<Role> selectPageRole(int pageOn,int pageNumber,String roleNameLike);
	
	//登陆的时候根据输入用户，查询权限列表
	List<Map<String,Object>> queryPermission(Role role); 
	//添加角色
	int addRole(Role role);
	
	//修改角色
	int roleUpdate(int roleId,String roleName,String roleMessage);
	
	//删除角色
	int deleteRole(int roleId);
	//根据角色，查询没有那个权限
	List<Map<String,Object>> queryNoPermission(Role role1);
	
	//给某个角色去掉特定权限
	int deleteRolePermission(int roleId,int permissionId);
	
	//给某个角色 增加特定权限
	int addRolePermission(int roleId,int permissionId);
}
