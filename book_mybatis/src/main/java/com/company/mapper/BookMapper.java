package com.company.mapper;

import java.util.List;

import com.company.domain.BookDTO;

public interface BookMapper {
	public BookDTO read(String code);
	public List<BookDTO> list();
	public int insert(BookDTO dto);
	public int update(String code, int price);
	public int delete(String code);
	
}
