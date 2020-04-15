package com.wy.hodgepodges.common;

import com.wy.hodgepodges.WebApplicationTests;
import com.wy.hodgepodges.common.drools.DroolsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-15 18:35
 */
public class DroolsTest extends WebApplicationTests {

    @Autowired
    DroolsService droolsService;

    @Test
    public void aaa(){
        droolsService.fireRule();
    }
}
