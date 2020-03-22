package com.wy.hodgepodges.demo;

import com.wy.hodgepodges.common.asyn.AsyncTaskService;
import com.wy.hodgepodges.config.ConfigAdapter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-18 20:22
 */
public class AsyncTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext  ccontext = new AnnotationConfigApplicationContext(ConfigAdapter.class);
        AsyncTaskService  asyncTaskService = ccontext.getBean(AsyncTaskService.class);
        for (int i=0 ;i<10;i++){
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        ccontext.close();
    }
}
