package com.apnabank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

	@GetMapping({"/", "/index", "/home"})
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "admin";
	}
	
	@GetMapping("/db")
	public String getDb() {
		return "db";
	}
	
	@GetMapping("/access_denied")
	public String getAcessDenied() {
		return "access_denied";
	}
}
