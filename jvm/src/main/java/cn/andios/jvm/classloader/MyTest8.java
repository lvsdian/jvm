package cn.andios.jvm.classloader;

import java.util.Random;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/02/11:45
 */
public class MyTest8 {
    public static void main(String[] args) {
        //反编译可以看到,x1是常量，所以用iconst_3表示
        System.out.println(FinalTest.x1);
        //x2的值需要在运行期才能确定，所以是getstatic
        System.out.println(FinalTest.x2);
    }
}

class FinalTest{
    public static final int x1 = 3;
    public static final int x2 = new Random().nextInt(1);
    static {
        System.out.println("FinalTest static block");
    }
}
