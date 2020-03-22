package com.wy.hodgepodges.service.impl;

import org.springframework.stereotype.Service;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-18 20:46
 */
@Service
public class DemoService {
    public String  outputResult(){
        System.out.println("从组合注解配置照样获得的bean");
        return "";
    }
}
