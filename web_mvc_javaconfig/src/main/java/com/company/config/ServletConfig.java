package com.company.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.company.interceptor.SampleInterceptor;


@ComponentScan("com.company.controller")
@EnableWebMvc
@Configuration
public class ServletConfig implements WebMvcConfigurer {
//	<resources mapping="/resources/**" location="/resources/" />
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
		        .addResourceLocations("/resources/");
	}
	
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		
		
		
		registry.viewResolver(viewResolver);
	}
	
  /*<!-- interceptor 설정 -->
	<interceptors>
		<interceptor>
			<mapping path="/doA"/>
			<mapping path="/doB"/>
			<mapping path="/doC"/>
			<mapping path="/"/>
			<beans:ref bean="sampleInterceptor"/>
		</interceptor>
	</interceptors> */
	
	
	@Override
		public void addInterceptors(InterceptorRegistry registry) {
			List<String> urlPatterns = Arrays.asList("/doA","/doB","/doC","/");
			registry.addInterceptor(new SampleInterceptor())
					.addPathPatterns(urlPatterns);
		}
	
	
	
	
	
	
}















