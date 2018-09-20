package com.wtuoblist.beyond.concurrent.demo;

/**
 * @author zhangfb
 */
public class InterruptDontWork {

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread() {
            @Override public void run() {
                while (true) {
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000L);
        System.out.println("start interrupt");
        t1.interrupt();
        System.out.println("end interrupt");
    }
}
