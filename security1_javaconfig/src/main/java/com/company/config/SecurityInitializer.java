package com.company.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	// 내부적으로 DelegatingFilterProxy를 스프링에 등록시킴
	
	//<!-- security -->
	/*<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern></url-pattern>
	</filter-mapping>*/
	
	
}
