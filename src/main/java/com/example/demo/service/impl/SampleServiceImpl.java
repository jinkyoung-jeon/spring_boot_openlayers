package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Sample;
import com.example.demo.persistence.SampleMapper;
import com.example.demo.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    SampleMapper sampleMapper;

    @Transactional(readOnly = true)
    public List<Sample> getList(Sample sample) {
        return sampleMapper.getList(sample);
    }

}
