package com.gxc.apipassenger.interceptor;

import com.gxc.internalcommon.constant.TokenConstants;
import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.dto.TokenResult;
import com.gxc.internalcommon.util.JwtUtils;
import com.gxc.internalcommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        boolean result = true;
        String resutltString = "";
        String token = request.getHeader("Authorization");
        //解析token
        TokenResult tokenResult = JwtUtils.checkToken(token);

        if (tokenResult == null) {
            resutltString= "Access token invalid";
            result = false;
        }else {
            //拼接key
            String phone = tokenResult.getPhone();
            String identity = tokenResult.getIdentity();
            String tokenkey = RedisPrefixUtils.generatorTokenKey(phone,identity, TokenConstants.ACCESS_TOKEN_TYPE);
            //从redis中取出token
            String tokenKeyRedis = stringRedisTemplate.opsForValue().get(tokenkey);
            if (StringUtils.isBlank(tokenkey) || !token.trim().equals(tokenKeyRedis.trim())) {
                resutltString= "token invalid";
                result = false;
            }

        }

        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resutltString)).toString());
        }
        return true;
    }
}
