package com.wtuoblist.beyond.concurrent.demo;

/**
 * @author zhangfb
 */
public class InterruptSleepWork {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread() {
            @Override public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted When Sleep");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000L);
        t1.interrupt();
    }
}
