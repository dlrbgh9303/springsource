package com.company.app;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.BookDTO;
import com.company.service.BookService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BookClient {

	private static BookService service;
	
	public static void main(String[] args) {
		
		log.info("main 시작");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");

		//서비스 호출 
		service = (BookService) ctx.getBean("bookServiceImple");
		menu();
	}
	
	public static void menu() {
		
		boolean flag = true;
		
		while (flag) {
			System.out.println("--------------------------");
			System.out.println("1. 도서 정보 조회");
			System.out.println("2. 도서 정보 추가");
			System.out.println("3. 도서 정보 삭제");
			System.out.println("4. 도서 목록 전체조회");
			System.out.println("5. 도서 정보 수정");
			System.out.println("6. 종료");
			System.out.println("--------------------------");
			
			Scanner sc = new Scanner(System.in);
			System.out.print("메뉴 선택 >> ");
			int no = Integer.parseInt(sc.nextLine());
			
			System.out.println();
			
			switch (no) {
			case 1:
				System.out.println("조회할 도서 코드 입력 >> ");
				String code = sc.next();
				BookDTO dto =service.getRow(code);
				
				System.out.print(dto.getCode()+"\t");
				System.out.print(dto.getTitle()+"\t");
				System.out.print(dto.getWriter()+"\t");
				System.out.println(dto.getPrice()+"\n");
				
				break;
			case 2:
				//새로운 도서 입력
//				BookDTO insertDto = new BookDTO("1111","스프링jdbc","쩐다",33000);
				BookDTO insertDto = new BookDTO();
				System.out.println("도서코드 : ");
				insertDto.setCode(sc.nextLine());
				
				System.out.println("도서제목 : ");
				insertDto.setTitle(sc.nextLine());
				
				System.out.println("도서저자 : ");
				insertDto.setWriter(sc.nextLine());
				
				System.out.println("도서가격 : ");
				insertDto.setPrice(Integer.parseInt(sc.nextLine()));
				
				System.out.println(service.insertBook(insertDto)?"입력성공":"입력실패");
				break;
			case 3:
				//도서 정보 삭제
//				System.out.println(service.deleteBook("1005")?"삭제성공":"삭제실패");
				break;
			case 4:
				//전체 리스트 결과 호출 
				List<BookDTO> list = service.getList();
				
				System.out.println("코드      제목      작가      가격");
				System.out.println("----------------------------------");
				for(BookDTO book:list) {
					System.out.print(book.getCode()+"\t");
					System.out.print(book.getTitle()+"\t");
					System.out.print(book.getWriter()+"\t");
					System.out.println(book.getPrice()+"\n");
				}
				break;
			case 5:
				//도서 정보 수정
//				System.out.println(service.updateBook("1006",40000)?"수정성공":"수정실패");
				break;
			case 6:
				flag=false;
				break;
			}
		}
		
		
	}
	
	
}
