package com.wy.hodgepodges.common.design;


import com.wy.hodgepodges.common.design.observer.Subject;
import com.wy.hodgepodges.common.design.observer.impl.MySubject;
import com.wy.hodgepodges.common.design.observer.impl.Observer1;
import com.wy.hodgepodges.common.design.observer.impl.Observer2;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-23 11:43
 */
public class demo {

    public static void main(String[] args) {
        Subject sub = new MySubject();
        sub.add(new Observer1());
        sub.add(new Observer2());

        sub.operation();
    }
}
