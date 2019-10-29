package com.wy.hodgepodge.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-22 00:03
 */


@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionCode {
    //值
    String value();
    //描述
    String descript();
    //默认参数
    boolean onlySupportPost() default false;

}
