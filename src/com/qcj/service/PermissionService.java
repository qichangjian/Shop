package com.qcj.service;

import java.util.List;
import java.util.Map;

public interface PermissionService {
/*	//根据分页查询用户(每页多少条数，第几页)--这个方法出错了，换成了queryPermission方法
	List<Permission> selectPagePermission(int pageOn,int pageNumber);*/
	//根据分页查询用户(每页多少条数，第几页)
	List<Map<String,Object>> queryPermission(int pageOn, int pageNumber);
	List<Map<String,Object>> queryPermission(int pageOn, int pageNumber,String permissionNameLike);
	
	//添加权限
	int addPermission(String pname,String purl,String pmessage);
	
	//删除权限
	int deletePermission(int permissionId);
	
	//修改权限
	int permissionUpdate(int permissionId,String permissionName,String permissionUrl,String permissionMessage);
}
