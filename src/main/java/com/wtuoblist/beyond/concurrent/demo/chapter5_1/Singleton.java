package com.wtuoblist.beyond.concurrent.demo.chapter5_1;

/**
 * @author zhangfb
 */
public class Singleton {

    public Singleton() {
        System.out.println("Singleton is create");
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}
