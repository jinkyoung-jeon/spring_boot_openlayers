package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Bike;
import com.example.demo.persistence.BikeMapper;
import com.example.demo.service.BikeService;

@Service
public class BikeServiceImpl implements BikeService {

	@Autowired
	BikeMapper bikeMapper;
	
	@Transactional(readOnly = true)
	public List<Bike> getList(Bike bike) {
		return bikeMapper.getList(bike);
	}
}
