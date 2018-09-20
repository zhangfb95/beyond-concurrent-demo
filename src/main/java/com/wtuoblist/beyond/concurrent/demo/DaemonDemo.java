package com.wtuoblist.beyond.concurrent.demo;

/**
 * @author zhangfb
 */
public class DaemonDemo {

    public static class DaemonT extends Thread {

        @Override public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new DaemonT();
        t.setDaemon(true);
        t.start();
        Thread.sleep(2000L);
    }
}
