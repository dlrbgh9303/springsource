package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.domain.PageDTO;
import com.company.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired // 객체생성
	private BoardService service;
	
	@GetMapping("/register")
	public void registerGet() {
		log.info("register 요청");
	}
		
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto, RedirectAttributes rttr) {
		log.info("register 가져오기 " + insertDto);
		
		//첨부파일 확인
		if(insertDto.getAttachList()!=null) {
			insertDto.getAttachList().forEach(attach -> log.info(attach+""));
		}
		
		
		
		

		service.register(insertDto);
		
		// log.info("bno" +insertDto.getBno());
		
		rttr.addFlashAttribute("result", insertDto.getBno());
		return "redirect:/board/list";
	}

	
	
	@GetMapping("/list")
	public void list(Model model, Criteria cri) { 
		log.info("전체 리스트 요청 " + cri);
		
		List<BoardDTO> list = service.getList(cri);
		
		// 페이지 나누기를 위한 정보 얻기
		int totalCnt = service.getTotalCount(cri);
		
		model.addAttribute("pageDto", new PageDTO(cri, totalCnt));
		model.addAttribute("list", list);
	}
		
	@GetMapping({ "/read", "/modify" })
	public void readGet(int bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("read or modify 요청 " + bno);

		BoardDTO dto = service.getRow(bno);

		model.addAttribute("dto", dto);
			
	}
	

		
	@PostMapping("/modify")
	public String modify(BoardDTO modifyDto, Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 수정 " + modifyDto + " " + cri);
			
		// 수정 완료 후 리스트로 이동
		service.update(modifyDto);
		
		// 페이지 나누기 값
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		// 검색 값
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String removePost(int bno, Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 삭제 " + bno);

		// 수정 삭제 후 리스트로 이동 
		service.delete(bno);

		// 페이지 나누기 값
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		// 검색 값
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> getAttachList(int bno){
		log.info("파일 첨부 가져오기 "+bno);
		return new ResponseEntity<List<AttachFileDTO>>(service.findByBno(bno),HttpStatus.OK);
	}
}









