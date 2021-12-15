package com.company.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 요청 + 응답 + 인증(Authentication authentication, 안에 담겨있는 내용이 내포되어 있음) 성공 시 각종 정보를 담고 있는 객체
		
		log.info("Login success");
	
		// 로그인 성공시 ROLE이 admin 이라면 admin-page 로 이동
		// ROLE이 user이라면 user-page로 이동
		
		List<String> roleNames = new ArrayList<String>();
		authentication.getAuthorities().forEach(Authority -> { // "getAuthorities()" 안에 authority에 쓴 것이 담겨진 곳
			roleNames.add(Authority.getAuthority());
		});
	
		log.info("roleNames "+roleNames);
		
		if (roleNames.contains("ROLE_ADMIN")) { // admin이 있다면 admin 페이지가 돌거고 만나는 페이지는 admin-page야
			response.sendRedirect("/admin-page");
			return;
		}
		if (roleNames.contains("ROLE_USER")) { // user이 있다면 user 페이지가 돌거고 만나는 페이지는 user-page야
			response.sendRedirect("/user-page");
			return;
		}
		response.sendRedirect("/"); // 아무 것도 안 걸리면 메인으로 간다.

	
	}
	
	
	
}
