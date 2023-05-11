package com.lili.lilichatgpt.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    private static final Gson gson;

    static {
        final GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = builder.create();
    }

    public static String toJson(Object obj){
        return gson.toJson(obj);
    }

    public static <T> T toObject(String json,Class<T> aClass){
        return gson.fromJson(json,aClass);
    }
}
