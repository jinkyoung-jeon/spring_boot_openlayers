package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
	
	@GetMapping("/home")
	public String main() {
		return "index.html";
	}
	
	@GetMapping("/info_pop")
	public String home() {
		return "info_pop_main.html";
	}

}
