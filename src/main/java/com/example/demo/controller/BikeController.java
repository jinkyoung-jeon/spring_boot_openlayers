package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Bike;
import com.example.demo.service.BikeService;

@Controller
@RequestMapping("/bike")
public class BikeController {
	
	@GetMapping("/home")
	public String main() {
		return "index.html";
	}
	
	@GetMapping("/info_pop")
	public String home() {
		return "info_pop_main.html";
	}
	
	@Autowired
	BikeService bikeService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getList(Bike bike) {
		try {
			List<Bike> list = bikeService.getList(bike);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

}
