package com.wy.hodgepodges.moudle.vo;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author wy
 * @version V1.0
 * @desc 日志
 * @date 2020-04-05 21:45
 */

@Data
@Document(indexName = "blog",type = "blogInfo",shards = 1,replicas = 1)
public class BlogInfo {

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 标题
     * 是否需要分词？  Text 表示文本，可被分词也可以被索引，keyword表示为关键字，
     *                  不分词，但可以被索引，只是String可被分词
     * 是否需要索引？  index为true表示可被索引，为false表示不可被索引
     * 是否需要存储？  store为true表示可被存储，为false表示不可被存储
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    /**
     * 关键字
     */
    @Field(type = FieldType.Keyword)
    private String keyword;

    /**
     * 内容
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String content;

}

