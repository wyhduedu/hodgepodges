package com.wy.hodgepodges;

import com.wy.hodgepodges.service.repertory.BlogService;
import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-29 10:47
 */
public class aaaa {

    static class MyTask {
        private static ReentrantLock rl = new ReentrantLock();

        private static Condition conditionA = rl.newCondition();
        private static Condition conditionB = rl.newCondition();
        private static Condition conditionC = rl.newCondition();

        public void execute(String flag) {
            rl.lock();
            try {
                for (int i = 1; i <= 10; i++) {
                    if ("A".equals(flag)) {
                        System.out.println(Thread.currentThread().getName() + " - " + i);
                        conditionB.signal();
                        conditionA.await();
                    }

                    if ("B".equals(flag)) {
                        System.out.println(Thread.currentThread().getName() + " - " + i);
                        conditionC.signal();
                        conditionB.await();
                    }

                    if ("C".equals(flag)) {
                        System.out.println(Thread.currentThread().getName() + " - " + i);
                        conditionA.signal();
                        conditionC.await();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rl.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Iterator<BlogService> providers = Service.providers(BlogService.class);
        ServiceLoader<BlogService> load = ServiceLoader.load(BlogService.class);

        while (providers.hasNext()) {
            BlogService ser = providers.next();
            ser.execute();
        }
        System.out.println("--------------------------------");
        Iterator<BlogService> iterator = load.iterator();
        while (iterator.hasNext()) {
            BlogService ser = iterator.next();
            ser.execute();
        }
//        final MyTask myTask = new MyTask();
//        new Thread(() -> myTask.execute("A"), "A").start();
//        new Thread(() -> myTask.execute("B"), "B").start();
//        new T(hread(() -> myTask.execute("C"), "C").start();

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
//        RateLimiter rateLimiter = RateLimiter.create(3);
//        while(true) {
//            rateLimiter.acquire();
//            System.out.println(simpleDateFormat.format(new Date()));
//        }


    }

}
