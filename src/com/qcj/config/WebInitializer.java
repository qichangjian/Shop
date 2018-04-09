package com.qcj.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

//实现WebApplicationinitializer的类都可以在web应用程序启动时被加载
public class WebInitializer implements WebApplicationInitializer {
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("-------------------------start---------------------");
		FilterRegistration.Dynamic encodingfilter = servletContext.addFilter("encodingFilter",
				CharacterEncodingFilter.class);
		encodingfilter.setInitParameter("encoding", "UTF-8");
		encodingfilter.addMappingForUrlPatterns(null, false, "/*");
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext(); // 该类可以注册配置文件类
		ctx.register(DataSourceConfig.class, MvcConfig.class); // 注册DataSourceConfig类和MvcConfig类
		ctx.setServletContext(servletContext);
		// 创建一个Servlet
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}
}
