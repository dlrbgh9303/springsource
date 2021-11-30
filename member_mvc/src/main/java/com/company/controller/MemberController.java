package com.company.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member/*")

public class MemberController {

	@Autowired
	private MemberService service;
	
	// 로그인

	// signin 페이지 들어옴
	@GetMapping("/signin")
	public void signin() {
		log.info("로그인 요청");
		// /member/signin/
	}

	// loginPost()
	@PostMapping("/signin")
	public String loginPost(LoginDTO loginDto, HttpSession session) {
		log.info("login 요청 " + loginDto);

		loginDto = service.login(loginDto);
		
		session.setAttribute("loginDto", loginDto);
		
		return "redirect:/";
	}
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("로그아웃 요청");
		session.invalidate(); // session.removeAttribute("loginDto"); 개별로 세션날리기
		return "redirect:/";
	}
	//비밀번호 변경
	@GetMapping("/changePwd")
	public void changePwd() {
		log.info("changePwd.jsp");
	}
	
	//changePwd(POST) + 폼에서 넘기는 값 가져오기(ChangeDTO)
	@PostMapping("/changePwd")
	public String changePwdPost(ChangeDTO changeDto,HttpSession session) {
		log.info("비밀번호 변경 요청 "+ changeDto);
		
		//비밀번호 변경요청
		// 1. userid 가져오기
		LoginDTO loginDto = (LoginDTO)session.getAttribute("loginDto");
		changeDto.setUserid(loginDto.getUserid());
		
		if(service.changePwd(changeDto)) {
			//비밀번호 변경이 되면 기존의 세션 해제
			//signin 페이지 보여주기
			session.invalidate();
			return "redirect:/member/signin";

		}
		
		//변경 실패시 changePwd 보여주기
		return "redirect:/member/changePwd";
	}
	
	//회원탈퇴
	@GetMapping("/leave")
	public void leaveGet() {
		log.info("leave.jsp 요청");
	}
	
	@PostMapping("/leave")
	public String leavePost(LoginDTO leaveDto,HttpSession session) {
		log.info("탈퇴요청 "+leaveDto);
		
		if (service.leave(leaveDto)) {
			session.invalidate();
			return "redirect:/"; // 탈퇴 성공 이동경로
		}
		return "redirect:/member/leave"; //탈퇴 실패할때 이동경로
	}
	
}













