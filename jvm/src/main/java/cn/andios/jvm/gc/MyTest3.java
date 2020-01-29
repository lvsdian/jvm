package cn.andios.jvm.gc;

/**
 * @description:
 *  -verbose:gc
 *  -Xms20M
 *  -Xmx20M
 *  -Xmn10M
 *  -XX:+PrintGCDetails
 *  -XX:SurvivorRatio=8
 *  -XX:+PrintCommandLineFlags
 *  -XX:MaxTenuringThreshold=5
 *  -XX:+PrintTenuringDistribution
 * @author:LSD
 * @when:2020/01/29/22:29
 */
public class MyTest3 {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte [] myAllocate1 = new byte [2 * size];
        byte [] myAllocate2 = new byte [3 * size];
        byte [] myAllocate3 = new byte [2 * size];
        byte [] myAllocate4 = new byte [5 * size];
    }
}
