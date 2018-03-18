package com.qcj.dao;

import java.util.List;

import com.qcj.entity.Commodity;

public interface CommodityDao {
	int allCountNum(String commodityNameLike,int gategoriesId);
	
	List<Commodity> selectPageCommodity(int pageOn,int pageNumber,String commodityNameLike,int gategoriesId);
	
	int addCommodity(Commodity commodity);
	
	int commodityUpdate(int commodityId,String commodityName,String commodityPrice,String commodityIsMan,String commodityIsShelves,String commoditySize);
	
	//上下架
	int upDownCommodity(int commodityId,String commodityIsShelves);
	
	int deleteCommodity(int commodityId);
}
