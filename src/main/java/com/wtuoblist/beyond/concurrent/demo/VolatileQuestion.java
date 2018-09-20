package com.wtuoblist.beyond.concurrent.demo;

/**
 * @author zhangfb
 */
public class VolatileQuestion {

    static volatile int i = 0;

    public static class PlusTask implements Runnable {

        public void run() {
            for (int k = 0; k < 10000; k++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];
        for (int j = 0; j < threads.length; j++) {
            threads[j] = new Thread(new PlusTask());
            threads[j].start();
        }
        for (int j = 0; j < threads.length; j++) {
            threads[j].join();
        }

        System.out.println(i);
    }
}
