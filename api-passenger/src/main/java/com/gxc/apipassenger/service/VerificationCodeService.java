package com.gxc.apipassenger.service;

import com.gxc.internalcommon.constant.CommonStatusEnum;
import com.gxc.internalcommon.constant.IdentityConstants;
import com.gxc.internalcommon.constant.TokenConstants;
import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.request.VerificationDTO;
import com.gxc.internalcommon.responese.NumberCodeResponse;
import com.gxc.internalcommon.responese.TokenResponse;
import com.gxc.internalcommon.util.JwtUtils;
import com.gxc.internalcommon.util.RedisPrefixUtils;
import com.gxc.apipassenger.remoto.ServicePassengerUserClient;
import com.gxc.apipassenger.remoto.ServiceVerificationClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationClient serviceVerificationClient;



    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

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
        System.out.println("生成的key:"+numberCode);
        //存入redis
        //key,value,过期时间
        String key = RedisPrefixUtils.generatorKeyByPhone(PassengerPhone,IdentityConstants.PASSENGER_IDENTITY);
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
        //生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(passengerPhone,IdentityConstants.PASSENGER_IDENTITY);
        // 根据key获取验证码
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value:"+codeRedis);
        //校验验证码
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        //判断原来是否有用户,并进行对应的处理
        System.out.println("判断原来是否有有用户,并进行对应的处理");
        VerificationDTO verificationDTO = new VerificationDTO();
        verificationDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationDTO);
        //派发令牌
        System.out.println("派发令牌");
        //不应该用魔法值,用常量
        String accessToken = JwtUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(passengerPhone, IdentityConstants.PASSENGER_IDENTITY,TokenConstants.REFRESH_TOKEN_TYPE);

        //将token存到redis当中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY,TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(passengerPhone, IdentityConstants.PASSENGER_IDENTITY,TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30,TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31,TimeUnit.DAYS);


        //响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return ResponseResult.success(tokenResponse);
    }
}
