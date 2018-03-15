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
}
