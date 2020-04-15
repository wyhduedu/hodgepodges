package com.wy.hodgepodges.common.drools;

import lombok.Data;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-15 18:25
 */
@Data
public class Message {
    public static final Integer HELLO = 0;
    public static final Integer GOODBYE = 1;

    private String message;

    private Integer status;

}