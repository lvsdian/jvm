package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/11/15:32
 */
public class MyTest19_1 {
    public static void main(String[] args) {
        /**
         *  打印三个层级的类加载器加载的类有哪些
         *  其中，
         *      C:\Program Files\Java\jdk1.8.0_171\jre\classes目录
         *      为sun.boot.class.path加载所包含的目录
         *
         *      C:\Users\LSD\Desktop\jvm\jvm\jvm\target\classes;
         *      即本项目的字节码文件目录为java.class.path加载所包
         *      含的目录
         *      因此，在MyTest19_2中，
         *          1.如果我们把MyTest1.class拷到
         *      C:\Program Files\Java\jdk1.8.0_171\jre\classes目录
         *      下，那么加载MyTest1的不再是AppClassLoader，而是
         *      Bootstrap ClassLoader,打印时为null
         *          2.如果不移动MyTest1.class，则由AppClassLoader加载
         *          3.如果把MyTest1.class复制到G盘，并把类路径下的MyTest1.class删掉，
         *      则由我们自定义的MyTest16类加载器加载
         */
        //Bootstrap ClassLoader
        System.out.println(System.getProperty("sun.boot.class.path"));

        //Extension ClassLoader
        System.out.println(System.getProperty("java.ext.dirs"));

        //App ClassLoader
        System.out.println(System.getProperty("java.class.path"));
    }
}
