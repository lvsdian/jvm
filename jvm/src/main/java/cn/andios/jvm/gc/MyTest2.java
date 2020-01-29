package cn.andios.jvm.gc;

/**
 * @description:
 *  -verbose:gc
 *  -Xms20M
 *  -Xmx20M
 *  -Xmn10M
 *  -XX:+PrintGCDetails
 *  -XX:SurvivorRatio=8
 *  -XX:PretenureSizeThreshold=4194304
 *  -XX:+UseSerialGC
 * @author:LSD
 * @when:2020/01/29/21:41
 */
public class MyTest2 {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte [] myAllocate1 = new byte [5*size];
    }
}
