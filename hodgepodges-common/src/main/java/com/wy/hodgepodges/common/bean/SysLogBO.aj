package com.wy.hodgepodges.common.bean;


import lombok.Data;

/**
 * @author wy
 * @desc
 * @date 2019-11-01 10:12
 * @version V1.0
 */
@Data
public class SysLogBO {

    public String className;

    public String methodName;

    public String params;

    public Long exeuTime;

    public String remark;

    public String createDate;




}
