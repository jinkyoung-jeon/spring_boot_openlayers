package com.example.demo.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Sample;

@Repository
public interface SampleMapper {

    /**
     * 목록 조회
     * @param sample
     * @return
     */
    List<Sample> getList(Sample sample);

}