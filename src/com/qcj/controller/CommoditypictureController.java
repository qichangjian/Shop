package com.qcj.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import com.qcj.entity.Commoditypicture;
import com.qcj.service.CommoditypictureService;
import com.qcj.service.PageService;

@Controller
public class CommoditypictureController {
	static Logger log = Logger.getLogger(CommoditypictureController.class);

	@Resource
	CommoditypictureService commoditypictureService;

	@Resource
	PageService pageService;

	int pageOn = 1;// 每页多少条数据

	// 查询
	@RequestMapping("/commoditypictureToUpdate")
	public ModelAndView commoditypictureToUpdate(HttpServletRequest request, HttpServletResponse response) {
		String fpath = request.getSession().getServletContext().getRealPath("/file");
		System.out.println("commoditypictureToUpdate path:----------" + fpath);
		String commodityId1 = request.getParameter("commodityId");
		int commodityId = Integer.parseInt(commodityId1);
		ModelAndView mv = new ModelAndView();

		List<Commoditypicture> list = commoditypictureService.selectCommoditypicture(commodityId);// 查询相关的那一页
		if (null == list) {
			mv.setViewName("shopWMS/view/commoditypictureUpdate");
			mv.addObject("commodityId", commodityId);// 查询到的结果集
			mv.addObject("commoditypictureList", list);// 查询到的结果集
		} else {
			String path = "file/" + list.get(0).getCpURL();
			mv.setViewName("shopWMS/view/commoditypictureUpdate");
			mv.addObject("commodityId", commodityId);// 查询到的结果集
			mv.addObject("commoditypictureList", list);// 查询到的结果集
			mv.addObject("path", path);// 查询到的结果集
		}
		return mv;
	}

	// 查询
	@RequestMapping("/addCommoditypicture")
	public ModelAndView addCommoditypicture(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String commodityId1 = request.getParameter("commodityId");
		int commodityId = Integer.parseInt(commodityId1);
		String cpPosition = request.getParameter("cpPosition");
		String cpURL = request.getParameter("cpURL");

		Commoditypicture commoditypicture = new Commoditypicture();
		commoditypicture.setCommodityId(commodityId);
		commoditypicture.setCpPosition(cpPosition);
		commoditypicture.setCpURL(cpURL);
		log.info("参数 commoditypicture is :" + commoditypicture);
		if (commoditypictureService.addCommoditypicture(commoditypicture) != 0) {
			mv.setViewName("forward:updateGategories");
			return mv;
		} else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}

	@RequestMapping("/addCommoditypictures")
	public ModelAndView addCommoditypictures(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String commodityId = request.getParameter("commodityId");// 得到的模糊查询条件
		mv.setViewName("shopWMS/view/addCommoditypicture");
		mv.addObject("commodityId", commodityId);
		System.out.println("commodityId:" + commodityId);
		return mv;
	}

	@RequestMapping("/upPictures")
	public ModelAndView upPictures(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		String commodityId = request.getParameter("commodityId");// 得到的模糊查询条件
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 获取支持文件上传的Request对象 MultipartHttpServletRequest
		MultipartHttpServletRequest mtpreq = (MultipartHttpServletRequest) request;
		// 通过 mtpreq 获取文件域中的文件
		MultipartFile file = mtpreq.getFile("file");
		// 通过MultipartFile 对象获取文件的原文件名
		String fileName = file.getOriginalFilename();
		// 生成一个uuid 的文件名
		UUID randomUUID = UUID.randomUUID();
		// 获取文件的后缀名
		int i = fileName.lastIndexOf(".");
		String uuidName = randomUUID.toString() + fileName.substring(i);
		// 获取服务器的路径地址（被上传文件的保存地址）
		// String realPath =
		// request.getSession().getServletContext().getRealPath("/file");
		String realPath = "C:/Users/Admin/git/Shop/WebContent/file";
		// 将路径转化为文件夹 并 判断文件夹是否存在
		File dir = new File(realPath);
		if (!dir.exists()) {
			dir.mkdir();
		}
		// 获取一个文件的保存路径
		String path = realPath + "/" + uuidName;
		System.err.println("-----realpath改了后路径地址为：:" + realPath);
		// 为文件这服务器中开辟一给新的空间,*没有数据
		// File newFile = new File(path);
		try {
			file.transferTo(new File(path));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.err.println("-----服务器的路径地址为：:" + realPath);
		System.err.println("-----图片名称为：:" + fileName);
		System.err.println("-----图片新名称为：:" + uuidName);
		System.err.println("-----图片新路径为：:" + path);

		String newPath = "C:/Users/Admin/git/shoppingCartSys/WebContent/shopCartClient/file";
		copyDir(realPath, newPath);//

		mv.setViewName("shopWMS/view/addCommoditypicture");
		mv.addObject("cpURL", uuidName);
		mv.addObject("commodityId", commodityId);
		return mv;
	}

	public static void copyDir(String oldPath, String newPath) throws IOException {
		File file = new File(oldPath);
		String[] filePath = file.list();

		if (!(new File(newPath)).exists()) {
			(new File(newPath)).mkdir();
		}

		for (int i = 0; i < filePath.length; i++) {
			if ((new File(oldPath + file.separator + filePath[i])).isDirectory()) {
				copyDir(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
			}

			if (new File(oldPath + file.separator + filePath[i]).isFile()) {
				copyFile(oldPath + file.separator + filePath[i], newPath + file.separator + filePath[i]);
			}

		}
	}

	public static void copyFile(String oldPath, String newPath) throws IOException {
		File oldFile = new File(oldPath);
		File file = new File(newPath);
		FileInputStream in = new FileInputStream(oldFile);
		FileOutputStream out = new FileOutputStream(file);
		;

		byte[] buffer = new byte[2097152];

		while ((in.read(buffer)) != -1) {
			out.write(buffer);
		}

	}

	@RequestMapping("/deleteCommoditypicture")
	public ModelAndView deleteCommoditypicture(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String cpId1 = request.getParameter("cpId");
		int cpId = Integer.parseInt(cpId1);
		if (commoditypictureService.deleteCommoditypicture(cpId) != 0) {
			mv.setViewName("forward:updateGategories");
			return mv;
		} else {
			String deleteCommoditypictureErr = "操作失败";
			mv.addObject("deleteCommoditypictureErr", deleteCommoditypictureErr);
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}
	
}
