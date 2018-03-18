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
import com.qcj.entity.Commodity;
import com.qcj.service.AdminService;
import com.qcj.service.CommodityService;
import com.qcj.service.CommoditypictureService;
import com.qcj.service.PageService;

@Controller
public class CommodityController {
	static Logger log = Logger.getLogger(CommodityController.class);
	
	@Resource
	CommodityService commodityService;
	
	@Resource
	CommoditypictureService commoditypictureService;
	
	@Resource
	PageService pageService;
	
	int pageOn = 1;//每页多少条数据
	
	//模糊查询
	@RequestMapping("/updateCommodityLike")
	public ModelAndView updateCommodityLike(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;//初始化是第一页
		String pageNumberX = request.getParameter("pageNumberX");
		String gategoriesId1 = request.getParameter("gategoriesId");//得到的模糊查询条件 
		String commodityNameLike = request.getParameter("commodityNameLike");//得到的模糊查询条件
		int gategoriesId = Integer.parseInt(gategoriesId1);
		System.out.println("模糊查询条件："+commodityNameLike);
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		log.info("now pagenumber is:"+pageNumber);
		int commodityPageAll =  pageService.commodityPageAll(pageOn,commodityNameLike,gategoriesId);//查询没页1条数据一共多少页面
		System.out.println("模糊查询返回的adminPageAll："+commodityPageAll);
		if(pageNumber > commodityPageAll) {
			pageNumber = commodityPageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("模糊查询how much page:"+commodityPageAll);
		List<Commodity> list = commodityService.selectPageCommodity(pageOn, pageNumber,commodityNameLike,gategoriesId);//查询相关的那一页
		//log.info("模糊查询list size:"+list.get(0).getCommodityId());
		mv.setViewName("shopWMS/view/gategoriesAndCommodity");
		mv.addObject("commodityNameLike",commodityNameLike);
		mv.addObject("gategoriesId",gategoriesId);
		mv.addObject("pageAll",commodityPageAll);
		mv.addObject("pageNumberX",pageNumber);//当前是第几页
		mv.addObject("clist",list);//查询到的结果集
		return mv;
	}
	
	
	//模糊查询
	@RequestMapping("/addCommoditys")
	public ModelAndView addCommoditys(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String gategoriesId = request.getParameter("gategoriesId");//得到的模糊查询条件 
		mv.setViewName("shopWMS/view/addCommodity");
		mv.addObject("gategoriesId",gategoriesId);
		return mv;
	}
	
	//模糊查询
	@RequestMapping("/addCommodity")
	public ModelAndView addCommodity(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String gategoriesId1 = request.getParameter("gategoriesId");
		
		int gategoriesId = Integer.parseInt(gategoriesId1);
		String commodityName = request.getParameter("commodityName");
		String commodityPrice1 = request.getParameter("commodityPrice");
		float commodityPrice = Integer.parseInt(commodityPrice1);
		String commodityIsMan = request.getParameter("commodityIsMan");
		String commodityIsShelves = request.getParameter("commodityIsShelves");
		String commoditySize = request.getParameter("commoditySize");
		Commodity commodity= new Commodity();
		commodity.setGategoriesId(gategoriesId);
		commodity.setCommodityName(commodityName);
		commodity.setCommodityPrice(commodityPrice);
		commodity.setCommodityIsMan(commodityIsMan);
		commodity.setCommodityIsShelves(commodityIsShelves);
		commodity.setCommoditySize(commoditySize);
		log.info("参数 commodity is :" + commodity);
		if(commodityService.addCommodity(commodity) != 0) {		
			mv.setViewName("shopWMS/view/gategoriesAndCommodity");
			return mv;
		}else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	
	@RequestMapping("/commodityToUpdate")
	public ModelAndView commodityToUpdate(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String commodityId = request.getParameter("commodityId");
		String gategoriesId = request.getParameter("gategoriesId");
		String commodityName = request.getParameter("commodityName");
		String commodityPrice = request.getParameter("commodityPrice");
		String commodityIsMan = request.getParameter("commodityIsMan");
		String commodityIsShelves = request.getParameter("commodityIsShelves");
		String commoditySize = request.getParameter("commoditySize");
		mv.addObject("commodityId",commodityId);
		mv.addObject("gategoriesId",gategoriesId);
		mv.addObject("commodityName",commodityName);
		mv.addObject("commodityPrice",commodityPrice);
		mv.addObject("commodityIsMan",commodityIsMan);
		mv.addObject("commodityIsShelves",commodityIsShelves);
		mv.addObject("commoditySize",commoditySize);
		System.out.println("commoditytoupdate:--"+commodityId+":"+gategoriesId);
		mv.setViewName("shopWMS/view/commodityUpdate");
		return mv;
	}
	
	@RequestMapping("/commodityUpdate")
	public ModelAndView commodityUpdate(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String commodityId1 = request.getParameter("commodityId");
		int commodityId = Integer.parseInt(commodityId1);
		String gategoriesId1 = request.getParameter("gategoriesId");
		int gategoriesId = Integer.parseInt(gategoriesId1);
		String commodityName = request.getParameter("commodityName");
		String commodityPrice = request.getParameter("commodityPrice");
		String commodityIsMan = request.getParameter("commodityIsMan");
		String commodityIsShelves = request.getParameter("commodityIsShelves");
		String commoditySize = request.getParameter("commoditySize");
		if(commodityService.commodityUpdate(commodityId,commodityName,commodityPrice,commodityIsMan,commodityIsShelves,commoditySize) != 0) {
			mv.setViewName("shopWMS/view/updateGategories");
			return mv;
		}else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	@RequestMapping("/upDownCommodity")
	public ModelAndView upDownCommodity(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String commodityId1 = request.getParameter("commodityId");
		int commodityId = Integer.parseInt(commodityId1);
		String commodityIsShelves = request.getParameter("commodityIsShelves");
		if(commodityService.upDownCommodity(commodityId,commodityIsShelves) != 0) {
			mv.setViewName("shopWMS/view/updateGategories");
			return mv;
		}else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	
	@RequestMapping("/deleteCommodity")
	public ModelAndView deleteCommodity(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String commodityId1 = request.getParameter("commodityId");
		int commodityId = Integer.parseInt(commodityId1);
		if(commoditypictureService.deleteCommoditypictures(commodityId) != 0){
			System.out.println("商品所有图片删除成功");
			if(commodityService.deleteCommodity(commodityId) != 0) {
				mv.setViewName("shopWMS/view/commoditypictureUpdate");
				return mv;
			}else {
				String deleteCommoditypictureErr = "操作失败";
				mv.addObject("deleteCommoditypictureErr",deleteCommoditypictureErr);
				mv.setViewName("shopWMS/view/errorPage");
				return mv;
			}
		}else {
			String deleteCommodityErr = "操作失败";
			mv.addObject("deleteCommodityErr",deleteCommodityErr);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
		
		
	}

}
