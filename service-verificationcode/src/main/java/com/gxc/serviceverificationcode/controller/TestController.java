package com.gxc.serviceverificationcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String Test(){
        return "service-verificationcode";
    }
    @GetMapping("/testw")
    public String Testw(){
        return "service-verificationcodggggggggggggg";
    }
}
