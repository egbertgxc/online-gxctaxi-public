package com.gxc.servicepassengeruser.controller;

import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.request.VerificationDTO;
import com.gxc.servicepassengeruser.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationDTO verificationDTO){
        String passengerPhone = verificationDTO.getPassengerPhone();
        System.out.println("手机号"+passengerPhone);
        log.info("注册用户信息");
        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone){
//        String passengerPhone = verificationDTO.getPassengerPhone();
        log.info("用户信息");
        return userService.getUserByPhone(passengerPhone);
    }
}
