package com.wy.hodgepodges.manager.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/20 4:43 下午
 */
public class ZookeeperDistributeLock extends ZookeeperAbstractLock{

    private CountDownLatch countDownLatch = null;

    //尝试获得锁
    @Override
    public boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //监听某个节点,匿名回调函数实现对节点信息变化的监听,
    @Override
    public void waitLock() {

        //一旦zookeeper检测到节点信息的变化，就会触发匿名匿名回调函数，通知订阅的客户端，即zkClient
        IZkDataListener iZkDataListener = new IZkDataListener() {

            @Override
            public void handleDataDeleted(String path) throws Exception {
                //唤醒被等待的线程
                if(countDownLatch != null){
                    countDownLatch.countDown();
                }
            }
            @Override
            public void handleDataChange(String path, Object data) throws Exception {

            }
        };
        //注册事件监听
        zkClient.subscribeDataChanges(PATH, iZkDataListener);

        //如果节点存在了，则需要等待一直到接收到了事件通知
        if(zkClient.exists(PATH)){
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        zkClient.unsubscribeDataChanges(PATH, iZkDataListener);
    }

    //释放锁
    @Override
    public void unlock() {
        if(zkClient != null){
            zkClient.delete(PATH);
            zkClient.close();
            System.out.println("释放锁资源");
        }
    }
}

