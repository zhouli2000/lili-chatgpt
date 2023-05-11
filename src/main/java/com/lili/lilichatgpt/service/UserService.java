package com.lili.lilichatgpt.service;

import com.lili.lilichatgpt.bean.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private TokenService tokenService;

    public Boolean login(String userName, String password, HttpServletResponse response){
        final Boolean isLogin = cacheService.login(userName, password);
        if (isLogin){
            final LoginUser loginUser = new LoginUser();
            loginUser.setUserName(userName);
            loginUser.setPassword(password);
            final Cookie cookie = new Cookie("token",tokenService.getToken(loginUser));
            response.addCookie(cookie);
        }
        return isLogin;
    }

    public Boolean register(String userName, String password) {
        if (! cacheService.isRegistered(userName)){
            final LoginUser loginUser = new LoginUser();
            loginUser.setUserName(userName);
            loginUser.setPassword(password);
            cacheService.register(loginUser);
            return true;
        }
        return false;
    }
}
