package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookDTO;
import com.company.mapper.BookMapper;


@Service // == @Component
public class BookServiceImple implements BookService {

	@Autowired
	private BookMapper mapper;
	
	
	@Override
	public List<BookDTO> getList() {
		
		return mapper.list();
	}


	@Override
	public boolean insertBook(BookDTO dto) {
		// TODO Auto-generated method stub
		return mapper.insert(dto)>0?true:false;
	}
	public boolean updateBook(String code,int price) {
		return mapper.update(code, price)>0?true:false;
	}
	
	public boolean deleteBook(String code) {
		return mapper.delete(code)>0?true:false;

	}


	@Override
	public BookDTO getRow(String code) {
		return mapper.read(code);
	}
}
