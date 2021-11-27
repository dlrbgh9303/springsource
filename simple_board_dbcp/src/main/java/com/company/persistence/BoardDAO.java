package com.company.persistence;

import static com.company.persistence.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BoardDTO;

@Repository
public class BoardDAO {
   
   
   @Autowired
   private JdbcTemplate jdbcTemplate;
   
   // 삽입
   public boolean insert(BoardDTO insertDto) {
      
         String sql = "insert into spring_board";
               sql += "(bno,title,content,writer)";
               sql += " values(seq_board.nextval,?,?,?)";
               
         int result = jdbcTemplate.update(sql,insertDto.getTitle(),
               insertDto.getContent(),insertDto.getWriter());
      
         return result > 0 ? true : false;
   }
   // 선택
   public BoardDTO getRow(int bno) {
   
         String sql = "select * from spring_board where bno=?";
      
      
      return jdbcTemplate.queryForObject(sql, new BoardRowMapper());
   }
   // 삭제
   public boolean delete(int bno) {
      PreparedStatement pstmt = null;
      Connection con =null;
      boolean deleteFlag = false;
      
      try {
         con = ds.getConnection();
         
         String sql = "delete from spring_board where bno=?";
         
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, bno);
         
         int result = pstmt.executeUpdate();
         
         if(result>0) {
            deleteFlag = true;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
            close(pstmt);
            close(con);
      }
      return deleteFlag;
   }
   // 수정 
   public boolean update(BoardDTO updateDto) {
      PreparedStatement pstmt = null;
      boolean updateFlag = false;
      Connection con = null;
      try {
         con = ds.getConnection();
         // 수정할 목록
         // title, content, updatedate
         String sql = "update spring_board set title=?,content=?,updatedate=sysdate where bno=?";
         pstmt = con.prepareStatement(sql);
         
         pstmt.setString(1,updateDto.getTitle());
         pstmt.setString(2,updateDto.getContent());
         pstmt.setInt(3, updateDto.getBno());
         
         int result = pstmt.executeUpdate();
         
         if(result>0) {
            updateFlag = true;
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }finally {
            close(pstmt);
            close(con);
      }
      return updateFlag;
   }
   // 전체조회
   public List<BoardDTO> listAll(){
      
         String sql = "select * from spring_board";
         
      return jdbcTemplate.query(sql, new BoardRowMapper());
   }
   
}