package com.company.tvapp;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("lg")
public class LgTv implements TV {
	
//	@Autowired // 생성된 객체를 주입(Speaker 를 구현하는 모든 클래스가 대상)
	
	@Inject
	@Qualifier("apple") // 이름 지정 기능 밖에 없음
	private Speaker speaker;
	
	
	public LgTv() {
		System.out.println("LgTV 객체생성");
	}
	

//	public LgTv(Speaker speaker) {
//		super();
//		this.speaker = speaker;
//	} 	
//	
//	public void setSpeaker(Speaker speaker) {
//		this.speaker = speaker;
//	}
	

	
	@Override
	public void turnOn() {
		System.out.println("LgTv - 전원 On");
	}
	@Override
	public void turnOff() {
		System.out.println("LgTv - 전원 Off");
	}
	@Override
	public void soundUp() {
//		System.out.println("LgTv - 볼륨 Up");
//		speaker = new SonySpeaker();
		speaker.volumeUp();
	
	}
	@Override
	public void soundDown() {
//		System.out.println("LgTv - 볼륨 Off");
//		speaker = new SonySpeaker();
		speaker.volumeDown();
		
		
		
		

		
	}
}
