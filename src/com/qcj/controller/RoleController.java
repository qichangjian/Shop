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

import com.qcj.entity.Role;
import com.qcj.service.PageService;
import com.qcj.service.RoleService;

@Controller
public class RoleController {
	static Logger log = Logger.getLogger(RoleController.class);
	
	@Resource
	RoleService roleService;
	@Resource
	PageService pageService;
	
	int pageOn = 1;//每页多少条数据
	
	@RequestMapping("/findRoleList")
	public ModelAndView findRoleList(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;//初始化是第一页
		String pageNumberX = request.getParameter("pageNumberX");
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		int rolePageAll = pageService.roleAllPage(pageOn);//返回一共多少页
		System.out.println("role多少页："+rolePageAll);
		if(pageNumber > rolePageAll) {
			pageNumber = rolePageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("how much rolePage :" + rolePageAll);
		List<Role> list = roleService.selectPageRole(pageOn, pageNumber);//查询相关的那一页
		log.info("list size:"+list.get(0).getRid());
		mv.setViewName("shopWMS/view/findRoleList");
		mv.addObject("pageAll",rolePageAll);
		mv.addObject("pageNumberX",pageNumber);
		mv.addObject("RoleList",list);
		return mv;
	}
	
	@RequestMapping("/findRolePermission")
	public ModelAndView findRolePermission(HttpServletRequest request,HttpServletResponse response) {
		  ModelAndView mv = new ModelAndView();
	        String roleId1 = request.getParameter("roleId");
	        String roleName = request.getParameter("roleName");
	        int roleId = Integer.parseInt(roleId1);
	        Role role1 = new Role();
	        role1.setRid(roleId);
		    log.info("----传输过来的roleId："+roleId);
			String errtip = "该用户没有任何权限";
			List<Map<String, Object>> plist = roleService.queryPermission(role1);
			if( plist.size() == 0) {	
				System.out.println("进入判断语句了");
				mv.addObject("roleName",roleName);
				mv.addObject("errtip1",errtip);		
				mv.setViewName("shopWMS/view/findRolePermission");
				return mv;
		}else {	
			for (int i = 0; i < plist.size(); i++) {
				log.info("Plist:"+plist.get(i));
			
			mv.addObject("plist1",plist);
			mv.addObject("roleName",roleName);
			mv.setViewName("shopWMS/view/findRolePermission");
			}
		}
			return mv;
	}
	@RequestMapping("/updateRole")
	public ModelAndView updateRole(HttpServletRequest request,HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;//初始化是第一页
		String pageNumberX = request.getParameter("pageNumberX");
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		int rolePageAll = pageService.roleAllPage(pageOn);//返回一共多少页
		System.out.println("role多少页："+rolePageAll);
		if(pageNumber > rolePageAll) {
			pageNumber = rolePageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("how much rolePage :" + rolePageAll);
		List<Role> list = roleService.selectPageRole(pageOn, pageNumber);//查询相关的那一页
		log.info("list size:"+list.get(0).getRid());
		mv.setViewName("shopWMS/view/updateRole");
		mv.addObject("pageAll",rolePageAll);
		mv.addObject("pageNumberX",pageNumber);
		mv.addObject("RoleList",list);
		return mv;
	}
	@RequestMapping("/updateRoleLike")
	public ModelAndView updateRoleLike(HttpServletRequest request,HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;//初始化是第一页
		String pageNumberX = request.getParameter("pageNumberX");
		String roleNameLike = request.getParameter("roleNameLike");//得到的模糊查询条件
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		int rolePageAll = pageService.roleAllPage(pageOn,roleNameLike);//返回一共多少页
		System.out.println("role多少页："+rolePageAll);
		if(pageNumber > rolePageAll) {
			pageNumber = rolePageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("how much rolePage :" + rolePageAll);
		List<Role> list = roleService.selectPageRole(pageOn, pageNumber,roleNameLike);//查询相关的那一页
		log.info("list size:"+list.get(0).getRid());
		mv.setViewName("shopWMS/view/updateRole");
		mv.addObject("pageAll",rolePageAll);
		mv.addObject("roleNameLike",roleNameLike);
		mv.addObject("pageNumberX",pageNumber);
		mv.addObject("RoleList",list);
		return mv;
	}
	@RequestMapping("/addrole")
	public ModelAndView addrole(HttpServletRequest request,HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		String rname = request.getParameter("rname");
		String rmessage = request.getParameter("rmessage");
		Role role = new Role();
		role.setRname(rname);
		role.setRmessage(rmessage);
	
		log.info("参数 role is :" + role);
		if(roleService.addRole(role) != 0) {		
			mv.setViewName("shopWMS/view/updateRole");
			return mv;
		}else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	@RequestMapping("/roleToUpdate")
	public ModelAndView roleToUpdate(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView();
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		String roleMessage = request.getParameter("roleMessage");
		mv.addObject("roleId",roleId);
		mv.addObject("roleName",roleName);
		mv.addObject("roleMessage",roleMessage);
		mv.setViewName("shopWMS/view/roleUpdate");
		return mv;
	}
	
	@RequestMapping("/roleUpdate")
	public ModelAndView roleUpdate(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String roleId1 = request.getParameter("rid");
		int roleId = Integer.parseInt(roleId1);
		String roleName = request.getParameter("rname");
		String roleMessage = request.getParameter("rmessage");
		if(roleService.roleUpdate(roleId,roleName,roleMessage) != 0) {
			mv.setViewName("shopWMS/view/updateRole");
			return mv;
		}else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	@RequestMapping("/deleteRole")
	public ModelAndView deleteRole(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String roleId1 = request.getParameter("roleId");
		int roleId = Integer.parseInt(roleId1);
		String err = "操作成功";
		if(roleService.deleteRole(roleId) != 0) {
			mv.addObject("err",err);
			mv.setViewName("shopWMS/view/updateRole");
			return mv;
		}else {
			err="操作失败";
			mv.addObject("err",err);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	//该角色赋予权限，和给角色删除权限
	@RequestMapping("/roleAndPermission")
	public ModelAndView roleAndPermission(HttpServletRequest request,HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView();
        String roleId1 = request.getParameter("roleId");
        String roleName = request.getParameter("roleName");
        int roleId = Integer.parseInt(roleId1);
        Role role1 = new Role();
        role1.setRid(roleId);
        String errtip = "该角色什么权限也没有！";
        String errtip1 = "该角色所有权限都具有";
		List<Map<String, Object>> plist = roleService.queryPermission(role1);//查询该用户据有的权限----下边是查询该用户没有的权限
		List<Map<String, Object>> noplist = roleService.queryNoPermission(role1);
		System.out.println("plist------"+plist.size()+"+"+plist);
		if( plist.size() == 0) {	
				System.out.println("进入判断语句了1");
				mv.addObject("errtip",errtip);		
				mv.addObject("noplist",noplist);
				mv.setViewName("shopWMS/view/roleAndPermission");
				return mv;
		}else {	
			for (int i = 0; i < plist.size(); i++) {
				System.out.println("不是空进入这里");
				mv.addObject("plists",plist);
				mv.addObject("roleName",roleName);
				mv.addObject("roleId", roleId);
				if(noplist.size() == 0) {
					System.out.println("进入判断语句了1");
					mv.addObject("errtip1",errtip1);
				}else {
					mv.addObject("noplist",noplist);
				}	
				mv.setViewName("shopWMS/view/roleAndPermission");	
			}
		}
		return mv;
	}
	
	@RequestMapping("/deleteRolePermission")
	public ModelAndView deleteRolePermission(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String roleId1 = request.getParameter("roleId");
		String permissionId1 = request.getParameter("permissionId");
		int roleId = Integer.parseInt(roleId1);
		int permissionId = Integer.parseInt(permissionId1);
		String deleteRolePermission =  "操作成功";
		if(roleService.deleteRolePermission(roleId,permissionId) != 0) {
		/*	mv.addObject("deleteRolePermission",deleteRolePermission);*/
			mv.setViewName("shopWMS/view/updateRole");
			return mv;
		}else {
			deleteRolePermission = "deleteRolePermission操作失败";
			mv.addObject("deleteRolePermission",deleteRolePermission);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	@RequestMapping("/addRolePermission")
	public ModelAndView addRolePermission(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String roleId1 = request.getParameter("roleId");
		String permissionId1 = request.getParameter("permissionId");
		int roleId = Integer.parseInt(roleId1);
		int permissionId = Integer.parseInt(permissionId1);
		String addRolePermission = "操作成功";
		System.out.println("add---"+roleId+":"+permissionId);
		if(roleService.addRolePermission(roleId,permissionId) != 0) {
			mv.setViewName("shopWMS/view/updateRole");
			return mv;
		}else {
			addRolePermission = "addRolePermission操作失败";
			mv.addObject("addRolePermission",addRolePermission);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
}
