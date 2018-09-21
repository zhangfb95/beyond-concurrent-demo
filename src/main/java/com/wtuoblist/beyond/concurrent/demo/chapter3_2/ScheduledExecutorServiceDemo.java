package com.wtuoblist.beyond.concurrent.demo.chapter3_2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangfb
 */
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(8000L);
                    System.out.println(System.currentTimeMillis() / 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
