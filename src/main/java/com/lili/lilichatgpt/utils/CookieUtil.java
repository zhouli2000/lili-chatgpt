package com.lili.lilichatgpt.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CookieUtil {

    public static Cookie getCookieByName(String name,HttpServletRequest request){
        final Map<String, Cookie> cookieMap = Arrays.stream(request.getCookies())
                .collect(Collectors.toMap(Cookie::getName, Function.identity()));
        return cookieMap.get(name);
    }

}
