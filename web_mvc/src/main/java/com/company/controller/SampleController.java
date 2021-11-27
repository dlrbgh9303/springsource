package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.domain.UserDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller // 객체 생성
@RequestMapping("/sample/*")
public class SampleController {

	// ModelAndView

	@RequestMapping("/basic")
	public void basic() {
		log.info("/basic 요청........");
		// view 리졸버 /sample/basic

	}
	// 기본 get / post 둘다 응답
//	@RequestMapping("/login")
//	public String login() {
//		log.info("/login 요청........");
//		// view 리졸버 login
//		return "login";
//	}

	// GET 방식에만 응답
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		log.info("/login 요청........");
		// view 리졸버 login
		return "login";
	}

	// POST 방식에만 응답
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String loginPost() {
//		log.info("/login Post 요청");
//		return "/sample/basic";
//	}
	
	
	
	
	
	
	
	//POST 방식에만 응답 + 사용자의 입력값 가져오기
	// HttpServletRequest 이용
	// 바인딩 변수 사용
	// 바인딩 객체 사용
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String loginPost(HttpServletRequest req) {
//		log.info("/login Post 요청");
//		
//		log.info("userid :"+req.getParameter("userid"));
//		log.info("password :"+req.getParameter("password"));
//		
//		
//		
//		return "/sample/basic";
//	}
	
	
	
	
	
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String loginPost(String userid, String password) {
//		log.info("/login Post 요청");
//		
//		log.info("userid : "+userid);
//		log.info("userid : "+password);
//		
//		
//		
//		return "/sample/basic";
//	}
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(UserDTO userDto) {
		log.info("/login Post 요청");
		
		log.info("userid : "+userDto.getUserid());
		log.info("userid : "+userDto.getPassword());
		
		
		return "/sample/basic";
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/info",method=RequestMethod.GET)
	public void method1() {
		log.info("/sample/info 요청...");
		//뷰 리졸버 : /sample/info
	}
	@RequestMapping(value="/admin",method = RequestMethod.GET)
	public String method2() {
		log.info("/sample/admin 요청....");
		return "/sample/admin";
		//뷰 리졸버 : /sample/admin   =>    
	}

}
