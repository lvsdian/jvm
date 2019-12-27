package cn.andios.jvm.classloader;

import java.util.Random;

/**
 * @Author: lsd
 * @Description:
 * @Date: 2019/12/27 下午8:05
 *
 * 当一个接口在初始化时,并不要求其父接口都完成了初始化
 *      (接口中变量都由final修饰,都是常量,但类中的变量不一定都由final修饰,如果没有被final
 *      修饰,就不会被纳入到常量池中,那么这个类被初始化时就会导致它的父类被初始化.)
 * 只有在真正使用到父接口的时候(如引用接口中所定义的常量时),才会初始化
 */
public class MyTest5 {
    public static void main(String[] args) {
        /**
         * a,b
         *
         * result:6
         * 编译后,删掉MyParent5.class,再运行,正常打印
         */
        System.out.println(MyChild5.b);

        /**
         * a,c
         *
         * result:一个随机值
         * 编译后,删除MyParent5.class,再运行,报错
         * reason:c的值是在运行期动态生成的
         */
        System.out.println(MyChild5.c);

        /**
         * d,e
         * result:2
         * case1:编译后,删除MyParent5.class,再运行,正常打印
         * case2:将MyParent5,MyChild5改为class,其他不变,编译后,
         *      删除MyParent5.class,再运行,报错
         * reason:对于接口来说,d,e都是常量(都由final修饰),会被纳入到常量池,
         *      初始化时不会导致父类被初始化;
         *         改为class后,不是常量,不会被纳入到常量池,被使用时就会导致父类初始化,
         *         所以删除MyParent.class后再运行就报错
         *
         */
        System.out.println(MyChild5.e);
    }
}

interface MyParent5{
    int a = 1;

    int d = new Random().nextInt(3);
}

interface MyChild5 extends MyParent5{
    int b = 2;
    int c = new Random().nextInt(2);

    int e = 2;
}