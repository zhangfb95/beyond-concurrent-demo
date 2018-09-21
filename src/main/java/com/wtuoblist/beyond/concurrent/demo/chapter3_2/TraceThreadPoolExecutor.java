package com.wtuoblist.beyond.concurrent.demo.chapter3_2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangfb
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                   TimeUnit unit,
                                   BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override public void execute(Runnable command) {
        super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
    }

    @Override public Future<?> submit(Runnable task) {
        return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
        return new Runnable() {
            public void run() {
                try {
                    task.run();
                } catch (Exception e) {
                    clientStack.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private Exception clientTrace() {
        return new Exception("Client stack trace");
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE,
                                                              0, TimeUnit.SECONDS,
                                                              new SynchronousQueue<Runnable>());
        for (int i = 0; i < 5; i++) {
            pool.submit(new DivTask(100, i));
        }
    }
}
