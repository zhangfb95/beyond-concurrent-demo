package com.wtuoblist.beyond.concurrent.demo.chapter6_3;

import java.util.Arrays;

/**
 * @author zhangfb
 */
public class Main {

    static int[] array = {1, 2, 3, 4, 5, 6, 7};

    public static void main(String[] args) {
        Arrays.stream(array).forEach(System.out::println);
    }
}
