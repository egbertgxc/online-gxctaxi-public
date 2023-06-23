package com.gxc.servicepassengeruser.service;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.request.VerificationDTO;
import com.gxc.servicepassengeruser.dto.PassengerUser;
import com.gxc.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("user service被调用,手机号:"+passengerPhone);
        //根据手机号查询用户信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size() == 0?"无记录":passengerUsers.get(0).getPassengerPhone());
        //判断用户信息是否存在
        //如果不存在,插入用户
        
        return ResponseResult.success();
    }
}
