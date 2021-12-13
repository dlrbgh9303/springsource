package com.company.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.company.domain.UserDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller //객체 생성해줘
@RequestMapping("/sample/*")
public class SampleController {
	
	// ModelAndView

	
	@RequestMapping("/basic") // (void)   
	public void basic() {
		log.info("/basic 요청.........");
		// view 리졸버 /sample/basic
	}
	
	// 기본 GET / POST 둘다 응답
//	@RequestMapping("/login") //  /sample/login  (string) 
//	public String login() {
//		log.info("/login 요청.........");
//		// view 리졸버  login
//		return "login";
//	}

	//GET 방식 응답
	@RequestMapping(value="/login",method = RequestMethod.GET) // value = 나머지 주소, method에는 get 방식 
	// => 405 에러 (post를 지원하지 않는다. => post를 지원하는 메소드가 없어서 뜬 것임)
	public String login() {
		log.info("/login 요청.........");
		// view 리졸버  login
		return "login";
	}

	//POST 방식 응답
//	@RequestMapping(value="/login",method=RequestMethod.POST) // value = 나머지 주소, method에는 get 방식
//	public String loginPost() {
//		log.info("/login Post 요청");
//		return "/sample/basic";
//	}
	
	//POST 방식 응답 + 사용자의 입력값 가져오기
	// 1) HttpServletRequest 이용 - 잘 안씀
	// 2) 바인딩 변수 사용 - 
	// 3) 바인딩 객체 사용
	
	// 1) HttpServletRequest 이용 - 잘 안씀
//	@RequestMapping(value="/login",method=RequestMethod.POST) // value = 나머지 주소, method에는 get 방식
//	public String loginPost(HttpServletRequest req) {
//		log.info("/login Post 요청");
//		
//		log.info("userid : "+req.getParameter("userid"));
//		log.info("password : "+req.getParameter("password"));
//		
//		
//		return "/sample/basic";
//	}

	// 2) 바인딩 변수 사용
//	@RequestMapping(value="/login",method=RequestMethod.POST) // value = 나머지 주소, method에는 get 방식
//	public String loginPost(@RequestParam("userid") String userid,
//			@RequestParam("pwd") String password, Model model) {
//		log.info("/login Post 요청");
//		
//		log.info("userid : "+userid);
//		log.info("password : " +password);
//		
//		model.addAttribute("password", password); // request.setAttribute와 같은 맥락, 값이 넘어오게 만들어 줌
//		model.addAttribute("userid", userid); // request.setAttribute와 같은 맥락, 값이 넘어오게 만들어 줌
//		
//		return "/sample/basic";
//	}	
    
	// 3) 바인딩 객체 사용
	@RequestMapping(value="/login",method=RequestMethod.POST) // value = 나머지 주소, method에는 get 방식
	public String loginPost(@ModelAttribute("login") UserDTO userDto) { // 담아준 값을 그대로 적용해줌
		log.info("/login Post 요청");
		
		log.info("userid : "+userDto.getUserid());
		log.info("password : "+userDto.getPassword());
		log.info("name : "+userDto.getName());
		
		
		return "/sample/basic"; 
	}	
}

  