package com.wy.hodgepodges.api.response.common;

import com.wy.hodgepodge.sdk.enums.ErrorCodeMsg;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author weiyu
 * @version V1.0
 * @since 2018-12-07
 */
@Data
public class SdkResponse implements Serializable {

    private static final long serialVersionUID = -661701930329304203L;
    private Boolean success;
    private Integer code;
    private String msg;

    public static <T extends SdkResponse> T success(T t) {
        if (Objects.isNull(t)){
            t.setSuccess(true);
            t.setCode(ErrorCodeMsg.SUCCESS.getCode());
            t.setMsg(ErrorCodeMsg.SUCCESS.getMsg());
        }
        return t;
    }

    public static <T extends SdkResponse> T sectSuccess(T t) {
        t.setSuccess(true);
        t.setCode(ErrorCodeMsg.SECTSUCCESS.getCode());
        t.setMsg(ErrorCodeMsg.SECTSUCCESS.getMsg());
        return t;
    }


    public static <T extends SdkResponse> T failed(T t, int code, String msg) {
        t.setSuccess(false);
        t.setCode(code);
        t.setMsg(msg);
        return t;
    }

    public static <T extends SdkResponse> T failed(T t) {
        t.setSuccess(false);
        t.setCode(ErrorCodeMsg.FAIL.getCode());
        t.setMsg(ErrorCodeMsg.FAIL.getMsg());
        return t;
    }
}
