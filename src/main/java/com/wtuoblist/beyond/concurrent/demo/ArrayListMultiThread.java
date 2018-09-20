package com.wtuoblist.beyond.concurrent.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangfb
 */
public class ArrayListMultiThread {

    static List<Integer> al = new ArrayList<Integer>();

    public static class AddThread implements Runnable {

        public void run() {
            for (int i = 0; i < 1000000; i++) {
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}
