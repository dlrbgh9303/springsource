package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.domain.ChangeDTO;
import com.company.domain.LoginDTO;
import com.company.domain.MemberDTO;
import com.company.service.MemberService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class MemberRestController {
	
	@Autowired
	private MemberService service;
	
		@PostMapping("/insert")
		public ResponseEntity<String> insert(@RequestBody MemberDTO insertDto){
			log.info("회원가입 "+insertDto);
			
			if(service.register(insertDto)){
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
				}
			}
		
		@PutMapping("/changePwd")
		public ResponseEntity<String> change(@RequestBody ChangeDTO updateDto){
			log.info("수정 요청 "+updateDto);
			
			if(service.changePwd(updateDto)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
				}
		}
		
		@DeleteMapping("/leave")
		public ResponseEntity<String> delete(@RequestBody LoginDTO leaveDto){
			
			log.info("삭제 요청 "+leaveDto);
			
			if(service.leave(leaveDto)) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
				}
		}
			
			
			
		}
	













