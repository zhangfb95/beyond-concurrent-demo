package com.wtuoblist.beyond.concurrent.demo.chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zhangfb
 */
public class SemapDemo implements Runnable {

    final Semaphore semp = new Semaphore(5);

    public void run() {
        try {
            semp.acquire();
            Thread.sleep(2000L);
            System.out.println(Thread.currentThread().getId() + " done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semp.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }
}
