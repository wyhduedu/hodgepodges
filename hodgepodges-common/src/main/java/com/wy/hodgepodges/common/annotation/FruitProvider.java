package com.wy.hodgepodges.common.annotation;

import java.lang.annotation.*;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-31 15:51
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    /**
     * 供应商编号
     * @return
     */
    public int id() default -1;

    /**
     * 供应商名称
     * @return
     */
    public String name() default "";

    /**
     * 供应商地址
     * @return
     */
    public String address() default "";
}
