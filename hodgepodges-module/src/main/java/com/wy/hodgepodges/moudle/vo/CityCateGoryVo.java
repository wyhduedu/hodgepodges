package com.wy.hodgepodges.moudle.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/29 3:25 下午
 */
@Data
public class CityCateGoryVo {

    private Integer parentId;
    private Integer id;
    private String department;
    private List<CityCateGoryVo>  cityList;

}
