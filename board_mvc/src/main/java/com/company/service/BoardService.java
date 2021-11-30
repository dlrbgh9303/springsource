package com.company.service;

import java.util.List;

import com.company.domain.BoardDTO;


public interface BoardService {
	public boolean register(BoardDTO insertDto);
	public List<BoardDTO> getList();
	public BoardDTO getRow(int bno);
	public boolean update(BoardDTO updateDto);
	public boolean delete(int bno);
}
