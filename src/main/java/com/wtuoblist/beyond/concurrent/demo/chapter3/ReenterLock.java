package com.wtuoblist.beyond.concurrent.demo.chapter3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 进入了多少次锁，则需要退出多少次锁，次数必须相同。
 * 如果进入的次数比退出的次数多，则会产生死锁
 * 如果进入的次数比退出的次数少，则会出现异常java.lang.IllegalMonitorStateException
 *
 * @author zhangfb
 */
public class ReenterLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public void run() {
        for (int j = 0; j < 1000000; j++) {
            lock.lock();
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock rl = new ReenterLock();
        Thread t1 = new Thread(rl);
        Thread t2 = new Thread(rl);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
