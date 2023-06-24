package com.gxc.apipassenger.service;

import com.gax.internalcommon.dto.PassengerUser;
import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.dto.TokenResult;
import com.gax.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    public ResponseResult getUserByAccessToken(String accessToken){
        //解析accessToken,拿到手机号
        log.info("accessToken:"+accessToken);
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号:"+phone);
        //根据手机号查询用户信息

        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("张三");
        passengerUser.setProfilePhoto("头像");
        return ResponseResult.success(passengerUser);
    }
}
