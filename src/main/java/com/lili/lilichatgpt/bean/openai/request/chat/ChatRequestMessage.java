package com.lili.lilichatgpt.bean.openai.request.chat;

import lombok.Data;

@Data
public class ChatRequestMessage {

    private String role;

    private String content;

    private String name;
}
