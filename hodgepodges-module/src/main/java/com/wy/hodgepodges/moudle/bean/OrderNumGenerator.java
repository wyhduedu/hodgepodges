package com.wy.hodgepodges.moudle.bean;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/20 4:46 下午
 */
@Data
public class OrderNumGenerator {

    public static int count = 0;
    private Lock lock = new ReentrantLock();

    public String getNumber() {
        try {
            lock.lock();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            return sdf.format(new Date()) + "_" + ++count;
        } finally {
            lock.unlock();
        }

    }
}
