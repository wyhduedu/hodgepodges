package com.wy.hodgepodges.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-11-01 11:31
 */
@Configuration
public class TestConfiguration {
    public TestConfiguration(){
        System.out.println("spring容器启动初始化。。。");
    }
}
