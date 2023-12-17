package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Hello {
    @GetMapping("/test")
    public String sayHello(){
        return "Hey Hi There!!!";
    }
}
