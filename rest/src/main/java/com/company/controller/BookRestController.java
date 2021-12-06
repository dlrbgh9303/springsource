package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/book*")
@Log4j2
public class BookRestController {

	@Autowired
	private BookService service;

	@PostMapping("/insert-rest")
//	@ResponseBody
	public String insertPost(@RequestBody BookDTO insertdto) {
		log.info("insert 요청 " + insertdto);

		try {
			if (service.insert(insertdto)) {
				return "success";
			}
		} catch (Exception e) {
			return "fail";
		}
		return "fail";
	}

	@GetMapping(value = "/{code}",produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookDTO> row(@PathVariable("code") String code) {
		log.info("책 하나 가져오기 " + code);

		BookDTO dto = service.getRow(code);

		return new ResponseEntity<BookDTO>(dto, HttpStatus.OK);
	}
	@GetMapping(value = "/rest-list",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDTO>> getList() { //ResponseEntity = 데이터도 보내고 상태코드(HttpStatus)도 보냄
		log.info("rest 방식 list 요청");
		return new ResponseEntity<List<BookDTO>>(service.getList(),HttpStatus.OK);
		
	}
	
	//   /book/{code} + DELETE
	
	@DeleteMapping("/{code}")
	public ResponseEntity<String> delete(@PathVariable("code")String code){
		
		log.info("rest 삭제 "+code);
		
		if(service.remove(code)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
	}
	
	//도서 수정 = /book/update + 수정내용 + put(patch)
	
	@PutMapping("/update")
	public ResponseEntity<String> modify(@RequestBody BookDTO updateDto){
		log.info("rest 수정" +updateDto);
		
		if(service.update(updateDto)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
}










