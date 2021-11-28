package com.company.app;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.ChangeDTO;
import com.company.domain.MemberDTO;
import com.company.service.MemberService;

public class MemberClient {
	
	
	public static void main(String[] args) {
		
		// 스프링 컨테이너 로드작업 
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		// Service 호출 작업을 해준다.
		MemberService service =  (MemberService) ctx.getBean("service");
		
		
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		
		while(flag) {
			System.out.println("=====================================================");
			System.out.println("1. 전체 멤버 조회");
			System.out.println("2. 특정 멤버 조회");
			System.out.println("3. 특정 멤버 수정");
			System.out.println("4. 특정 멤버 삭제");
			System.out.println("5. 특정 멤버 추가");
			System.out.println("6. 프로그램  종료");
			System.out.println("=====================================================");
			
			System.out.print("메뉴 >> ");
			int no = Integer.parseInt(sc.nextLine());
			
			switch (no) {
			case 1:
				List<MemberDTO> list = service.getList();
				System.out.println();
				System.out.println("아이디\t 성명\t 성별\t 이메일\t");
				System.out.println("=================================================");
				for(MemberDTO dto : list) {
					System.out.print(dto.getUserid()+"\t");
					System.out.print(dto.getName()+"\t");
					System.out.print(dto.getGender()+"\t");
					System.out.println(dto.getEmail());
				}
				System.out.println();
				break;
			case 2:
				System.out.println("조회할 사용자 정보입력");
				System.out.println("===================");
				
				System.out.print("아이디를 입력하세요 : ");
				String id = sc.nextLine();
				System.out.print("비밀번호를 입력하세요 : ");
				String password= sc.nextLine();
				
				MemberDTO dto = service.getRow(id, password);
				
				System.out.println("조회하신 사용자 정보");
				System.out.print("userid : "+dto.getUserid()+"\t");
				System.out.print("name : "+dto.getName()+"\t");
				System.out.print("gender : "+dto.getGender()+"\t");
				System.out.println("email : "+dto.getEmail());
				System.out.println();
				
				break;
			case 3:
				ChangeDTO changeDto = new ChangeDTO();
				System.out.println("수정할 사용자 정보입력");
				System.out.println("===================");
				
				System.out.print("아이디를 입력하세요 : ");
				String updateId = sc.nextLine();
				System.out.print("비밀번호를 입력하세요 : ");
				String updatePassword= sc.nextLine();
			
				MemberDTO updateDto = service.getRow(updateId, updatePassword);
				
				changeDto.setUserid(updateId);
				changeDto.setPassword(updatePassword);
				
				if(updateDto!=null) {
					changeDto.setConfirm_password(sc.nextLine());

					System.out.print("수정 결과는 다음과 같습니다 : ");
					System.out.println(service.updateRow(changeDto) ? "수정성공" : "수정실패");
				}else {
					System.out.println("아이디랑 비밀번호가 틀렸습니다.");
				}
				break;
			case 4:
				System.out.print("아이디를 입력하세요 : ");
				String deleteId = sc.nextLine();
				System.out.print("비밀번호를 입력하세요 : ");
				String deletePassword= sc.nextLine();
				System.out.println(service.deleteRow(deleteId, deletePassword) ? "삭제성공" : "삭제실패");
				break;
			case 5:
				MemberDTO insertDto = new MemberDTO();
				
				System.out.println("추가할 사용자 정보 ");
				System.out.println("======================");

				System.out.print("아이디를 입력하세요 : ");
				insertDto.setUserid(sc.nextLine());
				System.out.print("비밀번호를 입력하세요 : ");
				insertDto.setPassword(sc.nextLine());
				System.out.print("이름을 입력하세요 : ");
				insertDto.setName(sc.nextLine());
				System.out.print("성별을 입력하세요(남/여) : ");
				insertDto.setGender(sc.nextLine());
				System.out.print("이메일을 입력하세요 : ");
				insertDto.setEmail(sc.nextLine());
				System.out.println("회원가입 결과는 다음과 같습니다.");
				System.out.println(service.insertRow(insertDto) ? "가입성공" : "가입실패");
				break;

			default:
				flag=false;
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}

}
