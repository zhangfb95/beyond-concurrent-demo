package com.wtuoblist.beyond.concurrent.demo.chapter4_4;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhangfb
 */
public class AtomicIntegerQuestionDemo {

    static AtomicReference<Integer> money = new AtomicReference<>();

    static {
        money.set(19);
    }

    void add() {
        for (int i = 0; i < 30; i++) {
            new Thread() {
                @Override public void run() {
                    while (true) {
                        Integer m = money.get();
                        if (m < 20) {
                            if (money.compareAndSet(m, m + 20)) {
                                System.out.println("余额小于20元，充值成功，余额：" + money.get() + "元");
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }.start();
        }
    }


    void consume() {
        new Thread() {
            @Override public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        Integer m = money.get();
                        if (m > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(m, m - 10)) {
                                System.out.println("成功消费10元，余额：" + money.get());
                                break;
                            }
                        } else {
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                    /*try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        new AtomicIntegerQuestionDemo().consume();
        new AtomicIntegerQuestionDemo().add();
    }

}
