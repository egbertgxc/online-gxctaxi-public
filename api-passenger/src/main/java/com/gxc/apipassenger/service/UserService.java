package com.gxc.apipassenger.service;

import com.gax.internalcommon.dto.PassengerUser;
import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.dto.TokenResult;
import com.gax.internalcommon.request.VerificationDTO;
import com.gax.internalcommon.util.JwtUtils;
import com.gxc.apipassenger.remoto.ServicePassengerUserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;


    public ResponseResult getUserByAccessToken(String accessToken){
        //解析accessToken,拿到手机号
        log.info("accessToken:"+accessToken);
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("手机号:"+phone);
        //根据手机号查询用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);

        return ResponseResult.success(userByPhone);
    }
}
