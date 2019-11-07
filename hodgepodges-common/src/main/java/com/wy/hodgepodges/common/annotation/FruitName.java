package com.wy.hodgepodges.common.annotation;

import java.lang.annotation.*;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-31 15:04
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String name() default "fieldName";

    String setFuncName() default "setField";

    String getFuncName() default "getField";

    boolean defaultDBValue() default false;

}
