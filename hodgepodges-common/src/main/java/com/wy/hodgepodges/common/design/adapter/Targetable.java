package com.wy.hodgepodges.common.design.adapter;

/**
 * @author wy
 * @version V1.0
 * @desc 适配器模式
 * @date 2020/5/12 12:48 上午
 */
public interface Targetable {
    /**
     * 与原类中的方法相同
     */
      void method1();

    /**
     *  新类的方法
     */
      void method2();
}
