package com.gxc.apipassenger.service;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.responese.NumberCodeResponse;
import com.gax.internalcommon.responese.TokenResponse;
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

    /**
     * 生成验证码
     *
     * @param PassengerPhone 手机号
     * @return
     */
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

    /**
     * 校验验证码
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode){
        //根据手机号,去redis读取验证码
        System.out.println("根据手机号,去redis读取验证码");

        //校验验证码
        System.out.println("校验验证码");
        //判断原来是否有用户,并进行对应的处理
        System.out.println("判断原来是否有有用户,并进行对应的处理");
        //派发令牌
        System.out.println("派发令牌");

        //响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);
    }
}
