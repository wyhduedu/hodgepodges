package com.wy.hodgepodges;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-11-29 21:30
 */
public class ThreadTest extends Thread {

    private int ticket = 10;

    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (this){
            if (this.ticket > 0) {
                try {
                sleep(89);
                    System.out.println("内部->"+Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName() + "卖票---->" + (this.ticket--));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        }
    }

    public static void main(String[] arg) {
        System.out.println("main->>>>" +Thread.currentThread().getName());
        ThreadTest test = new  ThreadTest(1);
        System.out.println("线程状态--->"+ test.getState());
        test.start();
        System.out.println("线程状态--->"+ test.getState());
test.run();
        System.out.println("线程状态--->"+ test.getState());


//        ThreadTest t1 = new ThreadTest(1);
//        new Thread(t1,"线程1").start();
//        new Thread(t1,"线程2").start();
    }

    int i;

    ThreadTest(int i) {
        super();
        System.out.println(this.getClass().getName());
        this.i = i;
        this.setName("thread-name");
    }
//    public static void main(String[] args) {
//
//        ThreadTest test=new ThreadTest(9);
//        test.run();
//        Integer num  = 0;
//        synchronized (num){
//            try {
//                num.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            num++;
//
//        }
//
//        Thread thread = new Thread();
//        Runnable runnable= thread;
//
//    }


//    @Override
//    public void run() {
//
//    }
}
