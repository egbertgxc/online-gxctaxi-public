package com.gxc.serviceverificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String Test(){
        return "service-verificationcode";
    }
    @GetMapping("/test1")
    public String Test1(){
        return "service-verificationcode";
    }

}
