package com.wy.hodgepodges.manager.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/20 4:42 下午
 */
public class ZookeeperAbstractLock extends AbstratcLock{

    private static String CONNECT_PATH = "127.0.0.1:2181";

    protected ZkClient zkClient = new ZkClient(CONNECT_PATH);

    protected static final String PATH = "/lock";

    protected static final String PATH2 = "/lock2";


    @Override
    public boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void waitLock() {

    }

    @Override
    public void unlock() {

    }
}
