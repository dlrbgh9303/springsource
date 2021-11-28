package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.MemberDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/register/*")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/step1")
	public void registGet() {
		log.info("register/step1.jsp 요청 ");
	}
	
	//step2
	@PostMapping("/step2")
	public String registerGet2(boolean agree, RedirectAttributes rtts) {
		log.info("/register/step2.jsp 요청"+agree);
		if(!agree) {
			// step1 페이지 보여주기
			//return "/register/step1";	 //WEB-INF/views/register/setp1
			rtts.addFlashAttribute("check","false");
			return "redirect:/register/step1";
			
		}
		// step2 페이지 보여주기
		return "/register/step2";
	}
	
	// 로그인 중복 확인
	@ResponseBody // 리턴하는 값이 데이터임 (jsp 페이지 찾지마)
	@PostMapping("/checkId")
	public String idCheck(String userid) {
		log.info("중복아이디 검사 "+userid);
		
		if(service.overlabCheck(userid)!=null) {
			return "false";
		}
		return "true";
	}
	
	
	@PostMapping("/step3")
	public String registerPost(MemberDTO memberDto) {
		log.info("회원가입 요청 !");
		try {
			if(!service.register(memberDto)) {
				//회원 가입 페이지 이동
				return "/register/step2";
			}
		} catch (Exception e) {
			return "/register/step2";
		}
		return "redirect:/register/signin";
	}
	
	// 로그인 시 확인 작성 
	@PostMapping("/signin")
	public String readPost(String userid, String password, Model model) {
		MemberDTO memberDto = service.select(userid, password);
		if(memberDto!=null) {
			model.addAttribute("memberDto", memberDto);
			return "/";
		}
		return "signin";
	}
	
	
	
	
	// signin 페이지 들어옴
	@GetMapping("/signin")
	public void readGet() {
		log.info("로그인 요청");
		// /register/signin/
	}
	
	// http://localhost:8080/register/step2 +GET
	// http://localhost:8080/register/step3 +GET
	// 405 - 허용되지 않는 메소드 
	
	@GetMapping(value= {"/step2","/step3"})
	public String handleGet() {
		log.info("잡아와라 이자슥아(step2,step3직접요청 ...");
		return "redirect:/";
	}
	
}
