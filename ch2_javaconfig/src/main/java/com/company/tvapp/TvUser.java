package com.company.tvapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.config.Config;

public class TvUser{

	
	public static void main(String[] args) {
		
		
	//	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationConfig.xml");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		LgTv tv = (LgTv)ctx.getBean("lg");
//		tv.setSpeaker(new SonySpeaker());
		tv.turnOn();
		tv.soundUp();
		tv.soundDown();
		tv.turnOff();
	} 
}
