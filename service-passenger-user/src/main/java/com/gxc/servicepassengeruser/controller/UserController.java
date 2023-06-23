package com.gxc.servicepassengeruser.controller;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.request.VerificationDTO;
import com.gxc.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationDTO verificationDTO){
        String passengerPhone = verificationDTO.getPassengerPhone();
        System.out.println("手机号"+passengerPhone);
        return userService.loginOrRegister(passengerPhone);
    }
}
