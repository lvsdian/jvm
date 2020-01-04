package cn.andios.jvm.classloader;

/**
 * @description:
 * @author:LSD
 * @when:2020/01/04/17:31
 */
public class MyTest13 {
    public static void main(String[] args) {
        /**
         * ClassLoader.getSystemClassLoader()：
         *      返回一个委托的系统类加载器，它是新的类加载器实例默认委托的双亲，
         * 也是典型的用来启动应用的类加载器
         *      这个方法在运行启动之前就被首次调用，在这个时间点它会创建系统类
         * 加载器并设置为调用线程的上下文类加载器
         *      默认的系统类加载器是一个与这个类相关的实例
         *      如果在这个方法首次被调用时定义了java.system.class.loader属性，
         * 那么这个属性的值会作为返回的系统类加载器的名字，这个类会使用默认的
         * 类加载器去加载，而且必须定义一个public的构造器来接收一个ClassLoader
         * 类型的参数用于作为委托的双亲。然后构造器会根据这个参数创建一个实例，
         * 所生成的就被定义为类加载器
         *
         * java.lang.ClassLoader#getParent():
         *      返回委托的父加载器，一些实现会使用null来代表启动类加载器，如果
         * 这个类加载器的父亲是启动类加载器那么这个方法就会返回null
         */
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        while (null != classLoader){

            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }

    }
}
