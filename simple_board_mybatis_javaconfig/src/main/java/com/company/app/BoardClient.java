package com.company.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BoardDTO;
import com.company.service.BoardService;

public class BoardClient {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(BoardClient.class);
		BoardService service = (BoardService)ctx.getBean("boardService");
		
		
//		  BoardDTO insertDto =new BoardDTO(); 
//		  insertDto.setTitle("mybatis 실행"); // insertDto.setContent("스프링 프레임워크 게시판");
//		  insertDto.setContent("안되면 죽는다.."); // insertDto.setContent("스프링 프레임워크 게시판");
//		  insertDto.setWriter("my 승주");
//		  System.out.println(service.insertBoard(insertDto) ? "입력성공" : "입력실패");
		 		// 수정
//		BoardDTO updateDto = new BoardDTO();
//		updateDto.setBno(122);
//		updateDto.setTitle("myBatis 지린다지려");
//		updateDto.setContent("코드가 ... 이리줄어 ?...");
//		System.out.println(service.updateBoard(updateDto)? "수정성공":"수정실패");
		
		// 삭제
		System.out.println(service.deleteBoard(861)? "삭제성공" : "삭제실패쉴퐤시르패");
		
		// 전체/개별조회
		List<BoardDTO> list = service.getRows();
		
		System.out.println("===================================================================================");
		System.out.print("번호	제목		내용			작성자	날짜		수정날짜\n");
		BoardDTO dto = service.getRow(22);
		for(BoardDTO dto1 : list) {
			System.out.print(dto1.getBno()+"\t");
			System.out.print(dto1.getTitle()+"\t");
			System.out.print(dto1.getContent()+"\t");
			System.out.print(dto1.getWriter()+"\t");
			System.out.print(dto1.getRegdate()+"\t");
			System.out.print(dto1.getUpdatedate()+"\n");
		}
		if(dto!=null) {
			System.out.print(dto.getBno()+"\t");
			System.out.print(dto.getTitle()+"\t");
			System.out.print(dto.getContent()+"\t");
			System.out.print(dto.getWriter()+"\t");
			System.out.print(dto.getRegdate()+"\t");
			System.out.print(dto.getUpdatedate()+"\n");
		}
		System.out.println("===================================================================================");
	}
}
