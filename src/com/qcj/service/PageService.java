package com.qcj.service;

public interface PageService {
	//计算admin一共多少页
	int adminAllPage(int pageOn);
	int adminAllPage(int pageOn,String adminNameLike);
	
	int roleAllPage(int pageOn);
	int roleAllPage(int pageOn,String roleNameLike); 
	
	int permissionAllPage(int pageOn);
	int permissionAllPage(int pageOn,String permissionNameLike);
}
