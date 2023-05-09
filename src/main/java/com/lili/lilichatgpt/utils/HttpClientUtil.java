package com.lili.lilichatgpt.utils;
import java.util.Collections;

import com.lili.lilichatgpt.bean.ChatCompletionRequest;
import com.lili.lilichatgpt.bean.ChatMessage;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtil {

    public static void main(String[] args) {
        final ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("chatGPT 你好");
        chatMessage.setRole("user");

        final ChatCompletionRequest request = new ChatCompletionRequest();
        request.setModel("gpt-3.5-turbo");
        request.setMessage(Collections.singletonList(chatMessage));
        request.setTemperature(0.7D);
        request.setN(1);
        request.setStream(false);
        request.setMaxTokens(1);
        request.setUser("testing");
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");


    }

}
