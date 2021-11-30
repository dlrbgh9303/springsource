package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.domain.BoardDTO;
import com.company.mapper.BoardMapper;

@Service

public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public boolean register(BoardDTO insertDto) {
		
		return mapper.insert(insertDto)>0?true:false;
	}

	@Override
	public List<BoardDTO> getList() {
		
		return mapper.listAll();
	}

	@Override
	public BoardDTO getRow(int bno) {
		
		return mapper.read(bno);
	}

	@Override
	public boolean update(BoardDTO updateDto) {
		
		return mapper.update(updateDto)>0?true:false;
	}

	@Override
	public boolean delete(int bno) {
		
		return mapper.delete(bno)>0?true:false;
	}

}
