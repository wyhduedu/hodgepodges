package com.wy.hodgepodge.common.design.observer.impl;

import com.wy.hodgepodge.common.design.observer.Observer;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-23 11:46
 */
public class Observer2 implements Observer {
    @Override
    public void update() {
        System.out.println("observer2 has received!");
    }
}
