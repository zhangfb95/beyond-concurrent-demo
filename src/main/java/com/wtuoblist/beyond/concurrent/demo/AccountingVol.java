package com.wtuoblist.beyond.concurrent.demo;

/**
 * @author zhangfb
 */
public class AccountingVol implements Runnable {

    static AccountingVol instance = new AccountingVol();
    static volatile int i = 0;

    public static void increase() {
        i++;
    }

    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new AccountingVol());
        Thread t2 = new Thread(new AccountingVol());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
