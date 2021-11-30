package com.company.mapper;

import java.util.List;

import com.company.domain.BoardDTO;

public interface BoardMapper {
	public int insert(BoardDTO insertDto);
	public List<BoardDTO> listAll();
	public BoardDTO read(int bno);
	public int update(BoardDTO updateDto);
	public int delete(int bno);
}
