package com.wy.hodgepodges.common.design.adapter;

/**
 * @author wy
 * @version V1.0
 * @desc 适配器模式
 * @date 2020/5/12 12:48 上午
 */
public class Adapter extends Source implements Targetable {

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
