package com.lili.lilichatgpt.bean.openai.request.chat;

import lombok.Data;

import java.util.List;

@Data
public class ChatRequest {

    private String model;

    private List<ChatRequestMessage> messages;

    private Double temperature;

    private Integer n;

    private Boolean stream;

    private Integer maxTokens;

    private String user;

}
