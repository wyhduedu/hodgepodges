package com.wy.hodgepodges.service;

import org.aspectj.weaver.Iterators;

import java.util.Iterator;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-21 23:38
 */
public class Source {

    public void method1() {
        Iterator iterator = Iterators.array(new Object[]{});
        System.out.println("this is original method!");
    }
}