package com.lili.lilichatgpt.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <R> Result<R> success(R data){
        return new Result<>(200,"处理成功",data);
    }

    public static <R> Result<R> fail(String msg){
        return new Result<>(500,msg,null);
    }

}
