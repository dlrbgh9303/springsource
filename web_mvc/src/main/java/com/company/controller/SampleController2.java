package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller // 객체 생성

public class SampleController2 {

	// ModelAndView

	@RequestMapping("/member/basic")
	public void basic() {
		log.info("/member/basic 요청........");
		

	}


	// GET 방식에만 응답
	//@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	@GetMapping("/member/login")
	public String login() {
		log.info("/member/login 요청........");
		// view 리졸버 login
		return "login";
	}

	// POST 방식에만 응답
	//@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	@PostMapping("/member/login")
	public String loginPost() {
		log.info("/member/login Post 요청");
		return "/sample/basic";
	}
	
//	@RequestMapping(value="/admin/info",method=RequestMethod.GET)
	@GetMapping("/admin/info")
	public void method1() {
		log.info("/admin/info 요청...");
		//뷰 리졸버 : /sample/info
	}
//	@RequestMapping(value="/admin",method = RequestMethod.GET)
	@GetMapping("/admin")
	public String method2() {
		log.info("/admin 요청....");
		return "/sample/admin";
		//뷰 리졸버 : /sample/admin   =>    
	}

}
