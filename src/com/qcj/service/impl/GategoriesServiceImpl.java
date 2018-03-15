package com.qcj.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qcj.dao.GategoriesDao;
import com.qcj.entity.Gategories;
import com.qcj.service.GategoriesService;

@Service("gategoriesService")
public class GategoriesServiceImpl implements GategoriesService {
	@Resource
	GategoriesDao gategoriesDao;

	@Override
	public List<Gategories> selectPageGategories(int pageOn, int pageNumber) {
		return gategoriesDao.selectPageGategories(pageOn, pageNumber);
	}

	@Override
	public int addGategories(Gategories gategories) {
		return gategoriesDao.addGategories(gategories);
	}

	@Override
	public List<Gategories> selectPageGategories(int pageOn, int pageNumber, String gategoriesNameLike) {
		return gategoriesDao.selectPageGategories(pageOn, pageNumber, gategoriesNameLike);
	}

	@Override
	public int gategoriesUpdate(int gategoriesId, String gategoriesName, String gategoriesMessage) {
		return gategoriesDao.gategoriesUpdate(gategoriesId, gategoriesName, gategoriesMessage);
	}

	@Override
	public List<Map<String, Object>> queryCommodity(Gategories gategories) {
		return gategoriesDao.queryCommodity(gategories);
	}
	
	
}
