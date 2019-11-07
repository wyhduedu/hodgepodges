package com.wy.hodgepodges.common.annotation;

import java.lang.annotation.*;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-31 15:50
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface FruitColor {
    /**
     * 颜色枚举
     *
     * @author peida
     */
   public enum Color {BULE, RED, GREEN}

    /**
     * 颜色属性
     *
     * @return
     */
   public Color fruitColor() default Color.GREEN;

}
