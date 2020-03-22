package com.wy.hodgepodges.common;

import com.wy.hodgepodges.config.ConfigAdapter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-18 20:36
 */
public class SchedulerTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBean(ConfigAdapter.class);

    }
}
