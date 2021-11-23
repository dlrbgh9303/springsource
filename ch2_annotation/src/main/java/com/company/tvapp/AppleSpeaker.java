package com.company.tvapp;

import org.springframework.stereotype.Component;

@Component("apple")
public class AppleSpeaker implements Speaker {

	public AppleSpeaker () {
		System.out.println("AppleSpeaker 객체 생성");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("AppleSpeaker volume Up");
	}

	@Override
	public void volumeDown() {
		System.out.println("AppleSpeaker volume Down");
	}

}
