package com.wy.hodgepodges.manager.zookeeper;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/20 4:40 下午
 */
public interface Lock {
    public void getLock();
    public void unlock();
}
