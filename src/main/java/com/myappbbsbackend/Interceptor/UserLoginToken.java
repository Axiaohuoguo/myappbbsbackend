package com.myappbbsbackend.Interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ Description:
 * @ Author: 小火锅
 * @ Date: 2020/11/22 20:00
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}
