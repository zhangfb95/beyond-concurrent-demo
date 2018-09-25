package com.wtuoblist.beyond.concurrent.demo.chapter5_5;

/**
 * @author zhangfb
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据=" + data.getResult());
    }
}
