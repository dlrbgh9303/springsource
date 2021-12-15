package com.company.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.company.handler.CustomAccessDeniedHandler;
import com.company.handler.CustomLoginSuccessHandler;
import com.company.service.CustomUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	
	//<!-- 암호화 객체를 생성하고 관리해달라는 의미-->
	// <bean id="bCryptPasswordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" ></bean>
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//<!-- Custom Service 객체 생성-->
	//<bean id="customUserDetailService" class="com.company.service.CustomUserDetailService"></bean>
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailService();
	}
	
	
	//<bean id="customAccessDeniedHandler" class="com.company.handler.CustomAccessDeniedHandler"></bean>
	
	@Bean
	public AccessDeniedHandler getAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	//<bean id="customLoginSuccessHandler" class="com.company.handler.CustomLoginSuccessHandler"></bean>
	
	@Bean 
	public AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}
	
	@Bean
	public PersistentTokenRepository persistentToken() {
		 
		JdbcTokenRepositoryImpl resp = new JdbcTokenRepositoryImpl();
		resp.setDataSource(dataSource);
		return resp;
		
	}
	
	@Override  // <security:http>
	protected void configure(HttpSecurity http) throws Exception {
		//<!-- 접근 권한이 없는 uri에 접근 시 움직이는 경로 설정 -->
	  	//  <!-- <security:access-denied-handler error-page="/access-denied"/> --> 
	  	//  <security:access-denied-handler ref="customAccessDeniedHandler"/> 
			
		http.exceptionHandling(ex -> ex.accessDeniedHandler(getAccessDeniedHandler()));

		// <!-- <security:form-login/> -->
	
		http.formLogin()
			.loginPage("/login")
			.failureUrl("/login-error")
			.successHandler(getAuthenticationSuccessHandler());
		
		//<!-- default 로그인 로그아웃 페이지 보여짐 -->
	    //<!-- <security:form-login/> -->
		
		http.logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true)
			.deleteCookies("remember-me","JSESSION_ID")
			.logoutSuccessUrl("/");
		
		
		// <security:intercept-url pattern="/user-page" access="hasRole('ROLE_USER')"/>
	    // <security:intercept-url pattern="/admin-page" access="hasRole('ROLE_ADMIN')"/>
		
		http.authorizeRequests().antMatchers("/user-page").access("hasRole('ROLE_USER')")
								.antMatchers("/admin-page").access("hasRole('ROLE_ADMIN')")
								.antMatchers("/login").permitAll();
	
		// <!-- remember-me 활성화 -->
	    // <security:remember-me data-source-ref="ds" token-validity-seconds="604800"/>
		http.rememberMe()
			.tokenRepository(persistentToken())
			.tokenValiditySeconds(604800);
		
	}
		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*<security:authentication-manager>
	      <security:authentication-provider user-service-ref="customUserDetailService">
	   			<security:password-encoder ref="bCryptPasswordEncoder"/>
	      </security:authentication-provider>
	   </security:authentication-manager> */	
			auth.userDetailsService(getDetailsService())
				.passwordEncoder(getPasswordEncoder());
		}
	
}

