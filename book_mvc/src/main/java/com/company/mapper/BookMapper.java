package com.company.mapper;

import java.util.List;

import com.company.domain.BookDTO;

public interface BookMapper {
	public List<BookDTO>  list();
	public BookDTO read(String code);
	public int insert(BookDTO insertDto);
	public int delete(String code);
	public int update(BookDTO updateDto);
	
}
