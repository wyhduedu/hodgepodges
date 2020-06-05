package com.wy.hodgepodges.common;

import com.wy.hodgepodges.common.asyn.AsyncTaskService;
import com.wy.hodgepodges.config.ConfigAdapter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-18 20:22
 */
public class AsyncTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext(ConfigAdapter.class);
        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        for (int i=0 ;i<10;i++){
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        context.close();

        HashMap<Object,Object> map = new HashMap<>();
    }
}
