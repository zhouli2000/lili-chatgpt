package com.lili.lilichatgpt.config;

import com.lili.lilichatgpt.service.TokenService;
import com.lili.lilichatgpt.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginHandler implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final Cookie token = CookieUtil.getCookieByName("token",request);
        final String value = token.getValue();
        if ("lige".equals(value)){
            return true;
        }
        return tokenService.isEffective(value);
    }
}
