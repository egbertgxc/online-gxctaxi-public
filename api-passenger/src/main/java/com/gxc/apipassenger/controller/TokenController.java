package com.gxc.apipassenger.controller;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.responese.TokenResponse;
import com.gxc.apipassenger.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;
    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的refreToken"+refreshTokenSrc);
        return tokenService.refreshToken(refreshTokenSrc);

    }
}
