package com.qcj.dao;

import java.util.List;


import com.qcj.entity.Commoditypicture;

public interface CommoditypictureDao {
	List<Commoditypicture> selectCommoditypicture(int commodityId);
	
	int addCommoditypicture(Commoditypicture commoditypicture);
	
	int deleteCommoditypicture(int cpId);
	
	int deleteCommoditypictures(int commodityId);
}
