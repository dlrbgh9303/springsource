package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BookDTO;
import com.company.persistence.BookDAO;

@Service // == @Component
public class BookServiceImple implements BookService {

	@Autowired
	private BookDAO dao;
	
	
	@Override
	public List<BookDTO> getList() {
		
		return dao.list();
	}


	@Override
	public boolean insertBook(BookDTO dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}
	public boolean updateBook(String code,int price) {
		return dao.update(code, price);
	}
	
	public boolean deleteBook(String code) {
		return dao.delete(code);

	}
}
