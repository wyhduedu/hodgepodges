package com.wy.hodgepodges.service;

import org.springframework.stereotype.Service;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-21 23:39
 */
@Service
public class AdapterTest {

    public static void main(String[] args) {
        Source source = new Source();
        Targetable target = new Wrapper(source);
        target.method1();
        target.method2();
    }

}
