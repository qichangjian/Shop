package com.qcj.service;

public interface PageService {
	// 计算admin一共多少页
	int adminAllPage(int pageOn);

	int adminAllPage(int pageOn, String adminNameLike);

	int roleAllPage(int pageOn);

	int roleAllPage(int pageOn, String roleNameLike);

	int permissionAllPage(int pageOn);

	int permissionAllPage(int pageOn, String permissionNameLike);

	int userAllPage(int pageOn);

	int userAllPage(int pageOn, String userNameLike);

	int gategoriesAllPage(int pageOn);

	int gategoriesAllPage(int pageOn, String gategoriesNameLike);

	int commodityPageAll(int pageOn, String commodityNameLike, int gategoriesId);

	int ordersPayAllPage(int pageOn);// 订货的

	int ordersAllPage(int pageOn);// 发货的

	int contactUsAllPage(int pageOn);

	int commodityCommentAllPage(int pageOn);

	int CommentAllPage(int pageOn, int commodityId);

	int salesVolumeAllPage(int pageOn);

}
