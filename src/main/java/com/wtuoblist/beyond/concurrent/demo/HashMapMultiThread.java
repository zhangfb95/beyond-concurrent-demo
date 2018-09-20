package com.wtuoblist.beyond.concurrent.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangfb
 */
public class HashMapMultiThread {

    static Map<String, String> map = new HashMap<String, String>();

    public static class AddThread implements Runnable {

        int start = 0;

        public AddThread(int start) {
            this.start = start;
        }

        public void run() {
            for (int i = start; i < 100000; i += 2) {
                map.put(i + "", i + "");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new AddThread(0));
        Thread t2 = new Thread(new AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
