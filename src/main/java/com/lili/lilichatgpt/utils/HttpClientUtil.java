package com.lili.lilichatgpt.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.lili.lilichatgpt.bean.exception.ChatException;
import com.lili.lilichatgpt.constant.Constant;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

    private static final String CHAT = "https://api.openai.com/v1/chat/completions";
    private static final String COMPLETIONS = "https://api.openai.com/v1/completions";

    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String sendOpenAiMessageByChat(String body){
        return send(body, CHAT);
    }

    public static String sendOpenAiMessageByCompletions(String body) {
       return send(body,COMPLETIONS);
    }

    private static String send(String body, String url) {
        try {
            final HttpPost post = new HttpPost(url);
            post.setHeader("Authorization", "Bearer " + Constant.OPEN_AI_KEY_LIST.get(0));
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
            final CloseableHttpResponse response = httpClient.execute(post);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            throw new ChatException(e.getMessage());
        }
    }
}
