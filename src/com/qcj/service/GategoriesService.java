package com.qcj.service;

import java.util.List;
import java.util.Map;

import com.qcj.entity.Gategories;

public interface GategoriesService {
	// 根据分页查询用户(每页多少条数，第几页)
	List<Gategories> selectPageGategories(int pageOn, int pageNumber);

	List<Gategories> selectPageGategories(int pageOn, int pageNumber, String gategoriesNameLike);

	// 增加用户
	int addGategories(Gategories gategories);

	// 修改
	int gategoriesUpdate(int gategoriesId, String gategoriesName, String gategoriesMessage);

	// 根据商品类型查询商品
	List<Map<String, Object>> queryCommodity(Gategories gategories);
	
	int deleteGategories(int gategoriesId);
}
