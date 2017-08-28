package com.qcj.dao;

import java.util.List;
import java.util.Map;

public interface PermissionDao {
	//分页查询中，查询Permission一共多少条数据
	int allCountNum();
	int allCountNum(String permissionNameLike);
	
	//根据分页查询用户(每页多少条数，第几页)这个出错了
//	List<Permission> selectPagePermission(int pageOn,int pageNumber);
	
	List<Map<String,Object>> queryPermission(int pageOn, int pageNumber);
	List<Map<String,Object>> queryPermission(int pageOn, int pageNumber,String permissionNameLike);
	
	//添加权限
	int addPermission(String pname,String purl,String pmessage);
	
	//删除权限
	int deletePermission(int permissionId);
	
	//修改权限
	int permissionUpdate(int permissionId,String permissionName,String permissionUrl,String permissionMessage);
}
