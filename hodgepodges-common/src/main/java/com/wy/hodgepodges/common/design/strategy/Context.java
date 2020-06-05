package com.wy.hodgepodges.common.design.strategy;

/**
 * @author wy
 * @version V1.0
 * @desc 策略模式
 * @date 2020-04-17 13:39
 */
public class Context {

    Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    //上下文接口
    public void contextInterface() {
        strategy.algorithmInterface();
    }

    public double getResult(Double n){

        return 0;
    }

}