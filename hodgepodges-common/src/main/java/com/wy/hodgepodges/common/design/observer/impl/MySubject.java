package com.wy.hodgepodges.common.design.observer.impl;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-23 17:47
 */
public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }

}