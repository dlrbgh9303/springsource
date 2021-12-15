package com.company.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

	@Autowired
	private BoardService service;
	
	//  /board/register.jsp
	
	// isAuthenticated() : 인증된 사용자의 경우 true
	
	@PreAuthorize("isAuthenticated()") //들어가기 전에 검증해라
	@GetMapping("/register")
	public void registerGet() {
		log.info("register 폼 요청");
	}
	
	// /board.register로 들어오는 거

//들어가기 전에 검증해라
	@PostMapping("/register")
	public String registerPost(BoardDTO insertDto, RedirectAttributes rttr) {
		log.info("register 가져오기 "+insertDto);

		//첨부파일 확인하기
//		if(insertDto.getAttachList()!=null) {
//			insertDto.getAttachList().forEach(attach -> log.info(attach+""));
//		}

		service.register(insertDto);
		
		//log.info("bno "+insertDto.getBno());
	
		rttr.addFlashAttribute("result", insertDto.getBno()); //세션을 잠시 사용할 때 
		return "redirect:/board/list";
	}
	
	// 전체 리스트 조회
	@GetMapping("/list")
	public void list(Model model,Criteria cri) { //객체 생성구문
		log.info("전체 리스트 요청 "+cri);

		List<BoardDTO> list = service.getList(cri);
	
		//페이지 나누기를 위한 정보 얻기
		int totalCnt = service.getTotalCount(cri);
		
		model.addAttribute("pageDto", new PageDTO(cri, totalCnt));
		model.addAttribute("list", list);
	}
	
	// 특정 번호 조회
	@GetMapping({"/read","/modify"})
	public void readGet(int bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("read or modify 요청 "+bno);
	
		BoardDTO dto = service.getRow(bno);
		
		model.addAttribute("dto", dto);	
	}
	
	// /modify/post
	@PostMapping("/modify")
	public String modify(BoardDTO modifyDto, Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 수정 "+modifyDto+" "+cri);

		//수정 완료 후 리스트로 이동
		service.update(modifyDto);
		
		//페이지 나누기 값
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		//검색 값
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		
		rttr.addFlashAttribute("result", "success");
		return "redirect:/board/list";
	}
	
	// /modify/post
	@PostMapping("/remove")
	public String removePost(int bno, Criteria cri, RedirectAttributes rttr) {
		log.info("게시글 삭제 "+bno);
		
		//첨부 파일 목록 얻어오기
		List<AttachFileDTO> attachList = service.findByBno(bno);
		
		//수정 완료 후 리스트로 이동
		if (service.remove(bno)) { //remove가 성공하면 파일 삭제하는 거고
			//첨부 파일 삭제
			deleteFiles(attachList);
			//페이지 나누기 값
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			//검색 값
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
			
			rttr.addFlashAttribute("result", "success");
			
		}
		return "redirect:/board/list"; // 실패하면 여기로 돌아간다.
	}
	
	
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> getAttachList(int bno){
		log.info("파일 첨부 가져오기 "+bno);
		return new ResponseEntity<List<AttachFileDTO>>(service.findByBno(bno),HttpStatus.OK);
	}
	
	private void deleteFiles(List<AttachFileDTO> attachList) {
		if (attachList == null || attachList.size() == 0) { // 파일 삭제할 것이 없으면 돌아가라는 의미
			return; 
		}
		log.info("파일 삭제 중... ");
		
	    attachList.forEach(attach ->{ //attach라는 이름으로 DTO에서 만든 것을 가져오는 부분
	    	Path file = Paths.get("c:\\upload\\"+attach.getUploadPath()+"\\"+attach.getUuid()+"_"+attach.getFileName());

	    	try {
	    		//일반 파일, 이미지 원본 파일만 존재
				Files.deleteIfExists(file); // 존재하면 삭제하겠다
			
				if(Files.probeContentType(file).startsWith("image")) { //이미지라면 썸네일까지 삭제하겠다
					Path thumbNail = Paths.get("c:\\upload\\"+attach.getUploadPath()
									+"\\s_"+attach.getUuid()+"_"+attach.getFileName());
					// 이미지 썸네일 삭제
					Files.delete(thumbNail);
				}
	    	} catch (IOException e) {
				e.printStackTrace();
			}
	    });
	}
}


