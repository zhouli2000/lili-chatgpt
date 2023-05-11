package com.lili.lilichatgpt.bean.openai.response.chat;

import com.lili.lilichatgpt.bean.openai.ChatUsage;
import lombok.Data;

import java.util.List;

@Data
public class ChatCompletionResponse {

    private String id;

    private String object;

    private Long created;

    private String model;

    private ChatUsage usage;

    private List<ChatChoices> choices;


}
