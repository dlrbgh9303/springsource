package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	private BookService service;
	
	
	
	@GetMapping("/insert")
	public void insertGet() {
		log.info("insert 요청");
	}
	
	
	//새 도서 입력
	//입력성공 시 전체 목록 보기
	@PostMapping("/insert")
//	@ResponseBody
	public ResponseEntity<String> insertPost(@RequestBody BookDTO insertdto) {
		log.info("insert 요청 "+insertdto);
		
		
		try {
			if (service.insert(insertdto)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("전체 리스트 요청");
		// /book/list => jsp 찾는데 사용됨
		
		

		
	}
	
	@GetMapping({"/read","/modify"})
	// /book/read  or /book modify
	public void readGet(String code, Model model) {
		log.info("read or modify 요청 "+code);
		
		BookDTO dto = service.getRow(code);
		
		model.addAttribute("dto" , dto);
		
		// /book/read  => WEB-INF/views/book/read.jsp
		// /book modify  => WEB-INF/views/book/modify.jsp
		
	}
	
		// /book/modify + POST
		// 수정이 완료된 후 내용보기
		@PostMapping("/update")
		public String modify(BookDTO modifyDto,RedirectAttributes rttr) {
			log.info("수정 요청"+modifyDto);
			
			service.update(modifyDto);
			
			rttr.addAttribute("code", modifyDto.getCode());
			
			return "redirect:/book/read";
		}
	
	// /book/remove 
	@GetMapping("/remove")
	public String removePost(String code) {
		log.info("도서 삭제 요청" +code);
		
		service.remove(code);
		
		return "redirect:/book/list";
		
	}
}

















