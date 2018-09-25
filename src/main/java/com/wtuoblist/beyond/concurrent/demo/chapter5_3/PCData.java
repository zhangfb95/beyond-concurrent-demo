package com.wtuoblist.beyond.concurrent.demo.chapter5_3;

/**
 * @author zhangfb
 */
public class PCData {

    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String data) {
        intData = Integer.valueOf(data);
    }

    public int getData() {
        return intData;
    }

    @Override public String toString() {
        return "PCData:" + intData;
    }
}
