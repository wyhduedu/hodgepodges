package com.wy.hodgepodges.common.design.adapter;

/**
 * @author wy
 * @version V1.0
 * @desc  对象的适配器模式
 * @date 2020/5/12 12:50 上午
 */
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source){
        super();
        this.source = source;
    }
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    @Override
    public void method1() {
        source.method1();
    }
}
