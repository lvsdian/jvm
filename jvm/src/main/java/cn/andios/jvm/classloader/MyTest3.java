package cn.andios.jvm.classloader;

import java.util.UUID;

/**
 * @Author: lsd
 * @Description:
 * @Date: 2019/12/27 下午7:13
 *
 * 当一个常量的值并非编译期间可以确定的,那么其值就不会被放到调用类的常量池中,
 * 这时在运行程序时,会导致主动使用这个常量所在的类,显然会导致这个类被初始化
 */
public class MyTest3 {
    public static void main(String[] args) {
        System.out.println(MyParent3.str);
//        result:
//          MyParent3 static static block
//          f3c1e34d-01fd-4450-95e8-a682ea983ce9
//        reason:
//          str的值在编译期不能确定,所以不会放到MyTest3的常量池中,
//          运行程序时,就会导致MyParent3的初始化
    }
}

class MyParent3{
    public static final String str = UUID.randomUUID().toString();
    static {
        System.out.println("MyParent3 static static block");
    }
}
