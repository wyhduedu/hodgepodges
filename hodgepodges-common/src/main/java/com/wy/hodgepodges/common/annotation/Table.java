package com.wy.hodgepodges.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author wy
 * @version V1.0
 * @desc 测试注解
 * @date 2019-10-31 14:49
 */
@Target({ElementType.TYPE})
public @interface Table {

    String tableName() default "a";
}
