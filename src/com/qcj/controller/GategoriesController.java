package com.qcj.controller;

import java.io.File;

import java.io.IOException;

import java.util.List;
import java.util.Map;
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

import com.qcj.entity.Commodity;
import com.qcj.entity.CommodityComment;
import com.qcj.entity.Commoditypicture;
import com.qcj.entity.Gategories;
import com.qcj.entity.SalesVolume;
import com.qcj.service.CommodityCommentService;
import com.qcj.service.CommodityService;
import com.qcj.service.CommoditypictureService;
import com.qcj.service.GategoriesService;
import com.qcj.service.PageService;
import com.qcj.service.SalesVolumeService;

@Controller
public class GategoriesController {
	static Logger log = Logger.getLogger(GategoriesController.class);

	@Resource
	GategoriesService gategoriesService;

	@Resource
	CommodityService commodityService;

	@Resource
	CommoditypictureService commoditypictureService;

	@Resource
	CommodityCommentService commodityCommentService;

	@Resource
	SalesVolumeService salesVolumeService;

	@Resource
	PageService pageService;

	int pageOn = 2;// 每页多少条数据

	@RequestMapping("/findGategoriesList")
	public ModelAndView findGategoriesList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;
		String pageNumberX = request.getParameter("pageNumberX");
		if (pageNumberX != null && pageNumberX != "") {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		int gategoriesPageAll = pageService.gategoriesAllPage(pageOn);// 返回一共多少页
		if (pageNumber > gategoriesPageAll) {
			pageNumber = gategoriesPageAll;
		}
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("how much page:" + gategoriesPageAll);
		List<Gategories> list = gategoriesService.selectPageGategories(pageOn, pageNumber);// 查询相关的那一页
		log.info("list size:" + list.get(0).getGategoriesId());
		mv.setViewName("shopWMS/view/findGategoriesList");
		mv.addObject("pageAll", gategoriesPageAll);
		mv.addObject("pageNumberX", pageNumber);// 当前是第几页
		mv.addObject("GategoriesList", list);// 查询到的结果集
		return mv;
	}

	@RequestMapping("/updateGategories")
	public ModelAndView updateGategories(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;
		String pageNumberX = request.getParameter("pageNumberX");
		if (pageNumberX != null && pageNumberX != "") {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		int gategoriesPageAll = pageService.gategoriesAllPage(pageOn);// 返回一共多少页
		if (pageNumber > gategoriesPageAll) {
			pageNumber = gategoriesPageAll;
		}
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("how much page:" + gategoriesPageAll);
		List<Gategories> list = gategoriesService.selectPageGategories(pageOn, pageNumber);// 查询相关的那一页
		log.info("list size:" + list.get(0).getGategoriesId());
		mv.setViewName("shopWMS/view/updateGategories");
		mv.addObject("pageAll", gategoriesPageAll);
		mv.addObject("pageNumberX", pageNumber);// 当前是第几页
		mv.addObject("GategoriesList", list);// 查询到的结果集
		return mv;
	}

	@RequestMapping("/addGategories")
	public ModelAndView addGategories(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String gategoriesName = request.getParameter("gategoriesName");
		String gategoriesMessage = request.getParameter("gategoriesMessage");
		Gategories gategories = new Gategories();
		gategories.setGategoriesName(gategoriesName);
		gategories.setGategoriesMessage(gategoriesMessage);

		log.info("参数gategories is :" + gategories);
		if (gategoriesService.addGategories(gategories) != 0) {
			/* mv.setViewName("shopWMS/view/findUserList"); */
			mv.setViewName("forward:findGategoriesList");
			return mv;
		} else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}

	// 模糊查询
	@RequestMapping("/updateGategoriesLike")
	public ModelAndView updateGategoriesLike(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int pageNumber = 1;// 初始化是第一页
		String pageNumberX = request.getParameter("pageNumberX");
		String gategoriesNameLike = request.getParameter("gategoriesNameLike");// 得到的模糊查询条件
		System.out.println("模糊查询条件：" + gategoriesNameLike);
		if (pageNumberX != null) {
			pageNumber = Integer.parseInt(pageNumberX);
		}
		log.info("now pagenumber is:" + pageNumber);
		int gategoriesPageAll = pageService.gategoriesAllPage(pageOn, gategoriesNameLike);// 查询没页1条数据一共多少页面
		System.out.println("模糊查询返回的gategoriesPageAll：" + gategoriesPageAll);
		if (pageNumber > gategoriesPageAll) {
			pageNumber = gategoriesPageAll;
		}
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		log.info("模糊查询how much page:" + gategoriesPageAll);
		List<Gategories> list = gategoriesService.selectPageGategories(pageOn, pageNumber, gategoriesNameLike);// 查询相关的那一页
		// log.info("模糊查询list size:" + list.get(0).getGategoriesId());
		mv.setViewName("shopWMS/view/updateGategories");
		mv.addObject("gategoriesNameLike", gategoriesNameLike);
		mv.addObject("pageAll", gategoriesPageAll);
		mv.addObject("pageNumberX", pageNumber);// 当前是第几页
		mv.addObject("GategoriesList", list);// 查询到的结果集
		return mv;
	}

	@RequestMapping("/gategoriesToUpdate")
	public ModelAndView gategoriesToUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String gategoriesId = request.getParameter("gategoriesId");
		String gategoriesName = request.getParameter("gategoriesName");
		String gategoriesMessage = request.getParameter("gategoriesMessage");
		mv.addObject("gategoriesId", gategoriesId);
		mv.addObject("gategoriesName", gategoriesName);
		mv.addObject("gategoriesMessage", gategoriesMessage);
		System.out.println("gategoriestoupdate:--" + gategoriesId + ":" + gategoriesName);
		mv.setViewName("shopWMS/view/gategoriesUpdate");
		return mv;
	}

	@RequestMapping("/deleteGategories")
	public ModelAndView deleteGategories(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String gategoriesId1 = request.getParameter("gategoriesId");
		int gategoriesId = Integer.parseInt(gategoriesId1);
		List<Commodity> clist = commodityService.selectCommodityBycId(gategoriesId);
		if (null == clist) {
			if (gategoriesService.deleteGategories(gategoriesId) != 0) {
				mv.setViewName("shopWMS/view/updateGategories");
				return mv;
			} else {
				String deleteGategoriesErr = "操作失败";
				mv.addObject("deleteGategoriesErr", deleteGategoriesErr);
				mv.setViewName("shopWMS/view/errorPage");
				return mv;
			}
		} else {
			// 根据返回的cid挨个删除
			for (Commodity commodity : clist) {
				int commodityId = commodity.getCommodityId();
				// begin
				// 查询看看是否有图片有就执行下边 没有直接删除
				List<Commoditypicture> cplist = commoditypictureService.selectCommoditypicture(commodityId);
				List<CommodityComment> cclist = commodityCommentService.selectCommodityCommentByCid(commodityId);
				List<SalesVolume> svlist = salesVolumeService.selectSalesVolumeBycId(commodityId);
				if (null == cplist && null == cclist && null == svlist) {
					if (commodityService.deleteCommodity(commodityId) != 0) {

					} else {
						String deleteCommoditypictureErr = "操作失败";
						mv.addObject("deleteCommoditypictureErr", deleteCommoditypictureErr);
						mv.setViewName("shopWMS/view/errorPage");
						return mv;
					}
				} else if (null != cplist && null == cclist && null == svlist) {
					if (commoditypictureService.deleteCommoditypictures(commodityId) != 0) {
						System.out.println("商品所有图片删除成功");
						if (commodityService.deleteCommodity(commodityId) != 0) {
						} else {
							String deleteCommoditypictureErr = "操作失败";
							mv.addObject("deleteCommoditypictureErr", deleteCommoditypictureErr);
							mv.setViewName("shopWMS/view/errorPage");
							return mv;
						}
					} else {
						String deleteCommodityErr = "操作失败";
						mv.addObject("deleteCommodityErr", deleteCommodityErr);
						mv.setViewName("shopWMS/view/errorPage");
						return mv;
					}
				} else if (null == cplist && null != cclist && null == svlist) {
					if (commodityCommentService.deleteCommodityCommentbycId(commodityId) != 0) {
						System.out.println("商品评论删除成功");
						if (commodityService.deleteCommodity(commodityId) != 0) {
						} else {
							String deleteCommoditypictureErr = "操作失败";
							mv.addObject("deleteCommoditypictureErr", deleteCommoditypictureErr);
							mv.setViewName("shopWMS/view/errorPage");
							return mv;
						}
					} else {
						String deleteCommodityErr = "操作失败";
						mv.addObject("deleteCommodityErr", deleteCommodityErr);
						mv.setViewName("shopWMS/view/errorPage");
						return mv;
					}
				} else if (null == cplist && null == cclist && null != svlist) {
					if (salesVolumeService.deletesalesVolumebycId(commodityId) != 0) {
						System.out.println("商品销量表删除成功");
						if (commodityService.deleteCommodity(commodityId) != 0) {
						} else {
							String deleteCommoditypictureErr = "操作失败";
							mv.addObject("deleteCommoditypictureErr", deleteCommoditypictureErr);
							mv.setViewName("shopWMS/view/errorPage");
							return mv;
						}
					} else {
						String deleteCommodityErr = "操作失败";
						mv.addObject("deleteCommodityErr", deleteCommodityErr);
						mv.setViewName("shopWMS/view/errorPage");
						return mv;
					}
				} else if (null != cplist && null != cclist && null != svlist) {
					if (commoditypictureService.deleteCommoditypictures(commodityId) != 0
							&& commodityCommentService.deleteCommodityCommentbycId(commodityId) != 0
							&& salesVolumeService.deletesalesVolumebycId(commodityId) != 0) {
						System.out.println("三个关联表删除成功");
						if (commodityService.deleteCommodity(commodityId) != 0) {
						} else {
							String deleteCommoditypictureErr = "操作失败";
							mv.addObject("deleteCommoditypictureErr", deleteCommoditypictureErr);
							mv.setViewName("shopWMS/view/errorPage");
							return mv;
						}
					} else {
						String deleteCommodityErr = "操作失败";
						mv.addObject("deleteCommodityErr", deleteCommodityErr);
						mv.setViewName("shopWMS/view/errorPage");
						return mv;
					}
				} else if (null == cplist && null != cclist && null != svlist) {
					if (commodityCommentService.deleteCommodityCommentbycId(commodityId) != 0
							&& salesVolumeService.deletesalesVolumebycId(commodityId) != 0) {
						System.out.println("两个关联表删除成功");
						if (commodityService.deleteCommodity(commodityId) != 0) {
						} else {
							String deleteCommoditypictureErr = "操作失败";
							mv.addObject("deleteCommoditypictureErr", deleteCommoditypictureErr);
							mv.setViewName("shopWMS/view/errorPage");
							return mv;
						}
					} else {
						String deleteCommodityErr = "操作失败";
						mv.addObject("deleteCommodityErr", deleteCommodityErr);
						mv.setViewName("shopWMS/view/errorPage");
						return mv;
					}
				} else if (null != cplist && null == cclist && null != svlist) {
					if (commoditypictureService.deleteCommoditypictures(commodityId) != 0
							&& salesVolumeService.deletesalesVolumebycId(commodityId) != 0) {
						System.out.println("两个关联表删除成功");
						if (commodityService.deleteCommodity(commodityId) != 0) {
						} else {
							String deleteCommoditypictureErr = "操作失败";
							mv.addObject("deleteCommoditypictureErr", deleteCommoditypictureErr);
							mv.setViewName("shopWMS/view/errorPage");
							return mv;
						}
					} else {
						String deleteCommodityErr = "操作失败";
						mv.addObject("deleteCommodityErr", deleteCommodityErr);
						mv.setViewName("shopWMS/view/errorPage");
						return mv;
					}
				} else if (null != cplist && null != cclist && null == svlist) {
					if (commoditypictureService.deleteCommoditypictures(commodityId) != 0
							&& commodityCommentService.deleteCommodityCommentbycId(commodityId) != 0) {
						System.out.println("两个关联表删除成功");
						if (commodityService.deleteCommodity(commodityId) != 0) {
						} else {
							String deleteCommoditypictureErr = "操作失败";
							mv.addObject("deleteCommoditypictureErr", deleteCommoditypictureErr);
							mv.setViewName("shopWMS/view/errorPage");
							return mv;
						}
					} else {
						String deleteCommodityErr = "操作失败";
						mv.addObject("deleteCommodityErr", deleteCommodityErr);
						mv.setViewName("shopWMS/view/errorPage");
						return mv;
					}
				} else {
					String deleteCommodityErr = "操作失败";
					mv.addObject("deleteCommodityErr", deleteCommodityErr);
					mv.setViewName("shopWMS/view/errorPage");
					return mv;
				}
				// end
			}
			System.out.println("商品类型所有关联表删除成功");
			if (gategoriesService.deleteGategories(gategoriesId) != 0) {
				mv.setViewName("forward:updateGategories");
				return mv;
			} else {
				String deleteGategoriesErr = "操作失败";
				mv.addObject("deleteGategoriesErr", deleteGategoriesErr);
				mv.setViewName("shopWMS/view/errorPage");
				return mv;
			}
		}
	}

	@RequestMapping("/gategoriesUpdate")
	public ModelAndView gategoriesUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String gategoriesId1 = request.getParameter("gategoriesId");
		int gategoriesId = Integer.parseInt(gategoriesId1);
		String gategoriesName = request.getParameter("gategoriesName");
		String gategoriesMessage = request.getParameter("gategoriesMessage");

		if (gategoriesService.gategoriesUpdate(gategoriesId, gategoriesName, gategoriesMessage) != 0) {
			mv.setViewName("forward:updateGategories");
			return mv;
		} else {
			mv.setViewName("shopWMS/view/errorPage");
			return mv;
		}
	}

	@RequestMapping("/gategoriesAndCommodity")
	public ModelAndView gategoriesAndCommodity(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String gategoriesId1 = request.getParameter("gategoriesId");
		String gategoriesName = request.getParameter("gategoriesName");
		System.out.println("gategories 参数：--" + gategoriesId1 + ":" + gategoriesName);
		int gategoriesId = Integer.parseInt(gategoriesId1);
		Gategories gategories1 = new Gategories();
		gategories1.setGategoriesId(gategoriesId);
		List<Map<String, Object>> clist = gategoriesService.queryCommodity(gategories1);
		System.out.println("gategories clist 返回：" + clist);
		mv.addObject("clist", clist);
		mv.addObject("gategoriesId", gategoriesId);
		mv.addObject("gategoriesName", gategoriesName);
		mv.setViewName("shopWMS/view/gategoriesAndCommodity");
		return mv;
	}

	/**
	 * 图片上传测试例子
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/upPicture")
	public void upPicture(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		/*
		 * if (multipartResolver.isMultipart(request)) {
		 * System.out.println("ss"); }
		 */

		/*
		 * String testpath = request.getServletPath(); String testpath2 =
		 * request.getRequestURI(); String testpath3 =
		 * request.getServletContext().getRealPath("/"); // 第一种：获取类加载的根路径
		 * D:\git\daotie\daotie\target\classes File f = new
		 * File(this.getClass().getResource("/").getPath());
		 * System.out.println(f); // 获取当前类的所在工程路径; 如果不加“/” 获取当前类的加载目录
		 * D:\git\daotie\daotie\target\classes\my File f2 = new
		 * File(this.getClass().getResource("").getPath());
		 * System.out.println(f2); // 第二种：获取项目路径 D:\git\daotie\daotie File
		 * directory = new File("");// 参数为空 String courseFile =
		 * directory.getCanonicalPath(); System.out.println(courseFile); // 第三种：
		 * file:/D:/git/daotie/daotie/target/classes/ URL xmlpath =
		 * this.getClass().getClassLoader().getResource("");
		 * System.out.println(xmlpath); // 第四种： D:\git\daotie\daotie
		 * System.out.println(System.getProperty("user.dir"));
		 * 
		 * 结果： C:\Documents and Settings\Administrator\workspace\projectName
		 * 获取当前工程路径
		 * 
		 * // 第五种： 获取所有的类路径 包括jar包的路径
		 * System.out.println(System.getProperty("java.class.path"));
		 * System.err.println("-----testpath路径地址为：:"+testpath);
		 * System.err.println("-----testpath2路径地址为：:"+testpath2);
		 * System.err.println("-----testpath3路径地址为：:"+testpath3);
		 */

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("-----服务器的路径地址为：:" + realPath);
		System.err.println("-----图片名称为：:" + fileName);
		System.err.println("-----图片新名称为：:" + uuidName);
		System.err.println("-----图片新路径为：:" + path);

	}

}
