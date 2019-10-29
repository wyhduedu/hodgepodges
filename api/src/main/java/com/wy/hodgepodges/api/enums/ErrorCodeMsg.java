package com.wy.hodgepodges.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author weiyu
 * @version V1.0
 * @since 2018-12-07
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeMsg {
    /**
     * 错误码
     */
    SUCCESS(200, "成功"),

    SECTSUCCESS(201,"部分成功"),

    MISSING_REQUEST_NO(203, "请求号不能为空"),

    DATA_NOT_FOUND(204, "数据不存在"),

    FAIL(501, "操作失败"),

    PARAM_INVALID(502, "参数不正确"),

    ;

    private int code;
    private String msg;
}
