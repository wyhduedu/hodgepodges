package com.wy.hodgepodges.manager.zookeeper;

import com.wy.hodgepodges.moudle.bean.OrderNumGenerator;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020/5/20 4:45 下午
 */
public class OrderService implements Runnable{

    //订单号生成类
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();

    private Lock lock = new ZookeeperDistributeLock();
    //private Lock lock = new ZookeeperDistributeLock2();

    @Override
    public void run() {
        getNumber();
    }

    public void getNumber(){
        try {
            lock.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + ",产生了订单:" + number);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        System.out.println("##生成了订单####");
        for(int i=0;i<50;i++){
            new Thread(new OrderService()).start();
        }
    }

}

