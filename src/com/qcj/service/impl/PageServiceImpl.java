package com.qcj.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qcj.dao.AdminDao;
import com.qcj.dao.CommodityCommentDao;
import com.qcj.dao.CommodityDao;
import com.qcj.dao.ContactUsDao;
import com.qcj.dao.GategoriesDao;
import com.qcj.dao.OrdersDao;
import com.qcj.dao.PermissionDao;
import com.qcj.dao.RoleDao;
import com.qcj.dao.SalesVolumeDao;
import com.qcj.dao.UserDao;
import com.qcj.service.PageService;
@Service("pageService")
public class PageServiceImpl implements PageService{
	@Resource
	AdminDao adminDao;
	@Resource
	RoleDao roleDao;
	@Resource
	PermissionDao permissionDao;
	@Resource
	UserDao userDao;
	@Resource
	GategoriesDao gategoriesDao;
	@Resource
	CommodityDao commodityDao;
	@Resource
	OrdersDao ordersDao;
	@Resource
	ContactUsDao contactUsDao;
	@Resource
	CommodityCommentDao commodityCommentDao;
	@Resource
	SalesVolumeDao salesVolumeDao;
	
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
	
	@Override
	public int userAllPage(int pageOn1) {
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=userDao.allCountNum();
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
	@Override
	public int userAllPage(int pageOn1, String userNameLike) {
		System.out.println("pageservice中模糊查询传入的条件："+userNameLike);
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=userDao.allCountNum(userNameLike);
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
	public int gategoriesAllPage(int pageOn1) {
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=gategoriesDao.allCountNum();
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
	@Override
	public int gategoriesAllPage(int pageOn1, String gategoriesNameLike) {
		System.out.println("pageservice中模糊查询传入的条件："+gategoriesNameLike);
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=gategoriesDao.allCountNum(gategoriesNameLike);
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
	public int commodityPageAll(int pageOn1, String commodityNameLike, int gategoriesId) {
		System.out.println("pageservice中模糊查询传入的条件："+commodityNameLike);
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=commodityDao.allCountNum(commodityNameLike,gategoriesId);
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
	public int ordersPayAllPage(int pageOn1) {
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=ordersDao.allCountNum();
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
	
	@Override
	public int ordersAllPage(int pageOn1) {
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=ordersDao.allCountNum2();
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
	@Override
	public int contactUsAllPage(int pageOn1) {
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=contactUsDao.allCountNum();
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
	@Override
	public int commodityCommentAllPage(int pageOn1) {
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=commodityCommentDao.allCountNum();
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
	@Override
	public int CommentAllPage(int pageOn1, int commodityId) {
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=commodityCommentDao.allCountNum2(commodityId);
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
	@Override
	public int salesVolumeAllPage(int pageOn1) {
		int pageOn=1;//每页多少条数据
		pageOn = pageOn1;
		System.out.println("pageServiceimpl 中方法接受的的page页面pageon is:"+pageOn);
		int rowall=salesVolumeDao.allCountNum();
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
}
