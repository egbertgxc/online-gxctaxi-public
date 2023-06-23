package com.gxc.apipassenger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.util.JwtUtils;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;

public class JwtInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = true;
        String resutltString = "";
        String token = request.getHeader("Authorization");
        try {
            JwtUtils.parseToken(token);
        }catch (SignatureVerificationException e){
            resutltString = "token sing error";
            result = false;
        }catch (TokenExpiredException e){
            resutltString = "token time out";
            result = false;
        }catch (AlgorithmMismatchException e){
            resutltString = "token AlgorithmMismatchException";
            result = false;
        }catch (Exception e){
            resutltString = "token invalid";
            result = false;
        }

        if (!result) {
            PrintWriter out = response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resutltString)).toString());
        }
        return true;
    }
}