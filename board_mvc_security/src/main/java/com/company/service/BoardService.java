package com.company.service;

import java.util.List;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;

public interface BoardService {

	public boolean register(BoardDTO insertDto);
	
	public List<BoardDTO> getList(Criteria cri);
	
	public BoardDTO getRow(int bno);
	
	public boolean update(BoardDTO updateDto);
	
	public boolean remove(int bno);

	public int getTotalCount(Criteria cri);
	
	//첨부파일
	public List<AttachFileDTO> findByBno(int bno);
	
	public boolean attachRemove(int bno);





}
