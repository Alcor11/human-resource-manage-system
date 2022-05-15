package com.hrm.aspectlj.config;

import java.lang.annotation.*;

/**
 * @author guchun
 * @description TODO
 * @date 2022/5/1 21:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface TokenVerify {
}
