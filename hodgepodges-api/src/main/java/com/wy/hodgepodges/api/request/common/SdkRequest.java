package com.wy.hodgepodges.api.request.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author heqiang
 */
@Data
public class SdkRequest implements Serializable {

    private static final long serialVersionUID = 1395095583597723341L;
    /**
     * 接口请求号，不可空
     */
    private String requestNo;

}
