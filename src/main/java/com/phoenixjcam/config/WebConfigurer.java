package com.phoenixjcam.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;


/**
 * <strong> specify session factory, hibernate properties, data source, hibernate transaction, view resolver </strong>
 * <br>
 *  ComponentScan({ "com.phoenixjcam.*" }) annotation means 
 *  exactly the same as xml -> context:component-scan base-package="com.*" in dispatcher-servlet.xml
 *  
 * @author Bart88
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan({ "com.phoenixjcam.*" })
@EnableTransactionManagement
@Import({ WebSecurityConfigurer.class })
public class WebConfigurer extends WebMvcConfigurerAdapter
{
	@Bean
	public SessionFactory sessionFactory()
	{
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("com.phoenixjcam.login.users.model").addProperties(getHibernateProperties());
		builder.scanPackages("com.phoenixjcam.dashboard.humanResource.employee.model").addProperties(getHibernateProperties());
		
		return builder.buildSessionFactory();
	}

	private Properties getHibernateProperties()
	{
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return prop;
	}

	@Bean(name = "dataSource")
	public BasicDataSource dataSource()
	{
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/tomcat_jvcrm");
		ds.setUsername("root");
		ds.setPassword("");
		//ds.setPassword("root");
		return ds;
	}

	@Bean
	public HibernateTransactionManager txManager()
	{
		return new HibernateTransactionManager(sessionFactory());
	}

	@Bean
	public InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
//	@Bean
//	public UrlBasedViewResolver setupViewResolver()
//	{
//		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//		resolver.setPrefix("/WEB-INF/pages/");
//		resolver.setSuffix(".jsp");
//		resolver.setViewClass(JstlView.class);
//		return resolver;
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/res/**").addResourceLocations("/WEB-INF/res/");
	}
}