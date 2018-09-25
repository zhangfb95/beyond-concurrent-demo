package com.wtuoblist.beyond.concurrent.demo.chapter5_5;

/**
 * @author zhangfb
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String result) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(result);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.result = sb.toString();
    }

    @Override public String getResult() {
        return result;
    }
}
