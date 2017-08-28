package com.qcj.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qcj.service.PageService;
import com.qcj.service.PermissionService;

@Controller
public class PermissionController {
	static Logger log = Logger.getLogger(PermissionController.class);
	
	@Resource
	PermissionService permissionService;
	
	@Resource
	PageService pageService;
	
	int pageOn = 2;//每页多少条数据
	
	@RequestMapping("/findPermissionList")
	public ModelAndView findPermissionList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;//初始化第一页
		String pageNumberX = request.getParameter("pageNumberX");
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		log.info("now pagenumber is:"+pageNumber);
		int permissionPageAll = pageService.permissionAllPage(pageOn);//查询每页两条数据一共多少页
		if(pageNumber > permissionPageAll) {
			pageNumber = permissionPageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		//List<Permission> list = permissionService.selectPagePermission(pageOn, pageNumber);//查询相关的那页
		List<Map<String, Object>> list = permissionService.queryPermission(pageOn, pageNumber);
		mv.setViewName("shopWMS/view/findPermissionList");
		mv.addObject("pageAll",permissionPageAll);
		mv.addObject("pageNumberX",pageNumber);
		mv.addObject("permissionList",list);
		return mv;
	}
	
	@RequestMapping("/updatePermission")
	public ModelAndView updatePermission(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;//初始化第一页
		String pageNumberX = request.getParameter("pageNumberX");
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		log.info("now pagenumber is:"+pageNumber);
		int permissionPageAll = pageService.permissionAllPage(pageOn);//查询每页两条数据一共多少页
		if(pageNumber > permissionPageAll) {
			pageNumber = permissionPageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		//List<Permission> list = permissionService.selectPagePermission(pageOn, pageNumber);//查询相关的那页
		List<Map<String, Object>> list = permissionService.queryPermission(pageOn, pageNumber);
		mv.setViewName("shopWMS/view/updatePermission");
		mv.addObject("pageAll",permissionPageAll);
		mv.addObject("pageNumberX",pageNumber);
		mv.addObject("permissionList",list);
		return mv;
	}
	@RequestMapping("/updatePermissionLike")
	public ModelAndView updatePermissionLike(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;//初始化第一页
		String pageNumberX = request.getParameter("pageNumberX");
		String permissionNameLike = request.getParameter("permissionNameLike");
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		log.info("now pagenumber is:"+pageNumber);
		int permissionPageAll = pageService.permissionAllPage(pageOn,permissionNameLike);//查询每页两条数据一共多少页
		if(pageNumber > permissionPageAll) {
			pageNumber = permissionPageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		//List<Permission> list = permissionService.selectPagePermission(pageOn, pageNumber);//查询相关的那页
		List<Map<String, Object>> list = permissionService.queryPermission(pageOn, pageNumber,permissionNameLike);
		mv.setViewName("shopWMS/view/updatePermission");
		mv.addObject("pageAll",permissionPageAll);
		mv.addObject("permissionNameLike",permissionNameLike);
		mv.addObject("pageNumberX",pageNumber);
		mv.addObject("permissionList",list);
		return mv;
	}
	@RequestMapping("/addpermission")
	public ModelAndView addpermission(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String pname = request.getParameter("pname");
		String purl = request.getParameter("purl");
		String pmessage = request.getParameter("pmessage");
		
		log.info("参数 admin is :" + pname);
		if(permissionService.addPermission(pname,purl,pmessage) != 0) {		
			mv.setViewName("shopWMS/view/updatePermission");
			return mv;
		}else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	@RequestMapping("/deletePermission")
	public ModelAndView deletePermission(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		String permissionId1 = request.getParameter("permissionId");
		int permissionId = Integer.parseInt(permissionId1);
		System.out.println("------------------permissionId"+permissionId);
		String err = "操作成功";
		if(permissionService.deletePermission(permissionId) != 0) {
			mv.addObject("err",err);
			mv.setViewName("shopWMS/view/updatePermission");
			return mv;
		}else {
			err="操作失败";
			mv.addObject("err",err);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	@RequestMapping("/permissionToUpdate")
	public ModelAndView permissionToUpdate(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		String permissionId = request.getParameter("permissionId");
		String permissionName = request.getParameter("permissionName");
		String permissionUrl = request.getParameter("permissionUrl");
		String permissionMessage = request.getParameter("permissionMessage");
		mv.addObject("permissionId",permissionId);
		mv.addObject("permissionName",permissionName);
		mv.addObject("permissionUrl",permissionUrl);
		mv.addObject("permissionMessage",permissionMessage);
		mv.setViewName("shopWMS/view/permissionUpdate");
		return mv;
	}
	
	@RequestMapping("/permissionUpdate")
	public ModelAndView permissionUpdate(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		String permissionId1 = request.getParameter("pid");
		int permissionId = Integer.parseInt(permissionId1);
		String permissionName = request.getParameter("pname");
		String permissionUrl = request.getParameter("purl");
		String permissionMessage = request.getParameter("pmessage");
		System.out.println(" ------controller canshu:"+permissionId+":"+permissionName+":"+permissionUrl+":"+permissionMessage);
		if(permissionService.permissionUpdate(permissionId,permissionName,permissionUrl,permissionMessage) != 0) {
			mv.setViewName("shopWMS/view/updatePermission");
			return mv;
		}else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
}
