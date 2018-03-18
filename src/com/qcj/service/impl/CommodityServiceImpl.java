package com.qcj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qcj.dao.CommodityDao;
import com.qcj.entity.Commodity;
import com.qcj.service.CommodityService;
@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
	@Resource
	CommodityDao commodityDao;

	@Override
	public List<Commodity> selectPageCommodity(int pageOn, int pageNumber, String commodityNameLike, int gategoriesId) {
		return commodityDao.selectPageCommodity(pageOn, pageNumber, commodityNameLike, gategoriesId);
	}

	@Override
	public int addCommodity(Commodity commodity) {
		return commodityDao.addCommodity(commodity);
	}

	@Override
	public int commodityUpdate(int commodityId, String commodityName, String commodityPrice, String commodityIsMan,
			String commodityIsShelves,String commoditySize) {
		return commodityDao.commodityUpdate(commodityId, commodityName, commodityPrice, commodityIsMan, commodityIsShelves,commoditySize);
	}

	@Override
	public int upDownCommodity(int commodityId, String commodityIsShelves) {
		return commodityDao.upDownCommodity(commodityId, commodityIsShelves);
	}

	@Override
	public int deleteCommodity(int commodityId) {
		return commodityDao.deleteCommodity(commodityId);
	}

}
