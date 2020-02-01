package cn.andios.jvm.g1;

/**
 * @description:
 *  -verbose:gc
 * -Xms10M
 * -Xmx10M
 * -XX:+UseG1GC
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -XX:MaxGCPauseMillis=200m
 *
 * @author:LSD
 * @when:2020/02/01/12:45
 */
public class MyTest1 {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte [] myAllocate1 = new byte [size];
        byte [] myAllocate2 = new byte [size];
        byte [] myAllocate3 = new byte [size];
        byte [] myAllocate4 = new byte [size];
        System.out.println("end");
    }
}
