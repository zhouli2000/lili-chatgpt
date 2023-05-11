package com.lili.lilichatgpt.bean.openai.response.completions;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CompletionsChoices {

    private String text;

    private Integer index;

    private String logprobs;

    @SerializedName("finish_reason")
    private String finishReason;

}
