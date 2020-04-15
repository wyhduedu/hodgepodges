package com.wy.hodgepodges.common.annotation;

import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Persistent;

import java.lang.annotation.*;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-05 21:46
 */

@Persistent
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Document {
    // 索引库的名称，类似我们的 mysql 库的名称
    String indexName();
    // 文档类型，类似我们的 mysql 表的名称
    String type() default "";
    // 是否使用服务配置
    boolean useServerConfiguration() default false;
    // 分区 默认5
    short shards() default 5;
    // 复制分区 默认1
    short replicas() default 1;
    // 刷新间隔
    String refreshInterval() default "1s";
    // 索引文件存储类型
    String indexStoreType() default "fs";
    // 是否创建索引
    boolean createIndex() default true;

    VersionType versionType() default VersionType.EXTERNAL;
}
