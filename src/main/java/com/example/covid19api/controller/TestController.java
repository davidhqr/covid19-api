package com.example.covid19api.controller;

import com.example.covid19api.model.Test;
import com.example.covid19api.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/tests")
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
}
