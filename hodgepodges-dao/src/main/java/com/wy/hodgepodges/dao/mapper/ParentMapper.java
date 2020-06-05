package com.wy.hodgepodges.dao.mapper;

import com.wy.hodgepodges.moudle.vo.CityCateGoryVo;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/29 3:33 下午
 */
public interface ParentMapper extends Mapper<CityCateGoryVo> {

    CityCateGoryVo  selectTree(String parentId);
}
