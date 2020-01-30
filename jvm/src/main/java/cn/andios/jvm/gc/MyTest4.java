package cn.andios.jvm.gc;

/**
 * @description:
 * -verbose:gc
 * -Xmx200M
 * -Xmn50M
 * -XX:TargetSurvivorRatio=60
 * -XX:+PrintTenuringDistribution
 * -XX:+PrintGCDateStamps
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseParNewGC
 * -XX:MaxTenuringThreshold=3
 * @author:LSD
 * @when:2020/01/30/13:10
 */
public class MyTest4 {
    public static void main(String[] args) throws InterruptedException {
        //这两个数组不会被回收，回收的是myGC()方法中创建的数组
        byte [] byte_1 = new byte [512 * 1024];
        byte [] byte_2 = new byte [512 * 1024];

        myGC();
        Thread.sleep(1000);
        System.out.println("*******************");

        myGC();
        Thread.sleep(1000);
        System.out.println("-------------------");

        myGC();
        Thread.sleep(1000);
        System.out.println("++++++++++++++++++");

        myGC();
        Thread.sleep(1000);
        System.out.println("//////////////////");

        byte [] byte_3 = new byte [1024 * 1024];
        byte [] byte_4 = new byte [1024 * 1024];
        byte [] byte_5 = new byte [1024 * 1024];

        myGC();
        Thread.sleep(1000);
        System.out.println("!!!!!!!!!!!!!!!!!!");

        myGC();
        Thread.sleep(1000);
        System.out.println("||||||||||||||||||");

        System.out.println("end");
    }
    private static void myGC(){
        for (int i = 0; i < 40; i++) {
            byte [] byteArr = new byte[1024 * 1024];
        }
    }
}
