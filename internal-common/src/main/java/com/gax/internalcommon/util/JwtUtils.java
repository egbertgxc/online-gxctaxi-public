package com.gax.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gax.internalcommon.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    // 盐
    private static final String SIGN = "CPEdf";
    private static final String JWT_KEY_PHONE = "phone";
    // 乘客是1,司机是2
    private static final String JWT_KEY_IDENTITY = "identity";

    // 生成token
    public static String generatorToken(String passengerPhone,String identity) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENTITY,identity);
        // token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        map.forEach(
                (k, v) -> {
                    builder.withClaim(k, v);
                }
        );
        //整合过期时间
        builder.withExpiresAt(date);

        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));
        return sign;
    }
    // 解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).toString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).toString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }


    public static void main(String[] args) {
        String s = generatorToken("135687554231","1");
        System.out.println("生成的token:"+s);
        TokenResult tokenResult = parseToken(s);
        System.out.println("手机号"+tokenResult.getPhone()+"身份"+tokenResult.getIdentity());
    }
}