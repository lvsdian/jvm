package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/02/13:09
 */
public class MyTest12 {
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * result:
         *      class cn.andios.jvm.classloader.CL
         *      *******************
         *      class CL
         *      class cn.andios.jvm.classloader.CL
         *
         *  reason:
         *      classLoader.loadClass不会导致类的初始化
         *      Class.forName会导致类的初始化
         *      类的7种主动使用的情况之一就是反射
         */
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = classLoader.loadClass("cn.andios.jvm.classloader.CL");
        System.out.println(clazz);
        System.out.println("*******************");
        clazz = Class.forName("cn.andios.jvm.classloader.CL");
        System.out.println(clazz);
    }


}

class CL {
    static {
        System.out.println("class CL");
    }
}
