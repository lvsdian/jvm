package cn.andios.jvm.classloader;

import java.lang.reflect.Method;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/11/16:29
 */
public class MyTest21 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");

        Class<?> clazz1 = loader1.loadClass("cn.andios.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("cn.andios.jvm.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object obj1 = clazz1.newInstance();
        Object obj2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(obj1,obj2);

    }
}
