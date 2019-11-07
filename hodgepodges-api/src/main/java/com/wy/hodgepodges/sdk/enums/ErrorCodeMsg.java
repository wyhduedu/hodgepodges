package com.wy.hodgepodges.sdk.enums;

import lombok.Getter;

/**
 * @author weiyu
 * @version V1.0
 * @since 2018-12-07
 */
@Getter
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

    private Integer code;
    private String msg;

    ErrorCodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg  = msg;
    }
}
