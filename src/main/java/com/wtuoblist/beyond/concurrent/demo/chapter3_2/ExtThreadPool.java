package com.wtuoblist.beyond.concurrent.demo.chapter3_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangfb
 */
public class ExtThreadPool {

    public static class MyTask implements Runnable {

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        public void run() {
            System.out.println("正在执行:Thread id:" + Thread.currentThread().getId() + ",Task name:" + name);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
                                                    new LinkedBlockingQueue<Runnable>(5),
                                                    Executors.defaultThreadFactory()) {
            @Override protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行:" + ((MyTask) r).name);
            }

            @Override protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成:" + ((MyTask) r).name);
            }

            @Override protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 5; i++) {
            es.execute(new MyTask("task-geym-" + i));
            Thread.sleep(10L);
        }
        es.shutdown();
    }
}
