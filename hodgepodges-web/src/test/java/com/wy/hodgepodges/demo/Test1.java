package com.wy.hodgepodges.demo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;
/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-12-03 09:55
 */

public class Test1 {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.245.101:2181", retryPolicy);
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client, "/mylock");
        //lock.acquire(1000, TimeUnit.MILLISECONDS) 获取锁，超时时间为1000毫秒
        if ( lock.acquire(1000, TimeUnit.MILLISECONDS) )
        {
            try
            {
                System.out.println("得到锁，并执行");
                //模拟线程需要执行很长时间，观察ZK中/mylock下的临时ZNODE情况
                Thread.sleep(10000000);
            }
            finally
            {
                lock.release();
                System.out.println("释放锁");
            }
        }
    }


}
