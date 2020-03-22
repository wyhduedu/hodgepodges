package com.wy.hodgepodges.common.annotation;

import java.lang.annotation.*;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-17 14:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {

    String name ();

}
