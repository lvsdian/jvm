package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/14/9:36
 */

/**
 * 当前类加载器(Current ClassLoader):加载当前类的类加载器
 * 每个类都会使用自己的类加载器(即加载自身的类加载器)去加载其他类(指这个类所依赖的其它的类)
 * 如果ClassX引用了ClassY,那么ClassX的类加载器就会尝试加载ClassY,前提是ClassY尚未被加载,
 * 此时ClassX的类加载器就是当前类加载器
 *
 * 线程上下文类加载器（Context ClassLoader）
 *      是从jdk1.2开始引入的，Thread类中的getContextClassLoader与setContextClassLoader分别用来获取和设置线程上下文类加载器，
 *      如果没有设置，线程将继承其父线程的上下文类加载器
 *      java应用运行时初始线程的上下文类加载器是系统类加载器，在线程中运行的代码可以通过该类加载器来加载类与资源
 *
 * 线程上下文类加载器的重要性：
 *      父ClassLoader可以使用当前线程Thread.currentThread().getContextClassLoader()所指定的ClassLoader所加载的类，这就改变了父ClassLoader
 *  不能使用子ClassLoader或是其他没有直接父子关系的ClassLoader所加载的类的情况，即改变了双亲委托模型。
 *      线程上下文类加载器就是当前线程的Current ClassLoader
 *      在双亲委托模型下，类加载是由下至上的，即下层的类加载器会委托上层进行加载，但对于SPI(Service Provider Interface)来说，
 *  有些接口是java核心库提供的，而java核心库由启动类加载器来加载，这些接口的实现又来自不同的厂商，java启动类加载器不会加载其
 *  他来源的jar包，这样传统的双亲委托模型就无法满足SPI的要求。而通过给当前线程设置上下文类加载器，就可以由设置的类加载器来实现
 *  对接口的实现类的加载
 */
public class MyTest25 {
    public static void main(String[] args) {
        /**
         *  result:
         *      sun.misc.Launcher$AppClassLoader@18b4aac2
         *      null
         */
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.class.getClassLoader());
    }
}
