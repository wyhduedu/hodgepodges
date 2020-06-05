package com.wy.hodgepodges.common;

import com.wy.hodgepodges.WebApplicationTests;
import com.wy.hodgepodges.common.drools.DroolsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-15 18:35
 */
public class DroolsTest <T>extends WebApplicationTests {

    @Autowired
    DroolsService droolsService;

    @Autowired
    Map<String,T> map;

    @Test
    public void aaa(){
        droolsService.fireRule();
    }
}
