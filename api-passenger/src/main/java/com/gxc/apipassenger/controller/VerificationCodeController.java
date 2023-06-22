package com.gxc.apipassenger.controller;

import com.gxc.apipassenger.request.VerificationDTO;
import com.gxc.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationDTO verificationDTO){
        String passengerPhone = verificationDTO.getPassengerPhone();
        System.out.println("接受到手机号参数"+passengerPhone);

        return verificationCodeService.generatorCode(passengerPhone);
    }
}
