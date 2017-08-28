package com.qcj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qcj.dao.PermissionDao;
import com.qcj.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{
	@Resource
	PermissionDao permissionDao; 
	
	//这个方法出错了，换成了下边的方法queryPermission
/*	@Override
	public List<Permission> selectPagePermission(int pageOn, int pageNumber) {
		return permissionDao.selectPagePermission(pageOn, pageNumber);
	}*/

	@Override
	public List<Map<String, Object>> queryPermission(int pageOn, int pageNumber) {		
		return permissionDao.queryPermission(pageOn, pageNumber);
	}
	@Override
	public List<Map<String, Object>> queryPermission(int pageOn, int pageNumber,String permissionNameLike) {		
		return permissionDao.queryPermission(pageOn, pageNumber,permissionNameLike);
	}

	@Override
	public int addPermission(String pname,String purl,String pmessage) {
		return permissionDao.addPermission(pname, purl, pmessage);
	}

	@Override
	public int deletePermission(int permissionId) {
		System.out.println("--------service-permissionId:"+permissionId);
		return permissionDao.deletePermission(permissionId);
	}

	@Override
	public int permissionUpdate(int permissionId, String permissionName, String permissionUrl,
			String permissionMessage) {
		System.out.println(" ------service canshu:"+permissionId+":"+permissionName+":"+permissionUrl+":"+permissionMessage);
		return permissionDao.permissionUpdate(permissionId, permissionName, permissionUrl, permissionMessage);
	}

}
