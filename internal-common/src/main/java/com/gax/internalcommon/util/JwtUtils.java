package com.gax.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    // 盐
    private static final String SIGN = "CPEdf";

    // 生成token
    public static String generatorToken(Map<String, String> map) {
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


    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name","zhang san");
        hashMap.put("age","18");
        String s = generatorToken(hashMap);
        System.out.println("生成的token:"+s);
    }
}
