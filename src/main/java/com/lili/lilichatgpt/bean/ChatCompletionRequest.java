package com.lili.lilichatgpt.bean;

import lombok.Data;

import java.util.List;

@Data
public class ChatCompletionRequest {

    private String model;

    private List<ChatMessage> message;

    private Double temperature;

    private Integer n;

    private Boolean stream;

    private Integer maxTokens;

    private String user;

}
