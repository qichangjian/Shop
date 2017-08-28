package com.qcj.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
/**
 * @Configuration	                                              声明当前类是一个配置类（代替xml文件的类）
 * @ComponentScan("com.qcj")     配置扫描位置
 * @PropertySource(value={"classpath:jdbc.properties"},ignoreResourceNotFound=true)
 *			                                                                        通过注解读取属性文件，这样就可以忽略源文件找不到的情况					 
 */
@Configuration					
@ComponentScan("com.qcj")		
@PropertySource(value={"classpath:jdbc.properties"},ignoreResourceNotFound=true)
public class DataSourceConfig {
	static Logger log = Logger.getLogger(DataSourceConfig.class);
	
	@Resource
    private Environment env;			//引入环境系统，配合@PropertySource注解，可以直接获取相关数据
	
	@Resource(name="dataSource")
	public DataSource dataSource;     
	
	/**
	 * 声明该方法将返回一个Bean，Bean将放入容器中备用
	 * Druid是阿里巴巴数据缓冲池
	 */
	@Bean(name = "dataSource")		
	public DruidDataSource getDruidDataSource() {
		try {
			String className=env.getProperty("jdbc.className");
			String connString=env.getProperty("jdbc.url");
			String userName=env.getProperty("jdbc.userName");
			String password=env.getProperty("jdbc.password");
			DruidDataSource ds = new DruidDataSource();
			ds.setUrl(connString);
			ds.setDriverClassName(className);
			ds.setUsername(userName);
			ds.setPassword(password);
			ds.setMaxActive(6);
			log.info("-----------------------数据库连接成功--------------------------------");
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate;
	}
	

}
