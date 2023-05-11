package com.lili.lilichatgpt.bean.openai.request.completions;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CompletionsRequest {

    private String model;

    private String prompt;

    @SerializedName("max_tokens")
    private Integer maxTokens;

    private Double temperature;

    @SerializedName("top_p")
    private Integer topP;

    private Integer n;

    private Boolean stream;

    private String logprobs;

    private String stop;

}
