package cn.andios.jvm.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/04/18:02
 */
public class MyTest14 {
    public static void main(String[] args) throws IOException {
        /**
         * java.lang.Thread#getContextClassLoader()
         *      返回线程的上下文类加载器，这个上下文类加载器在加载类和资源的时候
         * 由线程的创建者运行线程中的代码来提供，如果没有设置setContextClassLoader,
         * 那么默认就是父线程的上下文类加载器，一个原始线程的上下文类加载器通常会设
         * 置为一个类加载器用于加载应用。
         *
         * java.lang.ClassLoader#getResources(java.lang.String)
         *      找到给定名字的所有资源，这些资源是可以被类的代码以一种与代码位置无关
         * 的方法访问的数据，资源的名字是由/来分割的路径名，
         */
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String resourceName = "cn/andios/jvm/classloader/MyTest13.class";
        Enumeration<URL> enumeration = classLoader.getResources(resourceName);
        while (enumeration.hasMoreElements()){
            URL url = enumeration.nextElement();
            System.out.println(url);
        }

        System.out.println("---------------------------");
        Class<MyTest14> myTest14Class = MyTest14.class;
        System.out.println(myTest14Class.getClassLoader());
        System.out.println(String.class.getClassLoader());
    }
}

