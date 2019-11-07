package com.wy.hodgepodges.common.design.observer.impl;

import com.wy.hodgepodges.common.design.observer.Observer;
import org.springframework.stereotype.Component;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-23 11:45
 */
@Component
public class Observer1 implements Observer {

    @Override
    public void update() {
        System.out.println("observer1 has received!");

    }
}
