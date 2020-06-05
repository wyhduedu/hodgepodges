package com.wy.hodgepodges.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wy
 * @version V1.0
 * @desc 多线程
 * @date 2020-04-20 20:32
 */
@Slf4j
public class ThreadUtils {

   public static ExecutorService executorService = new ThreadPoolExecutor(10, 50, 60, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(50), new DIYThreadFactory("处理线程"), new DIYRejectedExecutionHandler("线程池满了"));

    public static ExecutorService getExecutorService() {
        return executorService;
    }


    /**
     * 多线程实现统计1-1000000之间有多少个素数
     *
     * @return
     */
    public int getPrimeNumber(int num) {
        ThreadLocal threadLocal = new ThreadLocal();
        ThreadLocal threadLocal1 = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        ThreadLocal threadLocal3 = new ThreadLocal();

        executorService.submit(()->{

        });
     NumberUtils.isPrimeNumber(num);
        return 0;

    }

    static class DIYRejectedExecutionHandler implements RejectedExecutionHandler {

        private String msg;

        public DIYRejectedExecutionHandler(String msg) {
            this.msg = msg;
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                log.error(msg);
                e.getQueue().poll();
                e.execute(r);
            }
        }
    }

    static class DIYThreadFactory implements ThreadFactory {

        private String name;

        public DIYThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            final AtomicInteger poolNumber = new AtomicInteger(1);
            final ThreadGroup group;
            final AtomicInteger threadNumber = new AtomicInteger(1);
            final String namePrefix;

            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-"
                    + name +
                    poolNumber.getAndIncrement() +
                    "-thread-";

            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }

    }

}
