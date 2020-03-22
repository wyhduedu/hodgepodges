package com.wy.hodgepodges.demo;

import com.wy.hodgepodges.common.annotation.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-12-03 09:55
 */

@SpringBootTest
@Slf4j
public class Test1 {
    public static void main(String[] args) {
            throw new RuntimeException("ddj");



    }

    @Action(name = "djdj")
    public int caa(){

        log.info("jjdsjsd");
        return 1;

    }
}
