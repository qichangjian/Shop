package com.qcj.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qcj.entity.Admin;
import com.qcj.entity.Commoditypicture;
import com.qcj.service.CommodityService;
import com.qcj.service.CommoditypictureService;
import com.qcj.service.PageService;

@Controller
public class CommoditypictureController {
static Logger log = Logger.getLogger(CommoditypictureController.class);
	
	@Resource
	CommoditypictureService commoditypictureService;
	
	@Resource
	PageService pageService;
	
	int pageOn = 1;//每页多少条数据
		
	//查询
	@RequestMapping("/commoditypictureToUpdate")
	public ModelAndView commoditypictureToUpdate(HttpServletRequest request,HttpServletResponse response) {
		String fpath = request.getSession().getServletContext().getRealPath("/file");
		System.out.println("path:----------" + fpath);
		ModelAndView mv = new ModelAndView();
		String commodityId1 = request.getParameter("commodityId");
		int commodityId = Integer.parseInt(commodityId1);
		List<Commoditypicture> list = commoditypictureService.selectCommoditypicture(commodityId);//查询相关的那一页
		String path = "..//" + list.get(0).getCpURL();
		mv.setViewName("shopWMS/view/commoditypictureUpdate");
		mv.addObject("commoditypictureList",list);//查询到的结果集
		mv.addObject("path",path);//查询到的结果集
		return mv;
	}
}
