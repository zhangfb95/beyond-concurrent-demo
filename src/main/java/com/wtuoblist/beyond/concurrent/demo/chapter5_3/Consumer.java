package com.wtuoblist.beyond.concurrent.demo.chapter5_3;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhangfb
 */
public class Consumer implements Runnable {

    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override public void run() {
        System.out.println("start consumer id=" + Thread.currentThread().getId());
        Random r = new Random();

        try {
            while (true) {
                PCData data = queue.take();
                if (data != null) {
                    int re = data.getData() * data.getData();
                    System.out.println(data.getData() + "*" + data.getData() + "=" + re);
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
