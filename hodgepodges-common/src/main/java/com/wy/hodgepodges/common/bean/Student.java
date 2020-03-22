package com.wy.hodgepodges.common.bean;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-02-11 14:24
 */


import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Student implements Serializable {


    @NotNull(message = "名字不能为空")
    private String name;

    @Size(min = 6,max = 30,message = "地址应该在6-30字符之间")
    private String address;

    @DecimalMax(value = "100.00",message = "体重有些超标哦")
    @DecimalMin(value = "60.00",message = "多吃点饭吧")
    private BigDecimal weight;

    private String friendName;
    @AssertTrue
    private Boolean isHaveFriend(){
        return friendName != null?true:false;
    }

    @Future(message = "生日必须在当前实践之前")
    private Date birthday;

    @Pattern(regexp = "^(.+)@(.+)$",message = "邮箱的格式不合法")
    private String email;

}
