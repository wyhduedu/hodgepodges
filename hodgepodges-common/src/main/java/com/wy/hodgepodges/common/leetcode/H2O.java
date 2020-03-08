package com.wy.hodgepodges.common.leetcode;

import java.util.concurrent.Semaphore;

/**
 * @author wy
 * @version V1.0
 * @desc H2O生成
 * @date 2019-11-29 18:05
 */
class H2O {
    public int number = 2;
    public H2O() { }

    public synchronized void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        while(number == 0) {
            this.wait();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        number--;
        this.notifyAll();
    }

    public synchronized void oxygen(Runnable releaseOxygen) throws InterruptedException {
        while(number != 0) {
            this.wait();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        number = 2;
        this.notifyAll();
    }

}