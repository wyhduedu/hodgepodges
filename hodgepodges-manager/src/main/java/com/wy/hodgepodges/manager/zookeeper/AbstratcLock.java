package com.wy.hodgepodges.manager.zookeeper;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/20 4:40 下午
 */
public abstract class AbstratcLock implements Lock {


    @Override
    public void getLock() {
        if (tryLock()) {
            System.out.println("##获取锁的资源===============");
        } else {
            waitLock();
            getLock();
        }
    }

    public abstract boolean tryLock();

    public abstract void waitLock();
}
