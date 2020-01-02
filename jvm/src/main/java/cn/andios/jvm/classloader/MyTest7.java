package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/02/11:20
 */
public class MyTest7 {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取一个类的Class对象的4种方法
        Class clazz = null;

        /**
         *  result:
         *      test static block
         *      clazz:class cn.andios.jvm.classloader.Test
         *  reason：
         *      通过 Class.forName 会导致类的初始化
         */
//        clazz = Class.forName("cn.andios.jvm.classloader.Test");
//        System.out.println("clazz:"+clazz);

        /**
         *  result:
         *      clazz:class cn.andios.jvm.classloader.Test
         *  reason:
         *      通过 类.class 不会导致类的初始化
         */
        clazz = cn.andios.jvm.classloader.Test.class;
        System.out.println("clazz:"+clazz);

        /**
         *  result:
         *      clazz:class cn.andios.jvm.classloader.Test
         *  reason:
         *      通过  ClassLoader.getSystemClassLoader().loadClass 不会导致类的初始化
         */
//        clazz = ClassLoader.getSystemClassLoader().loadClass("cn.andios.jvm.classloader.Test");
//        System.out.println("clazz:"+clazz);

        /**
         *  result:
         *      test static block
         *      clazz:class cn.andios.jvm.classloader.Test
         *  reason：
         *      通过 对象.getClass() 会导致类的初始化
         */
//        clazz = new Test().getClass();
//        System.out.println("clazz:"+clazz);
    }
}
class Test{
    static {
        System.out.println("test static block");
    }
}
