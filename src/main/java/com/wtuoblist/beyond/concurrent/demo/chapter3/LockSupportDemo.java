package com.wtuoblist.beyond.concurrent.demo.chapter3;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhangfb
 */
public class LockSupportDemo {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        t1.start();
        Thread.sleep(100L);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
