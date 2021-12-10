package com.company.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileCheckTask {
	
	
	//fixedDelay : 이전에 실해된 Task의 종료 시간으로부터 정의된 시간만큼 지난후 실행 
	//fixedRate : 이전에 실해된 Task의 시작 시간으로부터 정의된 시간만큼 지난후 실행 
	
	
	
	//@Scheduled : 메소드 리턴타입은 void, 파라미터는 갖지 않아야함
	
	
	@Scheduled(cron="0 * * * * *")
	public void schedulerTest() {
		System.out.println("매 분 1초마다 스케쥴링");
	}
	@Scheduled(fixedDelay = 10000)
	public void schedulerTest2() {
		System.out.println("10초마다 스케쥴링");
	}
}














