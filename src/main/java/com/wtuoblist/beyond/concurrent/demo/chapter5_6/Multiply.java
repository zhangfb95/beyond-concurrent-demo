package com.wtuoblist.beyond.concurrent.demo.chapter5_6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhangfb
 */
public class Multiply implements Runnable {

    public static BlockingQueue<Msg> bq =new LinkedBlockingQueue<>();

    @Override public void run() {
        while (true) {
            try {
                Msg msg = bq.take();
                msg.i = msg.i + msg.j;
                Div.bq.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
