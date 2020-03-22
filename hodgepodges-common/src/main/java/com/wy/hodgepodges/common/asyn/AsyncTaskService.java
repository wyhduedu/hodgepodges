package com.wy.hodgepodges.common.asyn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-18 20:14
 */
@Service
@Slf4j
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer i){
        log.info("执行异步任务"+i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer j){
        log.info("执行异步任务1"+j);
    }
}
