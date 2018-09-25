package com.wtuoblist.beyond.concurrent.demo.chapter5_3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhangfb
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<>(10);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(producer1);
        es.execute(producer2);
        es.execute(producer3);
        es.execute(consumer1);
        es.execute(consumer2);
        es.execute(consumer3);

        Thread.sleep(10 * 1000L);
        producer1.stop();
        producer2.stop();
        producer3.stop();
        Thread.sleep(3000L);
        es.shutdown();
    }
}
