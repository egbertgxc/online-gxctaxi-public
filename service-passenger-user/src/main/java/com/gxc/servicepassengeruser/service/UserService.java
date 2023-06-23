package com.gxc.servicepassengeruser.service;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.request.VerificationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    public ResponseResult loginOrRegister(String passagePhone){
        System.out.println("user service被调用,手机号:"+passagePhone);
        //根据手机号查询用户信息
        //判断用户信息是否存在
        //如果不存在,插入用户
        return ResponseResult.success();
    }
}
