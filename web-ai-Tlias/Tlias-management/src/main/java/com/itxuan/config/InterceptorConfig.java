package com.itxuan.config;

import com.itxuan.Interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//这是一个配置类
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

//    //添加拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor( tokenInterceptor)
//                .addPathPatterns("/**")//表示拦截所有；/*只能拦截一层，比如/user，不能拦截/user/add，**代表多级路径，*代表一级路径，路径参数也算一个路径
//                .excludePathPatterns("/login");//表示不拦截/login
//    }
}
