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

        //不删除target下的MyPerson.class文件下，即不删除类路径下的MyPerson.class文件
        //此时clazz1由AppClassLoader加载，clazz2直接使用AppClassLoader
        //第一次加载MyPerson.class的结果，所以为true
        System.out.println(clazz1 == clazz2);

        Object obj1 = clazz1.newInstance();
        Object obj2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        //即：obj1.setMyPerson(obj2)
        //这里不会报错
        method.invoke(obj1,obj2);
    }
}
