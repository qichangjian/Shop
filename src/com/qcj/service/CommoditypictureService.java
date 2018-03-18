package com.qcj.service;

import java.util.List;

import com.qcj.entity.Commoditypicture;

public interface CommoditypictureService {
	List<Commoditypicture> selectCommoditypicture(int commodityId);
	int addCommoditypicture(Commoditypicture commoditypicture);
	int deleteCommoditypicture(int cpId);
	int deleteCommoditypictures(int commodityId);
}
