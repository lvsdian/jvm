package cn.andios.jvm.gc;

/**
 * @description:
 * -verbose:gc
 * -Xms20M
 * -Xms20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * -XX:+UseConcMarkSweepGC
 * @author:LSD
 * @when:2020/01/30/18:04
 */
public class MyTest5 {
    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte [] myAllocate1 = new byte [4 * size];
        System.out.println("11111111");

        byte [] myAllocate2 = new byte [4 * size];
        System.out.println("22222222");

        byte [] myAllocate3 = new byte [4 * size];
        System.out.println("33333333");

        byte [] myAllocate4 = new byte [8 * size];
        System.out.println("44444444");

        System.out.println("end");
    }
}
