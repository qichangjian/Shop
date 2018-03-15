package com.qcj.dao;

import java.util.List;

import com.qcj.entity.Commoditypicture;

public interface CommoditypictureDao {
	List<Commoditypicture> selectCommoditypicture(int commodityId);
}
