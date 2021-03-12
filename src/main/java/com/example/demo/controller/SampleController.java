package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Sample;
import com.example.demo.service.SampleService;

@Controller
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    SampleService sampleService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(HttpServletRequest request) {
        try {
            List<Sample> list = sampleService.getList(new Sample());
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
