package com.wy.hodgepodges.common.annotation;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.lang.annotation.*;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-05 21:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited
public @interface Field {
    // 自动检测属性的类型
    FieldType type() default FieldType.Auto;
    // 是否创建属性的索引
    boolean index() default true;
    //
    DateFormat format() default DateFormat.none;

    String pattern() default "";
    // 是否存储
    boolean store() default false;

    boolean fielddata() default false;
    // 指定字段搜索时使用的分词器
    String searchAnalyzer() default "";
    // 指定分词器，存储字段时
    String analyzer() default "";

    String normalizer() default "";
    // 如果某个字段需要被忽略，就加进去
    String[] ignoreFields() default {};
    // 是否解析
    boolean includeInParent() default false;

    String[] copyTo() default {};
}
