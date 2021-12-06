package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.ReplyDTO;
import com.company.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/replies")
@Log4j2
public class ReplyController {
	@Autowired
	private ReplyService service;
	
	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody ReplyDTO insertDto){
		log.info("댓글입력 "+insertDto);
		
		return service.insertReply(insertDto)?
				new ResponseEntity<String>("success",HttpStatus.OK):
					new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);	
		}
	
	@GetMapping("/{rno}")
	public ResponseEntity<ReplyDTO> read(@PathVariable int rno) {
		log.info("댓글 하나 가져오기 "+rno);
		
		return new ResponseEntity<ReplyDTO> (service.getRow(rno),HttpStatus.OK);
	}
	@PutMapping("/{rno}")
	public ResponseEntity<String> modify(@PathVariable int rno,@RequestBody ReplyDTO updateDto){
		log.info("댓글수정 "+updateDto);
		
		//updateDto rno 셋팅하기
		updateDto.setRno(rno);
		
		
		return service.updateReply(updateDto)?
				new ResponseEntity<String>("success",HttpStatus.OK):
					new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping("/{rno}")
	public ResponseEntity<String> remove(@PathVariable int rno){
		log.info("댓글 삭제 "+rno);
		
		return service.deleteReply(rno)?
				new ResponseEntity<String>("success",HttpStatus.OK):
					new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		
	}
	
	// 댓글 전체 가져오기
	@GetMapping("/pages/{bno}/{page}")
	public ResponseEntity<List<ReplyDTO>> readAll(@PathVariable int bno){
		log.info("댓글 전체가져오기 "+bno);
		
		return new ResponseEntity<List<ReplyDTO>>(service.getList(bno),HttpStatus.OK);
		
	}
	
	
	
}









