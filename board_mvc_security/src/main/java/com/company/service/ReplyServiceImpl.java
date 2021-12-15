package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.domain.ReplyPageDTO;
import com.company.mapper.BoardMapper;
import com.company.mapper.ReplyMapper;
@Service

public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replymapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public boolean insertReply(ReplyDTO insertDto) {
		//spring_board replycnt +1
		boardMapper.updateReplyCnt(insertDto.getBno(), 1);
		
		return replymapper.insert(insertDto) > 0? true:false;
	}

	@Override
	public ReplyDTO getRow(int rno) {
		return replymapper.get(rno);
	}

	@Override
	public boolean updateReply(ReplyDTO updateDto) {
		return replymapper.update(updateDto) > 0? true:false;
	}

	@Transactional
	@Override
	public boolean deleteReply(int rno) {
		//spring_board replycnt -1
		
		
		//bno 알아내기
		ReplyDTO reply = replymapper.get(rno);
		boardMapper.updateReplyCnt(reply.getBno(), -1);
		
		return replymapper.delete(rno) > 0? true:false;
	}

	@Override
	public ReplyPageDTO getList(Criteria cri, int bno) {
		return new ReplyPageDTO(replymapper.getCountByBno(bno), replymapper.list(cri, bno));
	}

	
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		


}
