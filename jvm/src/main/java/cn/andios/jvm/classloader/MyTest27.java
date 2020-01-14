package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/14/12:05
 */

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 线程上下文类加载器一般使用模式：（获取-使用-还原）
 *      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
 *      try{
 *          Thread.currentThread().setContextClassLoader(targetTccl);
 *          myMethod();
 *      }finally{
 *          Thread.currentThread.setContextClassLoader(classLoader);
 *      }
 *      myMethod里面则调用了Thread.currentThread().getContextClassLoader(),获取当前线程上下文做某些事情
 *      如果一个类由类加载器A加载，那么这个类的依赖类也由相同的类加载器加载(如果这个依赖类之前没有被加载过的话)，
 *
 *      contextClassLoader的作用就是为了破坏java的类加载委托机制
 *
 *      当高层提供了统一的接口让低层去实现，同时又要在高层加载(或实例化)低层的类时，就必须要通过线程上下文类加载器
 *  来帮助高层的ClassLoader找到并加载该类
 */
public class MyTest27 {

    public static void main(String[] args) {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()){
            Driver driver = iterator.next();
            System.out.println("driver:"+driver.getClass()+",loader:"+driver.getClass().getClassLoader());
        }
        System.out.println("当前线程上下文类加载器："+Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader类加载器："+ServiceLoader.class.getClassLoader());
    }
}
