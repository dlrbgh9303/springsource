package com.company.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	
//	<context-param>
//	<param-name>contextConfigLocation</param-name>
//	<param-value>/WEB-INF/spring/root-context.xml</param-value>
//	</context-param>
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

//	<init-param>
//	<param-name>contextConfigLocation</param-name>
//	<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
//	</init-param>
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}

//	<servlet-mapping>
//	<servlet-name>appServlet</servlet-name>
//	<url-pattern>/</url-pattern>
//	</servlet-mapping>
	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	// 한글처리
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("utf-8");
		encodingFilter.setForceEncoding(true);
		
		
		return new Filter[] {encodingFilter};
	}

}
