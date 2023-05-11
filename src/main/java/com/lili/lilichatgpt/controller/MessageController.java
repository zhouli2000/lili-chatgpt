package com.lili.lilichatgpt.controller;

import com.lili.lilichatgpt.annotations.ApiV1Controller;
import com.lili.lilichatgpt.bean.Result;
import com.lili.lilichatgpt.service.MessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ApiV1Controller("message/send")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("chat")
    public Result<List<String>> sendMessage(@RequestBody String msg, HttpServletRequest request){
        if (StringUtils.isBlank(msg)){
            return Result.fail("发送消息不能为空");
        }
       return Result.success(messageService.sendMessage(msg,request));
    }

    @PostMapping("completions")
    public Result<String> sendMessageCompletions(@RequestBody String msg){
        if (StringUtils.isBlank(msg)){
            return Result.fail("发送消息不能为空");
        }
        return Result.success(messageService.sendMessageCompletions(msg));
    }
}
