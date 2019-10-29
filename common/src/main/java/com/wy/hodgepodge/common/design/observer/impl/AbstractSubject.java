package com.wy.hodgepodge.common.design.observer.impl;


import com.wy.hodgepodge.common.design.observer.Observer;
import com.wy.hodgepodge.common.design.observer.Subject;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-23 17:47
 */
public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while(enumo.hasMoreElements()){
            enumo.nextElement().update();
        }
    }
}
