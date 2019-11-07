package com.wy.hodgepodges.common.annotation;

import java.lang.annotation.*;

/**
 * @author wy
 * @version V1.0
 * @desc 日志注解
 * @date 2019-11-01 10:13
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";

}
