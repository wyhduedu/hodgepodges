package com.wy.hodgepodges.service.repertory.impl;

import com.wy.hodgepodges.dao.repertory.BlogDao;
import com.wy.hodgepodges.service.repertory.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-05 21:52
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public void execute() {
        log.info("djdj");
    }
}
