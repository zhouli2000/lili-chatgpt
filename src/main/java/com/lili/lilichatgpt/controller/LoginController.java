package com.lili.lilichatgpt.controller;

import com.lili.lilichatgpt.annotations.ApiV1Controller;
import com.lili.lilichatgpt.bean.Result;
import com.lili.lilichatgpt.bean.exception.ChatException;
import com.lili.lilichatgpt.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@ApiV1Controller("user")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result<Boolean> login(String userName, String password, HttpServletResponse response){
        if (StringUtils.isAnyBlank(userName,password)){
            throw new ChatException("账号密码不能为空");
        }
        return Result.success(userService.login(userName,password,response));
    }

    @PostMapping("register")
    public Result<Boolean> register(String userName,String password){
        if (StringUtils.isAnyBlank(userName,password)){
            throw new ChatException("账号密码不能为空");
        }
        return Result.success(userService.register(userName,password));
    }
}
