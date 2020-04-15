package com.wy.hodgepodges.service.repertory.impl;

import com.wy.hodgepodges.dao.repertory.BlogDao;
import com.wy.hodgepodges.service.repertory.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-05 21:52
 */
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;
}
