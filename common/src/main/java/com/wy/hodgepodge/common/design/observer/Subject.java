package com.wy.hodgepodge.common.design.observer;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-23 17:46
 */
public interface Subject {
    /*增加观察者*/
    void add(Observer observer);

    /*删除观察者*/
    void del(Observer observer);

    /*通知所有的观察者*/
    void notifyObservers();

    /*自身的操作*/
    void operation();
}
