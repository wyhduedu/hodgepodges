package com.wy.hodgepodges.common.design.adapter;

/**
 * @author wy
 * @version V1.0
 * @desc 类的适配器模式
 * @date 2020/5/12 12:47 上午
 */
public class Source {

    public void method1() {
        System.out.println("this is original method!");
    }

    public static void main(String[] args) {
        Targetable target = new Adapter();
        target.method1();
        target.method2();
    }

}
