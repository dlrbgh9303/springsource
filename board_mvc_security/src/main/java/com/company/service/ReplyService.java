package com.company.service;

import java.util.List;

import com.company.domain.Criteria;
import com.company.domain.ReplyDTO;
import com.company.domain.ReplyPageDTO;

public interface ReplyService {

	public boolean insertReply(ReplyDTO insertDto);
	
	public ReplyDTO getRow(int rno);
	
	public boolean updateReply(ReplyDTO updateDto);
	
	public boolean deleteReply(int rno);

	public ReplyPageDTO getList(Criteria cri, int rno);
	
}
