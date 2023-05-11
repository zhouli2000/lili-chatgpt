package com.lili.lilichatgpt.service;

import com.lili.lilichatgpt.bean.LoginUser;
import com.lili.lilichatgpt.bean.openai.request.chat.ChatRequest;
import com.lili.lilichatgpt.bean.openai.request.chat.ChatRequestMessage;
import com.lili.lilichatgpt.bean.openai.request.completions.CompletionsRequest;
import com.lili.lilichatgpt.bean.openai.response.chat.ChatChoices;
import com.lili.lilichatgpt.bean.openai.response.chat.ChatCompletionResponse;
import com.lili.lilichatgpt.bean.openai.response.chat.ChatResponseMessage;
import com.lili.lilichatgpt.bean.openai.response.completions.CompletionsChoices;
import com.lili.lilichatgpt.bean.openai.response.completions.CompletionsResponse;
import com.lili.lilichatgpt.utils.CookieUtil;
import com.lili.lilichatgpt.utils.HttpClientUtil;
import com.lili.lilichatgpt.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private CacheService cacheService;

    public List<String> sendMessage(String msg, HttpServletRequest request) {
        String name = getUserName(request);

        final ChatRequest chatCompletionRequest = new ChatRequest();
        chatCompletionRequest.setModel("gpt-3.5-turbo");
        chatCompletionRequest.setMessages(cacheService.getRecord(name,msg));
        chatCompletionRequest.setTemperature(0.5);
        chatCompletionRequest.setN(1);
        chatCompletionRequest.setStream(false);
        chatCompletionRequest.setMaxTokens(256);

        final String responseStr = HttpClientUtil.sendOpenAiMessageByChat(JsonUtil.toJson(chatCompletionRequest));

        final ChatCompletionResponse chatCompletionResponse = JsonUtil.toObject(responseStr, ChatCompletionResponse.class);
        final List<ChatChoices> choices = chatCompletionResponse.getChoices();
        return choices.stream().map(ChatChoices::getMessage).map(ChatResponseMessage::getContent).collect(Collectors.toList());
    }

    public String sendMessageCompletions(String msg ) {
        final CompletionsRequest completionsRequest = new CompletionsRequest();
        completionsRequest.setModel("text-davinci-003");
        completionsRequest.setPrompt(msg);
        completionsRequest.setMaxTokens(50);
        completionsRequest.setTemperature(0.5);
        completionsRequest.setTopP(1);
        completionsRequest.setN(1);
        completionsRequest.setStream(false);
        completionsRequest.setLogprobs(null);
        completionsRequest.setStop("\n");

        final String responseStr = HttpClientUtil.sendOpenAiMessageByCompletions(JsonUtil.toJson(completionsRequest));
        final CompletionsResponse completionsResponse = JsonUtil.toObject(responseStr, CompletionsResponse.class);
        final List<CompletionsChoices> choices = completionsResponse.getChoices();
        if (! choices.isEmpty()){
            return choices.get(0).getText();
        }
        return "回复失败";
    }

    private String getUserName(HttpServletRequest request) {
        final Cookie token = CookieUtil.getCookieByName("token", request);
        final String value = token.getValue();
        String name = "lige";
        if (! "lige".equals(value)){
            final String subject = tokenService.getSubject(token.getValue());
            final LoginUser loginUser = JsonUtil.toObject(subject, LoginUser.class);
            name = loginUser.getUserName();
        }
        return name;
    }
}
