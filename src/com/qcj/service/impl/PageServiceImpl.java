package com.qcj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qcj.dao.AdminDao;
import com.qcj.dao.PermissionDao;
import com.qcj.dao.RoleDao;
import com.qcj.service.PageService;
@Service("pageService")
public class PageServiceImpl implements PageService{
	@Resource
	AdminDao adminDao;
	@Resource
	RoleDao roleDao;
	@Resource
	PermissionDao permissionDao;
	
	
	@Override
	public int adminAllPage(int pageOn1) {
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=adminDao.allCountNum();
		System.out.println("pageServiceimpl allCountNum is:"+rowall);
		int pageall = 0;
		if (rowall % pageOn == 0) {
			pageall = rowall / pageOn ;
		} else {
			pageall = rowall / pageOn + 1;
		}
		System.out.println("返回的pageall is :"+pageall);
		return pageall;
	}
	//传两个参数的adminAllPage
	@Override
	public int adminAllPage(int pageOn1,String adminNameLike) {
		System.out.println("pageservice中模糊查询传入的条件："+adminNameLike);
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=adminDao.allCountNum(adminNameLike);
		System.out.println("pageServiceimpl allCountNum is:"+rowall);
		int pageall = 0;
		if (rowall % pageOn == 0) {
			pageall = rowall / pageOn ;
		} else {
			pageall = rowall / pageOn + 1;
		}
		System.out.println("模糊查询返回的pageall is :"+pageall);
		return pageall;
	}
	@Override
	public int roleAllPage(int pageOn1) {
		int pageOn = 1; 
		pageOn = pageOn1;
		int rowall = roleDao.allCountNum();//返回多少行
		int pageall = 0;
		if(rowall % pageOn == 0) {
			pageall = rowall / pageOn;
		}else {
			pageall = rowall / pageOn +1;
		}
		System.out.println("pageService pageall is:"+pageall);
		return pageall;
	}

	@Override
	public int roleAllPage(int pageOn1,String roleNameLike) {
		int pageOn = 1; 
		pageOn = pageOn1;
		int rowall = roleDao.allCountNum(roleNameLike);//返回多少行
		int pageall = 0;
		if(rowall % pageOn == 0) {
			pageall = rowall / pageOn;
		}else {
			pageall = rowall / pageOn +1;
		}
		System.out.println("pageService pageall is:"+pageall);
		return pageall;
	}
	@Override
	public int permissionAllPage(int pageOn1) {
		int pageOn = 1;
		pageOn = pageOn1;
		int rowall = permissionDao.allCountNum();
		int pageall = 0;
		if(rowall % pageOn == 0) {
			pageall = rowall / pageOn;
		}else {
			pageall = rowall / pageOn +1;
		}
		return pageall;
	}
	@Override
	public int permissionAllPage(int pageOn1,String permissionNameLike) {
		int pageOn = 1;
		pageOn = pageOn1;
		int rowall = permissionDao.allCountNum(permissionNameLike);
		int pageall = 0;
		if(rowall % pageOn == 0) {
			pageall = rowall / pageOn;
		}else {
			pageall = rowall / pageOn +1;
		}
		return pageall;
	}
	
	
}
