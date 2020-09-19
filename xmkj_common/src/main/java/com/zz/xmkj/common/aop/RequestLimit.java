package com.zz.xmkj.common.aop;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
    /**
     * 允许访问最大次数
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段
     */
    long time() default 6000;

    /**
     * redis的key值
     * @return
     */
    String key() default "";
}