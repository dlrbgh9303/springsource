package com.company.mapper;

import java.util.List;

import com.company.domain.BoardDTO;

public interface BoardMapper {
	
	// 등록
	public int insert(BoardDTO insertDto);
	// 삭제
	public int delete(int bno);
	// 개별조회
	public BoardDTO read(int bno);
	// 전체조회
	public List<BoardDTO> all();
	// 수정
	public int update(BoardDTO updateDto);
}
