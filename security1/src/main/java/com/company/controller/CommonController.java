package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CommonController {
	
	@GetMapping("/")
	public String home() {
		log.info("home..");
		return "index";
	}
}
