package cn.andios.jvm.classloader;

import java.lang.reflect.Method;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/12/23:07
 */
public class MyTest22 {
    public static void main(String[] args) throws Exception {
        /**
         * 定义类加载器：真正加载字节码的类加载器
         * 初始化类加载器：其他类加载器称为初始类加载器
         */

        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");

        //拷贝target下的MyPerson.class文件到G盘(移出类路径)，并删除
        //target下的MyPerson.class
        loader1.setPath("G:\\");
        loader2.setPath("G:\\");

        Class<?> clazz1 = loader1.loadClass("cn.andios.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("cn.andios.jvm.classloader.MyPerson");

        //此时clazz1由loader1加载，clazz2由loader2加载，所以结果为false
        System.out.println(clazz1 == clazz2);

        Object obj1 = clazz1.newInstance();
        Object obj2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        //即：obj1.setMyPerson(obj2)
        /**
         * result：
         *      java.lang.ClassCastException:
         *      cn.andios.jvm.classloader.MyPerson
         *      cannot be cast to
         *      cn.andios.jvm.classloader.MyPerson
         *
         *  loader1和loader2属于两个不同的命名空间，没有直接或间接的父子关系，
         *  所以它们加载的类clazz1和clazz2互相不可见
         *  所以这里会报错
         */
        method.invoke(obj1,obj2);
    }
}
