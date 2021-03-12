package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Bike;

public interface BikeService {

	/** 
	 * 목록 조회
	 * @param bike
	 * @return
	 * */
	List<Bike> getList(Bike bike);
	
}
