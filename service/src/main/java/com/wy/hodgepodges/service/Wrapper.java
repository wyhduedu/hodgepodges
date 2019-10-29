package com.wy.hodgepodges.service;


/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-21 23:34
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
        System.out.println("dsds");
        source.method1();
    }
}
