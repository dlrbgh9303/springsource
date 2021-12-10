package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.company.domain.BoardDTO;
import com.company.mapper.BoardMapper;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	
	@Override
	public boolean insertBoard(BoardDTO insertDto) {
		
		return mapper.insert(insertDto) > 0 ? true:false;
	}

	@Override
	public boolean deleteBoard(int bno) {
		return mapper.delete(bno) > 0 ? true:false;
	}

	@Override
	public BoardDTO getRow(int bno) {
		return mapper.read(bno);
	}

	@Override
	public List<BoardDTO> getRows() {
		return mapper.all();
	}

	@Override
	public boolean updateBoard(BoardDTO updateDto) {
		return mapper.update(updateDto) > 0 ? true:false;
	}

}
