package com.wy.hodgepodges.api.enums;

/**
 * @author weiyu
 * @version V1.0
 * @since 2018-12-07
 */
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    ErrorCodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg  = msg;
    }
}
