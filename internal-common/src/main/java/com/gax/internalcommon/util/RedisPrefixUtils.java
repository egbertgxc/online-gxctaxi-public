package com.gax.internalcommon.util;

public class RedisPrefixUtils {

    //乘客验证码前缀
    public static String verificationCodePrefix = "possenger-verification-code-";
    //token的前缀
    public static String tokenPrefix = "token-";

    /**
     * 根据手机号生成key
     * @param PassengerPhone
     * @return
     */
    public static String generatorKeyByPhone(String PassengerPhone) {
        return verificationCodePrefix+ PassengerPhone;
    }

    /**
     * 根据手机号和省份标识生成token
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone,String identity,String tokenType){
        return tokenPrefix+phone+"-"+identity+"-"+tokenType;
    }
}
