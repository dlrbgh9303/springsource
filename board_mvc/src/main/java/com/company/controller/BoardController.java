package com.company.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardDTO;
import com.company.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
public class BoardController {
	
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/register")
	public void registerGet() {
		log.info("register 요청");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto,RedirectAttributes rttr) {
		log.info("register 가져오기 "+insertDto);
		
		service.register(insertDto);
		
		
		//log.info("bno" +insertDto.getBno());
		
		
		rttr.addFlashAttribute("result", insertDto.getBno());
		return "redirect:/board/list";
		
	}
	@GetMapping("/list")
	public void list(Model model) {
		log.info("전체 리스트 요청");
		
		List<BoardDTO> list = service.getList();
		
		model.addAttribute("list",list);
	}
	
	
	@GetMapping({"/read","/modify"})
	public void readGet(int bno,Model model) {
		log.info("read or modify 요청 "+bno);
		
		BoardDTO dto = service.getRow(bno);
		
		model.addAttribute("dto",dto);
		
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardDTO modifyDto) {
		log.info("게시글 수정 "+modifyDto);
		
		
		//수정 완료 후 리스트로 이동
		service.update(modifyDto); 
			
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String removePost(int bno) {
		log.info("게시글 삭제 "+bno);
		
		service.delete(bno);
		
		return "redirect:/board/list";
	}
	
	
	
}










