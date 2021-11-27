package com.company.persistence;


import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BoardDTO;

@Repository
public class BoardDAO {
   
   @Autowired
   private JdbcTemplate jdbcTemplate;
   
   //삽입
   public boolean insert(BoardDTO insertDto) {
      
         String sql="insert into spring_board(bno,title,content,writer) values(seq_board.nextval,?,?,?)";
         //update(sql,Object...args) : 인자의 개수가 정해지지 않은 경우
         int result = jdbcTemplate.update(sql,insertDto.getTitle(),insertDto.getContent(),insertDto.getWriter());
         
      return result>0?true:false;
   }
   
   //전체 조회
   public List<BoardDTO> list(){
      List<BoardDTO> boardList = new ArrayList<BoardDTO>();   
         String sql="select * from spring_board";
         return jdbcTemplate.query(sql, new BoardRowMapper());
   }
   
   //특정 게시물 가져오기
   public BoardDTO getRow(int bno) {
         String sql = "select * from spring_board where bno=?";
         return jdbcTemplate.queryForObject(sql, new BoardRowMapper(),bno);
   }
   
   //업데이트
   public boolean update(BoardDTO updateDto) {
         String sql="update spring_board set title=?,content=?,updatedate=sysdate where bno=?";
         int result = jdbcTemplate.update(sql,updateDto.getTitle(),updateDto.getContent(),updateDto.getBno());
         return result>0 ? true: false;
   }
   
   //삭제
   public boolean delete(int bno) {
         String sql="delete from spring_board where bno=?";
         int result = jdbcTemplate.update(sql,bno);
         return result>0 ? true:false;
   }
}