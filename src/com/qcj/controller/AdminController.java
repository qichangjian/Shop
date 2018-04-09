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
import com.qcj.entity.Admin;
import com.qcj.service.AdminService;
import com.qcj.service.PageService;

@Controller
public class AdminController {
	static Logger log = Logger.getLogger(AdminController.class);

	@Resource
	AdminService adminService;

	@Resource
	PageService pageService;

	int pageOn = 1;// 每页多少条数据

	@RequestMapping("/adminlogin")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Admin admin) {

		ModelAndView mv = new ModelAndView();
		Admin admin1 = new Admin();
		admin1 = adminService.checkAdmin(admin);
		log.info("-------------登陆查询验证返回的结果：" + admin1);
		if (admin1 != null) {
			List<Map<String, Object>> plist = adminService.queryPermission(admin1);// 在添加列表呈现之后

			for (int i = 0; i < plist.size(); i++) {
				log.info("Plist:" + plist.get(i));
			}
			mv.addObject("plist", plist);
			mv.setViewName("shopWMS/view/adminMain");
			request.getSession().setAttribute("plist", plist);
			request.getSession().setAttribute("admin", admin1);
			return mv;
		} else {
			log.info("---------输入错误----------");
			int err = 1;
			mv.addObject("err", err);
			mv.setViewName("adminLogin");
			return mv;
		}
	}

	@RequestMapping("/findAdminList")
	public ModelAndView findAdminList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;// 初始化是第一页
		String pageNumberX = request.getParameter("pageNumberX");
		if (pageNumberX != null  && pageNumberX != "") {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		log.info("now pagenumber is:" + pageNumber);
		int adminPageAll = pageService.adminAllPage(pageOn);// 查询没页1条数据一共多少页面
		if (pageNumber > adminPageAll) {
			pageNumber = adminPageAll;
		}
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("how much page:" + adminPageAll);
		List<Admin> list = adminService.selectPageAdmin(pageOn, pageNumber);// 查询相关的那一页
		log.info("list size:" + list.get(0).getAid());
		mv.setViewName("shopWMS/view/findAdminList");
		mv.addObject("pageAll", adminPageAll);
		mv.addObject("pageNumberX", pageNumber);// 当前是第几页
		mv.addObject("AdminsList", list);// 查询到的结果集
		return mv;
	}

	@RequestMapping("/findAdminPermission")
	public ModelAndView findAdminPermission(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String adminId1 = request.getParameter("adminId");
		String adminName = request.getParameter("adminName");
		int adminId = Integer.parseInt(adminId1);
		Admin admin1 = new Admin();
		admin1.setAid(adminId);
		log.info("----传输过来的adminId：" + adminId);

		List<Map<String, Object>> plist = adminService.queryPermission(admin1);
		if (plist.size() != 0) {
			for (int i = 0; i < plist.size(); i++) {

				mv.addObject("plist0", plist);
				mv.addObject("adminName", adminName);
				mv.setViewName("shopWMS/view/findAdminPermission");
			}
		} else {
			String tip = "该用户没有权限";
			mv.addObject("permissionTip", tip);
			mv.addObject("adminName", adminName);
			/*
			 * String tipmy = "正在登陆的管理员具有权限："; mv.addObject("tipmy",tipmy);
			 */
			mv.setViewName("shopWMS/view/findAdminPermission");
		}

		return mv;
	}

	@RequestMapping("/updateAdmin")
	public ModelAndView updateAdmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;// 初始化是第一页
		String pageNumberX = request.getParameter("pageNumberX");
		if (pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		log.info("now pagenumber is:" + pageNumber);
		int adminPageAll = pageService.adminAllPage(pageOn);// 查询没页1条数据一共多少页面
		if (pageNumber > adminPageAll) {
			pageNumber = adminPageAll;
		}
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("how much page:" + adminPageAll);
		List<Admin> list = adminService.selectPageAdmin(pageOn, pageNumber);// 查询相关的那一页
		log.info("list size:" + list.get(0).getAid());
		mv.setViewName("shopWMS/view/updateAdmin");
		mv.addObject("pageAll", adminPageAll);
		mv.addObject("pageNumberX", pageNumber);// 当前是第几页
		mv.addObject("AdminsList", list);// 查询到的结果集
		return mv;
	}

	// 模糊查询
	@RequestMapping("/updateAdminLike")
	public ModelAndView updateAdminLike(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;// 初始化是第一页
		String pageNumberX = request.getParameter("pageNumberX");
		String adminNameLike = request.getParameter("adminNameLike");// 得到的模糊查询条件
		System.out.println("模糊查询条件：" + adminNameLike);
		if (pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		log.info("now pagenumber is:" + pageNumber);
		int adminPageAll = pageService.adminAllPage(pageOn, adminNameLike);// 查询没页1条数据一共多少页面
		System.out.println("模糊查询返回的adminPageAll：" + adminPageAll);
		if (pageNumber > adminPageAll) {
			pageNumber = adminPageAll;
		}
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("模糊查询how much page:" + adminPageAll);
		List<Admin> list = adminService.selectPageAdmin(pageOn, pageNumber, adminNameLike);// 查询相关的那一页
		//log.info("模糊查询list size:" + list.get(0).getAid());
		mv.setViewName("shopWMS/view/updateAdmin");
		mv.addObject("adminNameLike", adminNameLike);
		mv.addObject("pageAll", adminPageAll);
		mv.addObject("pageNumberX", pageNumber);// 当前是第几页
		mv.addObject("AdminsList", list);// 查询到的结果集
		return mv;
	}

	@RequestMapping("/addAdmin")
	public ModelAndView addAdmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String aname = request.getParameter("aname");
		String apassword = request.getParameter("apassword");
		String amessage = request.getParameter("amessage");
		Admin admin = new Admin();
		admin.setAname(aname);
		admin.setApassword(apassword);
		admin.setAmessage(amessage);
		log.info("参数 admin is :" + admin);
		if (adminService.addAdmin(admin) != 0) {
			mv.setViewName("forward:findAdminList");
			return mv;
		} else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}

	@RequestMapping("/deleteAdmin")
	public ModelAndView deleteAdmin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String adminId1 = request.getParameter("adminId");
		int adminId = Integer.parseInt(adminId1);
		if (adminService.deleteAdmin(adminId) != 0) {
			mv.setViewName("forward:updateAdmin");
			return mv;
		} else {
			String deleteAdminErr = "操作失败";
			mv.addObject("deleteAdminErr", deleteAdminErr);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}

	@RequestMapping("/adminToUpdate")
	public ModelAndView adminToUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String adminId = request.getParameter("adminId");
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		String adminMessage = request.getParameter("adminMessage");
		mv.addObject("adminId", adminId);
		mv.addObject("adminName", adminName);
		mv.addObject("adminPassword", adminPassword);
		mv.addObject("adminMessage", adminMessage);
		System.out.println("admintoupdate:--" + adminId + ":" + adminName);
		mv.setViewName("shopWMS/view/adminUpdate");
		return mv;
	}

	@RequestMapping("/adminUpdate")
	public ModelAndView adminUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String adminId1 = request.getParameter("aid");
		int adminId = Integer.parseInt(adminId1);
		String adminName = request.getParameter("aname");
		String adminPassword = request.getParameter("apassword");
		String adminMessage = request.getParameter("amessage");
		if (adminService.adminUpdate(adminId, adminName, adminPassword, adminMessage) != 0) {
			mv.setViewName("forward:updateAdmin");
			return mv;
		} else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}

	@RequestMapping("/adminAndRole")
	public ModelAndView adminAndRole(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String adminId1 = request.getParameter("adminId");
		String adminName = request.getParameter("adminName");
		System.out.println("admin 参数：--" + adminId1 + ":" + adminName);
		int adminId = Integer.parseInt(adminId1);
		Admin admin1 = new Admin();
		admin1.setAid(adminId);
		String rerrtip = "该管理员什么角色也没有！";
		String rerrtip1 = "该角色所有的角色都具有";
		List<Map<String, Object>> rlist = adminService.queryRole(admin1);
		List<Map<String, Object>> norlist = adminService.queryNoRole(admin1);
		System.out.println("admin rlist 返回：" + rlist + ":norList:" + norlist);
		if (rlist.size() == 0) {
			System.out.println("进入判断语句了1");
			mv.addObject("rerrtip", rerrtip);
			mv.addObject("norlist", norlist);
			mv.addObject("adminId",adminId);
			mv.setViewName("shopWMS/view/adminAndRole");
			return mv;
		} else {
			for (int i = 0; i < rlist.size(); i++) {
				System.out.println("不是空进入这里");
				mv.addObject("rlists", rlist);
				mv.addObject("adminName", adminName);
				mv.addObject("adminId", adminId);
				if (norlist.size() == 0) {
					System.out.println("进入判断语句了1");
					mv.addObject("rerrtip1", rerrtip1);
				} else {
					mv.addObject("norlist", norlist);
				}
				mv.setViewName("shopWMS/view/adminAndRole");
			}
		}
		return mv;
	}

	@RequestMapping("/addAdminRole")
	public ModelAndView addAdminRole(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String adminId1 = request.getParameter("adminId");
		String roleId1 = request.getParameter("roleId");
		int adminId = Integer.parseInt(adminId1);
		int roleId = Integer.parseInt(roleId1);
		String addAdminRole = "操作成功";
		if (adminService.addAdminRole(adminId, roleId) != 0) {
			mv.setViewName("forward:updateAdmin");
			return mv;
		} else {
			addAdminRole = "addAdminRole操作失败";
			mv.addObject("addAdminRole", addAdminRole);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}

	@RequestMapping("/deleteAdminRole")
	public ModelAndView deleteAdminRole(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mv = new ModelAndView();
		String adminId1 = request.getParameter("adminId");
		String roleId1 = request.getParameter("roleId");
		int adminId = Integer.parseInt(adminId1);
		int roleId = Integer.parseInt(roleId1);
		String deleteAdminRole = "操作成功";
		if (adminService.deleteAdminRole(adminId, roleId) != 0) {
			mv.setViewName("forward:updateAdmin");
			return mv;
		} else {
			deleteAdminRole = "deleteAdminRole操作失败";
			mv.addObject("deleteAdminRole", deleteAdminRole);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
}
