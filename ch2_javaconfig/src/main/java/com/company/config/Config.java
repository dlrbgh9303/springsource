package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.company.app.MessageBean;
import com.company.app.MessageBeanEn;
import com.company.tvapp.AppleSpeaker;
import com.company.tvapp.LgTv;
import com.company.tvapp.TV;

@Configuration // 클래스 파일이 환경설정 파일
public class Config {
	
	@Bean // 객체를 생성해서 컨테이너가 관리
	public MessageBean getMessageBean() {
		return new MessageBeanEn();
	}
	
	@Bean
	public TV getTv() {
		return new LgTv(new AppleSpeaker());
	}
	
	
}







