package com.gxc.serviceverificationcode.controller;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.responese.NumberCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class numberCodeController {
    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        System.out.println("size: "+size);
        //生成验证码
        //获取随机数
        double mathRandom = (Math.random()*9+1)*(Math.pow(10,size-1));
        System.out.println(mathRandom);
        int resultInt= (int)mathRandom;
        System.out.println(resultInt);


//        JSONObject result = new JSONObject();
//        result.put("code",1);
//        result.put("message","success");
//        JSONObject data = new JSONObject();
//        data.put("numberCode",resultInt);
//        result.put("data",data);
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);
        return ResponseResult.success(response);
    }

}
