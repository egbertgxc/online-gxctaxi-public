package com.gxc.apipassenger.service;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.responese.NumberCodeResponse;
import com.gxc.apipassenger.remoto.ServiceVerificationClient;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationClient serviceVerificationClient;

    public String generatorCode(String PassengerPhone){
        // 调用验证码服务,获取验证码
        System.out.println("调用验证码服务,获取验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println("remote number code:"+numberCode);
        //存入redis
        System.out.println("存入redis");
        //返回值
        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }
}
