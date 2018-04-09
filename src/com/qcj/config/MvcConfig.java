package com.qcj.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc // 开启MVC注解功能
public class MvcConfig extends WebMvcConfigurerAdapter {
	static Logger log = Logger.getLogger(MvcConfig.class);

	/**
	 * 静态资源映射
	 * addResourceHandler()方法指定url，该url后连接静态文件，对应着addResourceLoaction()方法目录所对应的静态文件
	 */
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/shopWMS/404/**").addResourceLocations("/shopWMS/v404");
		registry.addResourceHandler("/shopWMS/css/**").addResourceLocations("/shopWMS/css/");
		registry.addResourceHandler("/shopWMS/img/**").addResourceLocations("/shopWMS/img/");
		registry.addResourceHandler("/shopWMS/i/**").addResourceLocations("/shopWMS/i/");
		registry.addResourceHandler("/shopWMS/js/**").addResourceLocations("/shopWMS/js/");
		registry.addResourceHandler("/shopWMS/fonts/**").addResourceLocations("/shopWMS/fonts/");
		registry.addResourceHandler("/shopWMS/view/**").addResourceLocations("/shopWMS/view");
		registry.addResourceHandler("/shopWMS/rev-slider/**").addResourceLocations("/shopWMS/rev-slider/");
		registry.addResourceHandler("/shopWMS/*.html").addResourceLocations("/shopWMS/view/");
		registry.addResourceHandler("/file/**").addResourceLocations("/file/");
	}

	@Bean // 声明视图解析器，在返回逻辑视图名后拼装物理视图名
	public UrlBasedViewResolver getViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		resolver.setContentType("text/html;charset=utf-8");
		log.info("----------------------------视图解析器配完成---------------------------------");
		return resolver;
	}

	/*
	 * resolve the multipart file upload.multipart图片解析器
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760);
		System.out.println("init resolver...multipart解析器dddd-------------");
		return multipartResolver;
	}
}
