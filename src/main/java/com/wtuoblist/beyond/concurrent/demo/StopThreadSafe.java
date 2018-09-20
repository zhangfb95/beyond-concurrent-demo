package com.wtuoblist.beyond.concurrent.demo;

/**
 * @author zhangfb
 */
public class StopThreadSafe {

    public static User u = new User();

    public static class User {

        private int id;
        private String name;

        public User() {
            this.id = 0;
            this.name = "0";
        }

        @Override public String toString() {
            return "User{" +
                   "id=" + id +
                   ", name='" + name + '\'' +
                   '}';
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class ChangeObjectThread extends Thread {

        private volatile boolean stopme;

        public void stopme() {
            stopme = true;
        }

        @Override public void run() {
            while (true) {
                if (stopme) {
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(v + "");
                    Thread.yield();
                }
            }
        }
    }

    public static class ReadObjectThread extends Thread {

        @Override public void run() {
            while (true) {
                synchronized (u) {
                    if (u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new ReadObjectThread().start();
        while (true) {
            ChangeObjectThread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stopme();
        }
    }
}
