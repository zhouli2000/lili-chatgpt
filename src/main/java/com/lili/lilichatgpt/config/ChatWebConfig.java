package com.lili.lilichatgpt.config;

import com.lili.lilichatgpt.annotations.ApiV1Controller;
import com.lili.lilichatgpt.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ChatWebConfig implements WebMvcConfigurer{

    @Autowired
    private LoginHandler loginHandler;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(Constant.FIX_PATH, c -> c.isAnnotationPresent(ApiV1Controller.class));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandler)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/chat/user/login/**",
                        "/chat/user/register/**"
                );
    }
}
