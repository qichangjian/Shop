package com.qcj.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qcj.dao.CommoditypictureDao;
import com.qcj.entity.Commoditypicture;
import com.qcj.service.CommoditypictureService;


@Service("commoditypictureService")
public class CommoditypictureServiceImpl implements CommoditypictureService {
	@Resource
	CommoditypictureDao commoditypictureDao;

	@Override
	public List<Commoditypicture> selectCommoditypicture(int commodityId) {
		return commoditypictureDao.selectCommoditypicture(commodityId);
	}

	@Override
	public int addCommoditypicture(Commoditypicture commoditypicture) {
		return commoditypictureDao.addCommoditypicture(commoditypicture);
	}

	@Override
	public int deleteCommoditypicture(int cpId) {
		return commoditypictureDao.deleteCommoditypicture(cpId);
	}

	@Override
	public int deleteCommoditypictures(int commodityId) {
		return commoditypictureDao.deleteCommoditypictures(commodityId);
	}
}
