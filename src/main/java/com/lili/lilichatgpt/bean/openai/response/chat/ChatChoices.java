package com.lili.lilichatgpt.bean.openai.response.chat;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ChatChoices {

    private ChatResponseMessage message;

    @SerializedName("finish_reason")
    private String finishReason;

    private Integer index;

}
