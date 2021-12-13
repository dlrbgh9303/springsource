package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller //객체 생성해줘
public class SampleController2 {
	
	// ModelAndView

	
	//@RequestMapping("/member/basic") // (void)   
	@GetMapping("/member/basic")
	public void basic() {
		log.info("/member/basic 요청.........");
		// view 리졸버 /sample/basic
	}
	

	//GET 방식 응답
	//@RequestMapping(value="/member/login",method = RequestMethod.GET) // value = 나머지 주소, method에는 get 방식 
	@GetMapping("/member/login")
	// => 405 에러 (post를 지원하지 않는다. => post를 지원하는 메소드가 없어서 뜬 것임)
	public String login() {
		log.info("/member/login 요청.........");
		// view 리졸버  login
		return "login";
	}

	//POST 방식 응답
	//@RequestMapping(value="/member/login",method=RequestMethod.POST) // value = 나머지 주소, method에는 get 방식
	@PostMapping("/member/login")
	public String loginPost() {
		log.info("/member/login Post 요청");
		return "/sample/basic";
	}

	//@RequestMapping(value = "/admin/info", method = RequestMethod.GET)
	@GetMapping("/admin/info")
	public void method1() {
		log.info("/admin/info 요청...");
		// 뷰리졸버 : void는 그대로 /sample/info
	}

	//@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@GetMapping("/admin")
	public String method2() {
		log.info("/admin 요청...");
		//return "/sample/admin"; => 뷰리졸버 : /sample/admin
 		return "admin";
		// admin =>  /WEB-INF/views/admin.jsp
	}
	
	
	
}

  