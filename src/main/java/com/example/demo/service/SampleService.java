package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Sample;

public interface SampleService {

    /**
     * 목록 조회
     * @param sample
     * @return
     */
    List<Sample> getList(Sample sample);

}
