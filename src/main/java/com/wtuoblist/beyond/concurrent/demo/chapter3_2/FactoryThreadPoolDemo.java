package com.wtuoblist.beyond.concurrent.demo.chapter3_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangfb
 */
public class FactoryThreadPoolDemo {

    public static class MyTask implements Runnable {

        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(
            5, 5,
            0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),
            new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    System.out.println("create " + t);
                    return t;
                }
            }, new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString() + " is discard");
            }
        });
        for (int i = 0; i < 5; i++) {
            es.submit(task);
            Thread.sleep(10L);
        }
    }
}
