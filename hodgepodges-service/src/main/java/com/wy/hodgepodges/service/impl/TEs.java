package com.wy.hodgepodges.service.impl;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-18 20:47
 */
public class TEs {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        DemoService semoService = context.getBean(DemoService.class);
        semoService.outputResult();
        context.close();
    }
}
