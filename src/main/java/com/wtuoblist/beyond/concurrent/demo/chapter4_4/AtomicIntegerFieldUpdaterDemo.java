package com.wtuoblist.beyond.concurrent.demo.chapter4_4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author zhangfb
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static class Candidate {

        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater =
        AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    public static AtomicInteger totalScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] threads = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            threads[i] = new Thread() {
                @Override public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(stu);
                        totalScore.incrementAndGet();
                    }
                }
            };
            threads[i].start();
        }

        for (int i = 0; i < 10000; i++) {
            threads[i].join();
        }
        System.out.println("score= " + stu.score);
        System.out.println("allScore= " + totalScore.get());
    }
}
