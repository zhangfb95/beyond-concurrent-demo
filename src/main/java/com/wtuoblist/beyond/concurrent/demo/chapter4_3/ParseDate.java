package com.wtuoblist.beyond.concurrent.demo.chapter4_3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangfb
 */
public class ParseDate implements Runnable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();

    int i = 0;

    public ParseDate(int i) {
        this.i = i;
    }

    @Override public void run() {
        try {
            if (tl.get() == null) {
                tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            Date t = tl.get().parse("2018-09-21 21:02:" + i % 60);
            System.out.println(i + ":" + t);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDate(i));
        }
        es.shutdown();
    }
}
