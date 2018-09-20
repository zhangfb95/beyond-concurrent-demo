package com.wtuoblist.beyond.concurrent.demo.chapter3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangfb
 */
public class FairLock implements Runnable {

    public static ReentrantLock fairLock = new ReentrantLock(true);

    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock");
            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fl = new FairLock();
        Thread t1 = new Thread(fl, "thread1");
        Thread t2 = new Thread(fl, "thread2");
        t1.start();
        t2.start();
    }
}
