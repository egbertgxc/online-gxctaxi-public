package com.gxc.apipassenger.service;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.responese.NumberCodeResponse;
import com.gxc.apipassenger.remoto.ServiceVerificationClient;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationClient serviceVerificationClient;

    //乘客验证码前缀
    private String verificationCodePrefix = "possenger-verification-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generatorCode(String PassengerPhone){
        // 调用验证码服务,获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        //存入redis
        //key,value,过期时间
        String key=verificationCodePrefix+PassengerPhone;
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);
        //通过短效服务商,将对应的验证码发送到手机山,阿里短信,腾讯短信,华信,容量
        return ResponseResult.success("");
    }
}
