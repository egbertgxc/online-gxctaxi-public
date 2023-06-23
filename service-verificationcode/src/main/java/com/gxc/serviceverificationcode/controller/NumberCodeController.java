package com.gxc.serviceverificationcode.controller;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.responese.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {
    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        //生成验证码
        //获取随机数
        double mathRandom = (Math.random()*9+1)*(Math.pow(10,size-1));
        System.out.println(mathRandom);
        int resultInt= (int)mathRandom;
        //定义返回值
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);
        return ResponseResult.success(response);
    }

}
