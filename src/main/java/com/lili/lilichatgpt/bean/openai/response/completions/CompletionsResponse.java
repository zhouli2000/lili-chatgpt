package com.lili.lilichatgpt.bean.openai.response.completions;

import com.lili.lilichatgpt.bean.openai.ChatUsage;
import lombok.Data;

import java.util.List;

@Data
public class CompletionsResponse {

    private String id;

    private String object;

    private Long created;

    private String model;

    private List<CompletionsChoices> choices;

    private ChatUsage usage;
}
