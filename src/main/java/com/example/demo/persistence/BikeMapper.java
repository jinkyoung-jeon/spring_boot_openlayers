package com.example.demo.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Bike;

@Repository
public interface BikeMapper {
	/**
	 * 목록 조회
	 * @param bike
	 * @return
	 */
	List<Bike> getList(Bike bike);
}
