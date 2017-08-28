package com.qcj.dao;

import java.util.List;
import java.util.Map;

import com.qcj.entity.Admin;

public interface AdminDao {
	//admin登陆检查
	public Admin checkAdmin(Admin admin);
	
	//登陆的时候根据输入用户，查询权限列表
	public List<Map<String,Object>> queryPermission(Admin admin); 
	
	//分页查询中，查询admin一共多少条数据
	int allCountNum();
	int allCountNum(String adminNameLike);
	
	//根据分页查询用户(每页多少条数，第几页)
	List<Admin> selectPageAdmin(int pageOn,int pageNumber);
	List<Admin> selectPageAdmin(int pageOn,int pageNumber,String adminNameLike);
	//增加管理员
	int addAdmin(Admin admin);
	//删除某个管理员
	int deleteAdmin(int adminId);
	
	//修改管理员
	int adminUpdate(int adminId,String adminName,String adminPassword,String adminMessage);
	
	//根据管理员查询角色
	List<Map<String,Object>> queryRole(Admin admin); 
	//根据管理员查询没有的角色
	List<Map<String,Object>> queryNoRole(Admin admin); 
	
	//给管理员添加角色
	int addAdminRole(int adminId,int roleId);
	//删除管理员的某个角色
	int deleteAdminRole(int adminId,int roleId);
}
