package com.myappbbsbackend.Interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @ Description: token 拦截器
 * @ Author: 小火锅
 * @ Date: 2020/11/22 19:41
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(
                authenticationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**","/v3/**", "/v2/**", "/swagger-ui/**");
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new AuthenticationInterceptor();
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        // 设置允许跨域的路径
//        registry.addMapping("/**")
//                // 设置允许跨域请求的域名
//                .allowedOrigins("http://localhost:8100")
//                // 是否允许证书 不再默认开启
//                .allowCredentials(false)
//                // 设置允许的方法
//                .allowedMethods("*")
//                // 跨域允许时间
//                .maxAge(3600);
    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }


}
