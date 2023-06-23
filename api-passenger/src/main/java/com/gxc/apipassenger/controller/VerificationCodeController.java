package com.gxc.apipassenger.controller;

import com.gax.internalcommon.dto.ResponseResult;
import com.gxc.apipassenger.request.VerificationDTO;
import com.gxc.apipassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationDTO verificationDTO){
        String passengerPhone = verificationDTO.getPassengerPhone();
        return verificationCodeService.generatorCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationDTO verificationDTO){
        String passengerPhone = verificationDTO.getPassengerPhone();
        String verificationCode = verificationDTO.getVerificationCode();

        System.out.println("手机号:"+passengerPhone+"验证码:"+verificationCode);
        return verificationCodeService.checkCode(passengerPhone,verificationCode);
    }


}
