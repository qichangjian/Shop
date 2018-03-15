package com.qcj.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qcj.entity.User;
import com.qcj.service.PageService;
import com.qcj.service.UserService;

@Controller
public class UserController {
	static Logger log = Logger.getLogger(RoleController.class);
	
	@Resource
	UserService userService;
	
	@Resource
	PageService pageService;
	
	int pageOn = 1;//每页多少条数据
	
	@RequestMapping("/findUserList")
	public ModelAndView findUserList(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;
		String pageNumberX = request.getParameter("pageNumberX");
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		int userPageAll = pageService.userAllPage(pageOn);//返回一共多少页
		if(pageNumber > userPageAll) {
			pageNumber = userPageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("how much page:"+userPageAll);
		List<User> list = userService.selectPageUser(pageOn, pageNumber);//查询相关的那一页
		log.info("list size:"+list.get(0).getUserId());
		mv.setViewName("shopWMS/view/findUserList");
		mv.addObject("pageAll",userPageAll);
		mv.addObject("pageNumberX",pageNumber);//当前是第几页
		mv.addObject("UserList",list);//查询到的结果集	
		return mv;
	}
	
	@RequestMapping("/updateUser")
	public ModelAndView updateAdmin(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;
		String pageNumberX = request.getParameter("pageNumberX");
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		int userPageAll = pageService.userAllPage(pageOn);//返回一共多少页
		if(pageNumber > userPageAll) {
			pageNumber = userPageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("how much page:"+userPageAll);
		List<User> list = userService.selectPageUser(pageOn, pageNumber);//查询相关的那一页
		log.info("list size:"+list.get(0).getUserId());
		mv.setViewName("shopWMS/view/updateUser");
		mv.addObject("pageAll",userPageAll);
		mv.addObject("pageNumberX",pageNumber);//当前是第几页
		mv.addObject("UserList",list);//查询到的结果集	
		return mv;
	}
	
	@RequestMapping("/addUser")
	public ModelAndView addUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		String userPhone = request.getParameter("userPhone");
		User user= new User();
		user.setUserName(userName);
		user.setUserEmail(userEmail);
		user.setUserPassword(userPassword);
		user.setUserPhone(userPhone);
		log.info("参数user is :" + user);
		if(userService.addUser(user) != 0) {		
			/*mv.setViewName("shopWMS/view/findUserList");*/
			mv.setViewName("forward:findUserList");
			return mv;
		}else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String userId1 = request.getParameter("userId");
		int userId = Integer.parseInt(userId1);
		if(userService.deleteUser(userId) != 0) {
			mv.setViewName("forward:updateUser");
			return mv;
		}else {
			String deleteUserErr = "操作失败";
			mv.addObject("deleteUserErr",deleteUserErr);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
	//模糊查询
	@RequestMapping("/updateUserLike")
	public ModelAndView updateUserLike(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;//初始化是第一页
		String pageNumberX = request.getParameter("pageNumberX");
		String userNameLike = request.getParameter("userNameLike");//得到的模糊查询条件
		System.out.println("模糊查询条件："+userNameLike);
		if(pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		log.info("now pagenumber is:"+pageNumber);
		int userPageAll =  pageService.userAllPage(pageOn,userNameLike);//查询没页1条数据一共多少页面
		System.out.println("模糊查询返回的userPageAll："+userPageAll);
		if(pageNumber > userPageAll) {
			pageNumber = userPageAll;
		}
		if(pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("模糊查询how much page:"+userPageAll);
		List<User> list = userService.selectPageUser(pageOn, pageNumber,userNameLike);//查询相关的那一页
		log.info("模糊查询list size:"+list.get(0).getUserId());
		mv.setViewName("shopWMS/view/updateUser");
		mv.addObject("userNameLike",userNameLike);
		mv.addObject("pageAll",userPageAll);
		mv.addObject("pageNumberX",pageNumber);//当前是第几页
		mv.addObject("UserList",list);//查询到的结果集
		return mv;
	}
	
	@RequestMapping("/userToUpdate")
	public ModelAndView userToUpdate(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		String userPhone = request.getParameter("userPhone");
		mv.addObject("userId",userId);
		mv.addObject("userName",userName);
		mv.addObject("userEmail",userEmail);
		mv.addObject("userPassword",userPassword);
		mv.addObject("userPhone",userPhone);
		System.out.println("usertoupdate:--"+userId+":"+userName);
		mv.setViewName("shopWMS/view/userUpdate");
		return mv;
	}
	
	@RequestMapping("/userUpdate")
	public ModelAndView userUpdate(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String userId1 = request.getParameter("userId");
		int userId = Integer.parseInt(userId1);
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");
		String userPhone = request.getParameter("userPhone");
		if(userService.userUpdate(userId,userName,userEmail,userPassword,userPhone) != 0) {
			mv.setViewName("shopWMS/view/updateUser");
			return mv;
		}else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
}
