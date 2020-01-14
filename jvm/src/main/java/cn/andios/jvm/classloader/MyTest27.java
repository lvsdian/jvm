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
 * 加mysql驱动依赖
 */
public class MyTest27 {
    public static void main(String[] args) {
        //Thread.currentThread().setContextClassLoader(MyTest23.class.getClassLoader().getParent());

        /**
         *  ServiceLoader在rt.jar下，由启动类来加载，但启动类不能加载到classpath中的类，那么mysql的驱动类就无法加载。
         *  所以在ServiceLoader.load()方法中获取了线程上下文类加载器：
         *      ClassLoader cl = Thread.currentThread().getContextClassLoader()
         *  在Launcher类中可以看到线程上下文类加载器是系统类加载器，所以后面操作的都是系统类加载器，因此
         *  mysql驱动可以正常加载
         *  验证：
         *      如上，设置当前线程上下文类加载器为MyTest23.class.getClassLoader().getParent()，即扩展类加载器，再
         *      运行下面的代码，结果不一样了
         */
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        /**
         * result：
         *      driver:class com.mysql.jdbc.Driver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
         *      driver:class com.mysql.fabric.jdbc.FabricMySQLDriver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
         *      当前线程上下文类加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
         *      ServiceLoader类加载器：null
         *
         *  如果设置当前线程上下文类加载器为MyTest23.class.getClassLoader().getParent()，即扩展类加载器后，
         *  result：
         *      当前线程上下文类加载器：sun.misc.Launcher$ExtClassLoader@1540e19d
         *      ServiceLoader类加载器：null
         *      因为扩展类加载器不会寻找当前应用的classpath，所以加载不到mysql驱动
         */
        while (iterator.hasNext()){
            Driver driver = iterator.next();
            System.out.println("driver:"+driver.getClass()+",loader:"+driver.getClass().getClassLoader());
        }
        System.out.println("当前线程上下文类加载器："+Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader类加载器："+ServiceLoader.class.getClassLoader());
    }
}
