package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.domain.AttachFileDTO;
import com.company.domain.BoardDTO;
import com.company.domain.Criteria;
import com.company.mapper.BoardAttachMapper;
import com.company.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

   @Autowired
   private BoardMapper mapper;
   
   @Autowired
   private BoardAttachMapper boardAttachMapper;
   
   
   @Transactional
   @Override
   public boolean register(BoardDTO insertDto) {
      
      //게시글 등록
      boolean result = mapper.insert(insertDto) > 0? true:false;

      //첨부파일 등록 (
      if(insertDto.getAttachList() == null || insertDto.getAttachList().size() <= 0) {
         return false;
      }
      //첨부물이 존재하면 첨부파일을 등록하는 것
      insertDto.getAttachList().forEach(attach -> {
         attach.setBno(insertDto.getBno());
         boardAttachMapper.insert(attach);
      });
                  
      return result;
   }

   @Override
   public List<BoardDTO> getList(Criteria cri) {
      return mapper.listAll(cri);
   }

   @Override
   public BoardDTO getRow(int bno) {
      return mapper.read(bno);
   }

   
   @Override 
   public boolean update(BoardDTO updateDto) { 
      return mapper.update(updateDto) > 0? true:false; 
   } 
     
   @Override 
   public boolean remove(int bno) { 
       return mapper.delete(bno) > 0? true:false;
   }

   @Override
   public int getTotalCount(Criteria cri) {
      return mapper.totalCnt(cri);
   }


   @Override
   public boolean delete(int bno) {
	   // TODO Auto-generated method stub
	   return false;
   }

@Override
public List<AttachFileDTO> findByBno(int bno) {
	
	return boardAttachMapper.read(bno);

}
   
   
   
   
   
   
   
   
}