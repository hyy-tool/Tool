package com.hyy.htool.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author :  HYY
 * 时间: 2022/10/14
 * 描述: 实现防止连续点击
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SingleClick {
    /**
     * 点击间隔时间 可以更改 @SingleClick(500)
     */
    long value() default 1500;
}