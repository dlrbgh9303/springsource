package com.company.tvapp;

public class SamsungTv implements TV{
	
	
	public SamsungTv () {
		System.out.println("SamsungTV 객체 생성");
	}
	
	
	@Override
	public void turnOn() {
		System.out.println("SamsungTv - 파워 On");
		
	}
	@Override
	public void turnOff() {
		System.out.println("SamsungTv - 파워 Off");
		
	}
	@Override
	public void soundUp() {
		System.out.println("SamsungTv - 볼륨 Up");
		
	}
	@Override
	public void soundOff() {
		System.out.println("SamsungTv - 볼륨 Down");
		
	}
	@Override
	public void soundDown() {
		// TODO Auto-generated method stub
		
	}
}
