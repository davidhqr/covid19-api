package com.example.covid19api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContainer {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Meenie!";
    }
}
