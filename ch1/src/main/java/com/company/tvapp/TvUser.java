package com.company.tvapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TvUser{

	
	public static void main(String[] args) {
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationConfig.xml");
		LgTv tv = (LgTv)ctx.getBean("lg");
//		tv.setSpeaker(new SonySpeaker());
		tv.turnOn();
		tv.soundUp();
		tv.soundDown();
		tv.turnOff();
	} 
}
