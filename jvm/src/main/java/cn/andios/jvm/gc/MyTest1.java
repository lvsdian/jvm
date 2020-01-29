package cn.andios.jvm.gc;

/**
 * @description:
 *  -verbose:gc
 *  -Xms20M
 *  -Xmx20M
 *  -Xmn10M
 *  -XX:+PrintGCDetails
 *  -XX:SurvivorRatio=8
 * @author:LSD
 * @when:2020/01/29/17:11
 */

public class MyTest1 {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte [] myAllocate1 = new byte [2 * size];
        byte [] myAllocate2 = new byte [3 * size];
        byte [] myAllocate3 = new byte [2 * size];
        byte [] myAllocate4 = new byte [5 * size];

        System.out.println("hello world");
    }
}
